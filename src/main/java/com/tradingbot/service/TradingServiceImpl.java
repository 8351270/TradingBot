package com.tradingbot.service;

import com.google.gson.Gson;
import com.tradingbot.entity.order.OpenOrder;
import com.tradingbot.entity.order.request.cancelorder.CancelOrder;
import com.tradingbot.entity.order.request.neworder.PlaceNewOrder;
import com.tradingbot.entity.order.request.updateorder.ReplaceOrder;
import com.tradingbot.entity.order.response.OrderResponse;
import com.tradingbot.entity.order.response.inner.Price;
import com.tradingbot.entity.orderbook.OrderBookItem;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class TradingServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(TradingServiceImpl.class);

    final
    Environment env;

    // data updated from balance
    private BigDecimal ordersMargin;
    private BigDecimal wallet;
    private BigDecimal positionsMargin;
    private BigDecimal unrealizedPnl;
    private BigDecimal available;
    private BigDecimal borrowed;

    //  updated from position
    private Integer positionSize;
    // updated from risk settings
    private BigDecimal riskLimit;

    // id to save as internal and match with latter server response
    private int orderId = 100;
    // active buy order
    private OpenOrder buyOrder = null;
    // active sell order
    private OpenOrder sellOrder = null;

    //set true once we receive balance information for first time
    private boolean balanceInitialized;
    //set true once we receive orders information for fist time
    private boolean orderInitialized;
    //set true once we receive orders fill information for fist time
    private boolean orderFillInitialized;
    //set true once we receive positionInformation for first time
    private boolean positionInitialized;
    // set true once we receive risk configuration
    private boolean riskSettingsInitialized;

    // will be true once (balanceInitialized && orderInitialized && orderFillInitialized) is true
    private boolean readyToTrade;


    public TradingServiceImpl(Environment env) {
        this.env = env;

    }

    public List<WebSocketMessage<String>> checkForConditionsAndCreateOrder(List<OrderBookItem> asks, List<OrderBookItem> bids) {

        BigDecimal bestBid = bids.stream().map(OrderBookItem::toBigDecimal).max(BigDecimal::compareTo).orElseThrow(NoSuchElementException::new);
        BigDecimal bestAsk = asks.stream().map(OrderBookItem::toBigDecimal).min(BigDecimal::compareTo).orElseThrow(NoSuchElementException::new);

        BigDecimal interest = new BigDecimal(Objects.requireNonNull(env.getProperty("interest")));
//        Integer positionInitial = Integer.valueOf(Objects.requireNonNull(env.getProperty("position.initial")));
        Integer positionMax = Integer.valueOf(Objects.requireNonNull(env.getProperty("position.max")));
        BigDecimal shift = new BigDecimal(Objects.requireNonNull(env.getProperty("shift")));
        Integer quoteSize = Integer.valueOf(Objects.requireNonNull(env.getProperty("quote.size")));
        BigDecimal average = bestBid.add(bestAsk).divide(new BigDecimal(2), RoundingMode.FLOOR);
        //buy order at (current best purchase price + current best sale price) / 2 - interest - shift * position
        BigDecimal myBuyPrice = average.subtract(interest).subtract(shift.multiply(BigDecimal.valueOf(this.positionSize)));
        //a sell order at (current best purchase price + current best sale price) / 2 + interest - shift * position
        BigDecimal mySellPrice = average.add(interest).subtract(shift.multiply(BigDecimal.valueOf(this.positionSize)));

        List<WebSocketMessage<String>> ret = new ArrayList<>();

        // update the quote size value to
        quoteSize = this.calculateQuoteSize(quoteSize, positionMax);
        if (quoteSize > 0) {
            quoteSize = this.checkBalance(quoteSize);
        }
        if (quoteSize == 0) {
            return null;

        }

        if (this.buyOrder == null) {
            ret.add(this.createBuyOrder(myBuyPrice, quoteSize));
        } else if (this.buyOrder.getPrice().compareTo(myBuyPrice) != 0) {
            if (this.buyOrder.getExchangeOrderId() != null && this.buyOrder.isConfirmed() && !this.buyOrder.getRemainingSize().equals(0)) {
                ret.add(this.updateBuyOrder(myBuyPrice, quoteSize));
            }
        }

        if (this.sellOrder == null) {
            ret.add(this.createSellOrder(mySellPrice, quoteSize));
        } else if (this.sellOrder.getPrice().compareTo(mySellPrice) != 0) {
            if (this.sellOrder.getExchangeOrderId() != null && this.sellOrder.isConfirmed() && !this.sellOrder.getRemainingSize().equals(0)) {
                ret.add(this.updateSellOrder(mySellPrice, quoteSize));
            }
        }
        return ret;

    }

    //  next position has to respect the formula: maxposition >= position >= -maxposition
    //  if not possible we shrink the position size to the max amount that will maintain the formula
    private Integer calculateQuoteSize(Integer quoteSize, Integer positionMax) {

        Integer futurePositionSIze = this.positionSize;
        if (futurePositionSIze <= 0) {
            futurePositionSIze += quoteSize;
            if (positionMax <= futurePositionSIze) {
                LOGGER.debug("quote size should be reduced to keep the position lower than max position: "
                        + positionMax + " quote size will be: " + (futurePositionSIze - positionMax));
                return (futurePositionSIze - positionMax);
            } else {
                return quoteSize;
            }
        } else {
            futurePositionSIze -= quoteSize;
            if (-positionMax >= futurePositionSIze) {
                LOGGER.debug("quote size should be reduced to keep the position higher than -max position: -"
                        + positionMax + " quote size will be: " + (futurePositionSIze - positionMax));
                return ((futurePositionSIze + positionMax) * -1);
            } else {
                return quoteSize;
            }
        }
    }

    private Integer checkBalance(Integer size) {
        // exchange won't allow you to replace an order if you don't have enough available balance for both orders at the same time
        BigDecimal microBtc = this.available.multiply(BigDecimal.valueOf(1000000));
        BigDecimal bg = microBtc.divide(BigDecimal.valueOf(2).multiply(BigDecimal.valueOf(size)));
        if (bg.compareTo(BigDecimal.valueOf(size)) > 0) {
            return size;
        } else {
            if (bg.intValue() > 0) {
                LOGGER.debug("Not enough balance to keep the quote size, quote size will be: " + bg.intValue());
            } else {
                LOGGER.debug("Not enough balance to keep trading: ");
            }
            return bg.intValue();
        }
    }

    private WebSocketMessage<String> updateSellOrder(BigDecimal price, Integer quoteSize) {
        ReplaceOrder replaceOrder = updateOrder(price, quoteSize, this.sellOrder.getExchangeOrderId());
        this.sellOrder.setInternalOrderId(this.orderId);
        this.sellOrder.setConfirmed(false);
        Gson gson = new Gson();
        String jsonOrder = gson.toJson(replaceOrder);
        WebSocketMessage<String> message = new TextMessage(jsonOrder);
        LOGGER.debug("will update a sell order: " + message.getPayload());
        return message;
    }

    private WebSocketMessage<String> updateBuyOrder(BigDecimal price, Integer quoteSize) {
        ReplaceOrder replaceOrder = updateOrder(price, quoteSize, this.buyOrder.getExchangeOrderId());
        this.buyOrder.setInternalOrderId(this.orderId);
        this.buyOrder.setConfirmed(false);
        Gson gson = new Gson();
        String jsonOrder = gson.toJson(replaceOrder);
        WebSocketMessage<String> message = new TextMessage(jsonOrder);
        LOGGER.debug("will update a buy order: " + message.getPayload());
        return message;
    }

    private ReplaceOrder updateOrder(BigDecimal price, Integer quoteSize, Long exchangeId) {
        ReplaceOrder replaceOrder = new ReplaceOrder();
        this.orderId++;
        replaceOrder.setId(this.orderId);
        replaceOrder.setMethod(9);
        replaceOrder.getParams().getData().setMethod("replaceOrder");
        replaceOrder.getParams().getData().getParams().setOrderId(exchangeId);
        replaceOrder.getParams().getData().getParams().getPrice().setExponent(0);
        replaceOrder.getParams().getData().getParams().getPrice().setMantissa(price.intValue());
        replaceOrder.getParams().getData().getParams().setSize(quoteSize);
        return replaceOrder;
    }

    private PlaceNewOrder createOrder(BigDecimal buyPrice, Integer size) {

        this.orderId++;
        PlaceNewOrder placeNewOrder = new PlaceNewOrder(9, this.orderId);
        placeNewOrder.getParams().getData().setMethod("placeOrder");
        placeNewOrder.getParams().getData().getParams().setInstrument(Integer.parseInt(Objects.requireNonNull(env.getProperty("instrument.code"))));
        placeNewOrder.getParams().getData().getParams().getPrice().setExponent(0);
        placeNewOrder.getParams().getData().getParams().getPrice().setMantissa(buyPrice.intValue());
        placeNewOrder.getParams().getData().getParams().setSize(size);
        placeNewOrder.getParams().getData().getParams().setType(env.getProperty("order.type"));
        placeNewOrder.getParams().getData().getParams().setTimeInForce(env.getProperty("position.type"));

        return placeNewOrder;
    }

    private WebSocketMessage<String> createSellOrder(BigDecimal sellPrice, Integer size) {
        PlaceNewOrder placeNewOrder = this.createOrder(sellPrice, size);
        placeNewOrder.getParams().getData().getParams().setSide("SELL");
        Gson gson = new Gson();
        String jsonOrder = gson.toJson(placeNewOrder);
        WebSocketMessage<String> message = new TextMessage(jsonOrder);
        this.sellOrder = new OpenOrder(this.orderId, size, sellPrice);
        LOGGER.debug("will create a sell order: " + message.getPayload());
        return message;
    }

    private WebSocketMessage<String> createBuyOrder(BigDecimal buyPrice, Integer size) {
        PlaceNewOrder placeNewOrder = this.createOrder(buyPrice, size);
        placeNewOrder.getParams().getData().getParams().setSide("BUY");
        Gson gson = new Gson();
        String jsonOrder = gson.toJson(placeNewOrder);
        WebSocketMessage<String> message = new TextMessage(jsonOrder);
        this.buyOrder = new OpenOrder(this.orderId, size, buyPrice);
        LOGGER.debug("will create a buy order: " + message.getPayload());
        return message;
    }

    // function to be called from Orders Service when a order update arrives
    public void updateOrderStatus(OrderResponse order) {
        switch (order.getStatus()) {
            case "CANCELLED":
                // the order response should arrive first than this and remove the order after canceling it
                if (order.getSide().equalsIgnoreCase("BUY")) {
                    if (this.buyOrder.getExchangeOrderId().equals(order.getId())) {
                        LOGGER.debug("Buy order canceled, internal order is: " + this.buyOrder.toString());
//                        if (this.buyOrder.isConfirmed()){
//                            this.buyOrder = null;
//                        }else {
//                            this.buyOrder.setRemainingSize(0);
//                        }

                    }
                } else if (this.sellOrder.getExchangeOrderId().equals(order.getId())) {
                    LOGGER.debug("Sell order canceled, internal order is: " + this.sellOrder.toString());
//                    if (this.sellOrder.isConfirmed()){
//                        this.sellOrder = null;
//                    }else {
//                        this.sellOrder.setRemainingSize(0);
//                    }

                } else {
                    LOGGER.debug("Cancel order was not matched, this may happen after reconnect, id should match a previously canceled order, order is: " + order.toString());
                }
                break;
            case "FILLED":
                if (order.getSide().equalsIgnoreCase("BUY") && this.buyOrder.getExchangeOrderId().equals(order.getId())) {
                    LOGGER.info("Buy Order completely filled, internal order is: " + this.buyOrder.toString());
                    if (this.buyOrder.isConfirmed()){
                        this.buyOrder = null;
                    }else {
                        this.buyOrder.setRemainingSize(0);
                    }

                } else if (this.sellOrder.getExchangeOrderId().equals(order.getId())) {
                    LOGGER.info("Sell Order completely filled, internal order is: " + this.sellOrder.toString());
                    if (this.sellOrder.isConfirmed()){
                        this.sellOrder = null;
                    }else {
                        this.sellOrder.setRemainingSize(0);
                    }
                }
//                LOGGER.debug("Completely filled order was not matched, this may happen after reconnect, id should match a previously canceled order, order is: " + order.toString());
                break;
            case "PARTIALLY_FILLED":
                if (order.getSide().equalsIgnoreCase("BUY")) {
                    this.buyOrder.setRemainingSize(order.getRemainingSize());
                    this.buyOrder.setUpdateTime(order.getUpdateTime().getSeconds());
                    LOGGER.info("Buy Order partially filled, internal order is: " + this.buyOrder.toString());
                } else {
                    this.sellOrder.setRemainingSize(order.getRemainingSize());
                    this.sellOrder.setUpdateTime(order.getUpdateTime().getSeconds());
                    LOGGER.info("Sell Order partially filled, internal order is: " + this.sellOrder.toString());
                }
                LOGGER.info("Order partially filled: " + order.toString());
                break;
            case "NEW":
                if (order.getSide().equalsIgnoreCase("BUY")) {
                    LOGGER.debug("buy order exchange id updated, new value is:" + order.getId());
                    this.buyOrder.setExchangeOrderId(order.getId());
//                    this.buyOrder.setConfirmed(true);
                } else {
                    LOGGER.debug("sell order exchange id updated, new value is:" + order.getId());
                    this.sellOrder.setExchangeOrderId(order.getId());
//                    this.sellOrder.setConfirmed(true);
                }
                break;
        }
    }

    // read the response from the server to an order creation or update (tag={ok,err})
    // if confirmed we save the internal id and update the update time
    public void setOrderConfirm(JSONObject jsonResponse) throws JSONException {
        Integer id = jsonResponse.getInt("id");
        String tag = jsonResponse.getJSONObject("result").getString("tag");
        if (this.sellOrder != null) {
            if (this.sellOrder.getInternalOrderId().equals(id)) {
                if (tag.equals("err")) {
                    String errCode = jsonResponse.getJSONObject("result").getJSONObject("value").getString("code");
                    LOGGER.error("sell order: " + this.sellOrder.toString());
                    LOGGER.error("was refused by the server with error code: " + errCode);
                    this.sellOrder = null;
                    //tag=ok
                } else {
                    if (jsonResponse.getJSONObject("result").getString("value").equals("ok")) {
                        //this is the confirm of an update
                        LOGGER.debug("sell order updated in server, order is:" + this.sellOrder.toString());

                    } else {
                        //this is the confirm of an order creation
                        this.sellOrder.setExchangeOrderId(jsonResponse.getJSONObject("result").getLong("value"));
                        LOGGER.debug("sell order was successfully created on server, order is: " + this.sellOrder.toString());
                    }
                    if (this.sellOrder.getRemainingSize().equals(0)){
                        this.sellOrder = null;
                    }else {
                        this.sellOrder.setConfirmed(true);
                    }

                }
                return;
            }
        }
        if (this.buyOrder != null) {
            if (this.buyOrder.getInternalOrderId().equals(id)) {
                if (tag.equals("err")) {
                    String errCode = jsonResponse.getJSONObject("result").getJSONObject("value").getString("code");
                    LOGGER.debug("buy order: " + this.buyOrder.toString());
                    LOGGER.debug("was refused by the server with error code: " + errCode);
                    this.buyOrder = null;
                } else {
                    if (jsonResponse.getJSONObject("result").getString("value").equals("ok")) {
                        //this is the confirm of an update
                        LOGGER.debug("buy order updated in server, order is: " + this.buyOrder.toString());

                    } else {
                        //this is the confirm of an order creation
                        this.buyOrder.setExchangeOrderId(jsonResponse.getJSONObject("result").getLong("value"));
                        LOGGER.debug("buy order was successfully created on server, order is: " + this.buyOrder.toString());
                    }
                    if (this.buyOrder.getRemainingSize().equals(0)){
                        this.buyOrder = null;
                    }else {
                        this.buyOrder.setConfirmed(true);
                    }

                }
            }
            return;
        }
        LOGGER.error("order with id: " + id + " was not matched");

    }

    public List<WebSocketMessage<String>> processErrorMessage(JSONObject jsonResponse) throws JSONException {
        List<WebSocketMessage<String>> ret = new ArrayList<>();
        if (this.buyOrder != null) {
            if (this.buyOrder.getInternalOrderId().equals(jsonResponse.getInt("id"))) {
                LOGGER.warn("Buy order was cancelled due to reason: " + jsonResponse.getJSONObject("result").getJSONObject("value").getString("code"));
//                ret.addAll(this.cancelBuyOrder());
                this.buyOrder = null;
            }
        }
        if (this.sellOrder != null) {
            if (this.sellOrder.getInternalOrderId().equals(jsonResponse.getInt("id"))) {
                LOGGER.warn("Sell order was cancelled due to reason: " + jsonResponse.getJSONObject("result").getJSONObject("value").getString("code"));
//                ret.addAll(this.cancelSellOrder());
                this.sellOrder = null;
            }
        }
        return ret;
    }

    public void setOrderCanceled(int orderId) {
        if (this.buyOrder != null && this.buyOrder.getInternalOrderId().equals(orderId)) {
            this.buyOrder = null;
        }
        if (this.sellOrder != null && this.sellOrder.getInternalOrderId().equals(orderId)) {
            this.sellOrder = null;
        }
    }

    private List<WebSocketMessage<String>> cancelBuyOrder() {
        return this.CreateCancelOrder(this.buyOrder.getExchangeOrderId());
    }

    private List<WebSocketMessage<String>> cancelSellOrder() {
        return this.CreateCancelOrder(this.sellOrder.getExchangeOrderId());
    }

    private List<WebSocketMessage<String>> CreateCancelOrder(Long exchangeOrderId) {
        CancelOrder cancelOrder = new CancelOrder();
        Gson gson = new Gson();
        cancelOrder.setMethod(9);
        this.orderId++;
        cancelOrder.setId(this.orderId);
        cancelOrder.getParams().getData().setMethod("cancelOrder");
        cancelOrder.getParams().getData().setParams(exchangeOrderId);
        String jsonCancelOrder = gson.toJson(cancelOrder);
        List<WebSocketMessage<String>> ret = new ArrayList<>();
        ret.add(new TextMessage(jsonCancelOrder));
        return ret;
    }

    public void setBalanceInitialized(boolean balanceInitialized) {
        this.balanceInitialized = balanceInitialized;
        if (this.orderInitialized && this.balanceInitialized && this.orderFillInitialized && this.positionInitialized && this.riskSettingsInitialized && !this.readyToTrade) {
            this.setReadyToTrade();
        }
    }

    public boolean isOrderInitialized() {
        return orderInitialized;
    }

    public void setOrderInitialized(boolean orderInitialized) {
        this.orderInitialized = orderInitialized;
        if (this.orderInitialized && this.balanceInitialized && this.orderFillInitialized && this.positionInitialized && this.riskSettingsInitialized && !this.readyToTrade) {
            this.setReadyToTrade();
        }
    }

    public boolean isOrderFillInitialized() {
        return orderFillInitialized;

    }

    public void setOrderFillInitialized(boolean orderFillInitialized) {
        this.orderFillInitialized = orderFillInitialized;
        if (this.orderInitialized && this.balanceInitialized && this.orderFillInitialized && this.positionInitialized && this.riskSettingsInitialized && !this.readyToTrade) {
            this.setReadyToTrade();
        }
    }

    public void setPositionInitialized(boolean positionInitialized) {
        this.positionInitialized = positionInitialized;
        if (this.orderInitialized && this.balanceInitialized && this.orderFillInitialized && this.positionInitialized && this.riskSettingsInitialized && !this.readyToTrade) {
            this.setReadyToTrade();
        }
    }

    public boolean isPositionInitialized() {
        return positionInitialized;
    }

    public boolean isRiskSettingsInitialized() {
        return riskSettingsInitialized;
    }

    public void setRiskSettingsInitialized(boolean riskSettingsInitialized) {
        this.riskSettingsInitialized = riskSettingsInitialized;
        if (this.orderInitialized && this.balanceInitialized && this.orderFillInitialized && this.positionInitialized && this.riskSettingsInitialized && !this.readyToTrade) {
            this.setReadyToTrade();
        }
    }

    private void setReadyToTrade() {
        this.readyToTrade = true;
        LOGGER.info("Balance, orders, order fills, position and risk settings information received");
        LOGGER.info("Now bot is ready to start trading");
    }

    public static BigDecimal toBigDecimal(Price p) {
        BigDecimal ret = new BigDecimal(p.getMantissa());
        if (ret.compareTo(BigDecimal.valueOf(0)) == 0) {
            return ret;
        }
        int exponent = (int) (p.getExponent() * -1);
        BigDecimal base = new BigDecimal(10);
        base = base.pow(exponent);
        ret = ret.divide(base);
        return ret;

    }

    public BigDecimal getWallet() {
        return wallet;
    }

    public void setWallet(BigDecimal wallet) {
        this.wallet = wallet;
    }

    public BigDecimal getAvailable() {
        return available;
    }

    public void setAvailable(BigDecimal available) {
        this.available = available;
    }

    public BigDecimal getPositionsMargin() {
        return positionsMargin;
    }

    public void setPositionsMargin(BigDecimal positionsMargin) {
        this.positionsMargin = positionsMargin;
    }

    public BigDecimal getUnrealizedPnl() {
        return unrealizedPnl;
    }

    public void setUnrealizedPnl(BigDecimal unrealizedPnl) {
        this.unrealizedPnl = unrealizedPnl;
    }

    public BigDecimal getOrdersMargin() {
        return ordersMargin;
    }

    public void setOrdersMargin(BigDecimal ordersMargin) {
        this.ordersMargin = ordersMargin;
    }

    public BigDecimal getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(BigDecimal borrowed) {
        this.borrowed = borrowed;
    }

    public OpenOrder getBuyOrder() {
        return buyOrder;
    }

    public void setBuyOrder(OpenOrder buyOrder) {
        this.buyOrder = buyOrder;
    }

    public OpenOrder getSellOrder() {
        return sellOrder;
    }

    public void setSellOrder(OpenOrder sellOrder) {
        this.sellOrder = sellOrder;
    }

    public boolean isBalanceInitialized() {
        return balanceInitialized;
    }

    public void setReadyToTrade(boolean ready) {
        this.readyToTrade = ready;
    }

    public boolean isReadyToTrade() {
        return readyToTrade;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Integer getPositionSize() {
        return positionSize;
    }

    public void setPositionSize(Integer positionSize) {
        this.positionSize = positionSize;
    }

    public BigDecimal getRiskLimit() {
        return riskLimit;
    }

    public void setRiskLimit(BigDecimal riskLimit) {
        this.riskLimit = riskLimit;
    }
}

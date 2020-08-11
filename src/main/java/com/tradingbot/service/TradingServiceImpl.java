package com.tradingbot.service;

import com.google.gson.Gson;
import com.tradingbot.entity.order.OpenOrder;
import com.tradingbot.entity.order.request.neworder.PlaceNewOrder;
import com.tradingbot.entity.order.request.updateorder.ReplaceOrder;
import com.tradingbot.entity.order.response.OrderResponse;
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

    // id to save as internal and match with latter server response
    private int orderId = 100;
    // active buy order
    private OpenOrder buyOrder = null;
    // active sell order
    private OpenOrder sellOrder = null;

    private Long balanceUpdateTime;
    //set true once we receive balance information for first time
    private boolean balanceInitialized;
    //set true once we receive orders information for fist time
    private boolean orderInitialized;
    //set true once we receive orders fill information for fist time
    private boolean orderFillInitialized;

    // will be true once (balanceInitialized && orderInitialized && orderFillInitialized) is true
    private boolean readyToTrade;

    public TradingServiceImpl(Environment env) {
        this.env = env;
    }

    public List<WebSocketMessage<String>> checkForConditionsAndCreateOrder(List<OrderBookItem> asks, List<OrderBookItem> bids) {

        BigDecimal bestBid = bids.stream().map(OrderBookItem::toBigDecimal).max(BigDecimal::compareTo).orElseThrow(NoSuchElementException::new);
        BigDecimal bestAsk = asks.stream().map(OrderBookItem::toBigDecimal).min(BigDecimal::compareTo).orElseThrow(NoSuchElementException::new);

        BigDecimal interest = new BigDecimal(Objects.requireNonNull(env.getProperty("interest")));
        BigDecimal positionInitial = new BigDecimal(Objects.requireNonNull(env.getProperty("position.initial")));
        BigDecimal positionMax = new BigDecimal(Objects.requireNonNull(env.getProperty("position.max")));
        BigDecimal shift = new BigDecimal(Objects.requireNonNull(env.getProperty("shift")));
        BigDecimal average = bestBid.add(bestAsk).divide(new BigDecimal(2), RoundingMode.FLOOR);
        BigDecimal myBuyPrice = average.subtract(interest).add(shift.multiply(positionInitial));
        BigDecimal mySellPrice = average.add(interest).add(shift.multiply(positionInitial));
        Integer quoteSize = Integer.valueOf(Objects.requireNonNull(env.getProperty("quote.size")));

        List<WebSocketMessage<String>> ret = new ArrayList<>();
        if (bestAsk.compareTo(myBuyPrice) < 0) {
            LOGGER.debug("should buy at: " + bestAsk);
            if (this.buyOrder == null) {
                ret.add(this.createBuyOrder(myBuyPrice,quoteSize)) ;
            }else{
                ret.add(this.updateBuyOrder(myBuyPrice,quoteSize)) ;
            }

        } else {
            LOGGER.debug("no buy order created for best buy price at: " + bestBid + "   and order price at: " + myBuyPrice);
        }
        if (bestBid.compareTo(mySellPrice) > 0) {
            LOGGER.debug("should sell at: " + bestBid);
            if (this.sellOrder == null){
                ret.add(this.createSellOrder(mySellPrice,quoteSize));
            }else {
                ret.add(this.updateSellOrder(myBuyPrice,quoteSize)) ;
            }

        } else {
            LOGGER.debug("no sell order created for best sell price at: " + bestAsk + "   and order price at: " + mySellPrice);
        }

        return ret;
    }

    private WebSocketMessage<String> updateSellOrder(BigDecimal price, Integer quoteSize) {
        ReplaceOrder replaceOrder = updateOrder(price,quoteSize,this.sellOrder.getExchangeOrderId());
        Gson gson = new Gson();
        String jsonOrder = gson.toJson(replaceOrder);
        WebSocketMessage<String> message = new TextMessage(jsonOrder);
        LOGGER.info("will update a sell order: " + message.getPayload());
        return message;
    }

    private WebSocketMessage<String> updateBuyOrder(BigDecimal price, Integer quoteSize) {
        ReplaceOrder replaceOrder = updateOrder(price,quoteSize,this.buyOrder.getExchangeOrderId());
        Gson gson = new Gson();
        String jsonOrder = gson.toJson(replaceOrder);
        WebSocketMessage<String> message = new TextMessage(jsonOrder);
        LOGGER.info("will update a buy order: " + message.getPayload());
        return message;
    }

    private ReplaceOrder updateOrder(BigDecimal price, Integer quoteSize, Long exchangeId){
        ReplaceOrder replaceOrder =  new ReplaceOrder();
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
        placeNewOrder.getParams().getData().getParams().setInstrument(1);
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
        sellOrder = new OpenOrder(this.orderId, size, sellPrice);
        LOGGER.info("will create a sell order: " + message.getPayload());
        return message;
    }

    private WebSocketMessage<String> createBuyOrder(BigDecimal buyPrice, Integer size) {
        PlaceNewOrder placeNewOrder = this.createOrder(buyPrice, size);
        placeNewOrder.getParams().getData().getParams().setSide("BUY");
        Gson gson = new Gson();
        String jsonOrder = gson.toJson(placeNewOrder);
        WebSocketMessage<String> message = new TextMessage(jsonOrder);
        buyOrder = new OpenOrder(this.orderId, size, buyPrice);
        LOGGER.info("will create a buy order: " + message.getPayload());
        return message;
    }

    // function to be called from Order Service when a order update arrives
    public void updateOrderStatus(OrderResponse order) {
        switch (order.getStatus()) {
            case "CANCELLED":
                if (order.getSide().equalsIgnoreCase("BUY")) {
                    LOGGER.info("Buy order canceled, internal order is: " + this.buyOrder.toString());
                    this.buyOrder = null;
                } else {
                    LOGGER.info("Sell order canceled, internal order is: " + this.sellOrder.toString());
                    this.sellOrder = null;
                }
                LOGGER.info("Order response from server is: " + order.toString());
                break;
            case "FILLED":
                if (order.getSide().equalsIgnoreCase("BUY")) {
                    LOGGER.info("Buy Order completely filled, internal order is: " + this.buyOrder.toString());
                    this.buyOrder = null;
                } else {
                    LOGGER.info("Sell Order completely filled, internal order is: " + this.sellOrder.toString());
                    this.sellOrder = null;
                }
                LOGGER.info("Order response from server is: " + order.toString());
                break;
            case "PARTIALLY_FILLED":
                LOGGER.info("Order partially filled: " + order.toString());
                if (order.getSide().equalsIgnoreCase("BUY")) {
                    this.buyOrder.setRemainingSize(order.getRemainingSize());
                    this.buyOrder.setUpdateTime(order.getUpdateTime().getSeconds());
                } else {
                    this.sellOrder.setRemainingSize(order.getRemainingSize());
                    this.sellOrder.setUpdateTime(order.getUpdateTime().getSeconds());
                }
                break;
        }
    }

    // read the response from the server to an order creation (tag={ok,err})
    // if confirmed we save the internal id and update the update time
    public void setOrderConfirm(JSONObject jsonResponse) throws JSONException {
        Integer id = jsonResponse.getInt("id");
        String tag = jsonResponse.getJSONObject("result").getString("tag");
        if (this.getSellOrder() != null) {
            if (this.sellOrder.getInternalOrderId().equals(id)) {
                if (tag.equals("err")) {
                    String errCode = jsonResponse.getJSONObject("result").getJSONObject("value").getString("code");
                    LOGGER.error("sell order: " + this.sellOrder.toString());
                    LOGGER.error("was refused by the server with error code: " + errCode);
                    this.sellOrder = null;
                } else {
                    this.sellOrder.setExchangeOrderId(jsonResponse.getJSONObject("result").getLong("value"));
                    this.sellOrder.setUpdateTime(new Date().getTime() / 1000);
                    LOGGER.info("sell order: " + this.sellOrder.toString());
                    LOGGER.info("was successfully created on server");
                }
            } else if (this.buyOrder.getInternalOrderId().equals(id)) {
                if (tag.equals("err")) {
                    String errCode = jsonResponse.getJSONObject("result").getJSONObject("value").getString("code");
                    LOGGER.error("buy order: " + this.buyOrder.toString());
                    LOGGER.error("was refused by the server with error code: " + errCode);
                    this.buyOrder = null;
                } else {
                    this.buyOrder.setExchangeOrderId(jsonResponse.getJSONObject("result").getLong("value"));
                    this.buyOrder.setUpdateTime(new Date().getTime() / 1000);
                    LOGGER.info("buy order: " + this.buyOrder.toString());
                    LOGGER.info("was successfully created on server");
                }
            } else {
                LOGGER.error("order with id: " + id + " was not matched");
            }
        }
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

    public void setBalanceInitialized(boolean balanceInitialized) {
        this.balanceInitialized = balanceInitialized;
        if (this.isOrderInitialized() && this.isBalanceInitialized() && this.orderFillInitialized && !this.readyToTrade) {
            this.setReadyToTrade();
        }
    }

    public boolean isOrderInitialized() {
        return orderInitialized;
    }

    public void setOrderInitialized(boolean orderInitialized) {
        this.orderInitialized = orderInitialized;
        if (this.isOrderInitialized() && this.isBalanceInitialized() && this.orderFillInitialized && !this.readyToTrade) {
            this.setReadyToTrade();
        }
    }

    public Long getBalanceUpdateTime() {
        return balanceUpdateTime;
    }

    public void setBalanceUpdateTime(Long balanceUpdateTime) {
        this.balanceUpdateTime = balanceUpdateTime;

    }

    public boolean isOrderFillInitialized() {
        return orderFillInitialized;

    }

    public void setOrderFillInitialized(boolean orderFillInitialized) {
        this.orderFillInitialized = orderFillInitialized;
        if (this.isOrderInitialized() && this.isBalanceInitialized() && this.orderFillInitialized && !this.readyToTrade) {
            this.setReadyToTrade();
        }
    }

    private void setReadyToTrade() {
        this.readyToTrade = true;
        LOGGER.info("Balance, orders and order fills information received. now system is ready to start trading");
    }

    public boolean isReadyToTrade() {
        return readyToTrade;
    }

}

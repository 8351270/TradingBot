package com.tradingbot.service;

import com.tradingbot.entity.order.OpenOrder;
import com.tradingbot.entity.order.request.Data;
import com.tradingbot.entity.order.request.PlaceNewOrder;
import com.tradingbot.entity.order.response.OrderResponse;
import com.tradingbot.entity.orderbook.OrderBookItem;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    final
    Environment env;

    private BigDecimal position=null;
    private BigDecimal wallet=null;
    private BigDecimal available = null;
    private BigDecimal positionsMargin=null;
    private BigDecimal unrealizedPnl = null;


    private OpenOrder buyOrder;
    private OpenOrder sellOrder;


    private boolean isOrderPlaced = false;

    // in seconds to compare with server time
    private Long updateTime;



    public TradingServiceImpl(Environment env) {
        this.updateTime = new Date().getTime()/1000;
        this.env = env;
    }

    public List<WebSocketMessage<String>> checkForConditions(List<OrderBookItem> asks, List<OrderBookItem> bids) {

        BigDecimal bestBid = bids.stream().map(OrderBookItem::toBigDecimal).max(BigDecimal::compareTo).orElseThrow(NoSuchElementException::new);
        BigDecimal bestAsk = asks.stream().map(OrderBookItem::toBigDecimal).min(BigDecimal::compareTo).orElseThrow(NoSuchElementException::new);

        BigDecimal interest = new BigDecimal(Objects.requireNonNull(env.getProperty("interest")));
        BigDecimal positionInitial = new BigDecimal(Objects.requireNonNull(env.getProperty("position.initial")));
        BigDecimal positionMax = new BigDecimal(Objects.requireNonNull(env.getProperty("position.max")));
        BigDecimal shift = new BigDecimal(Objects.requireNonNull(env.getProperty("shift")));
        BigDecimal average = bestBid.add(bestAsk).divide(new BigDecimal(2), RoundingMode.FLOOR);
        BigDecimal myBuyPrice = average.subtract(interest).add(shift.multiply(positionInitial)) ;
        BigDecimal mySellPrice = average.add(interest).add(shift.multiply(positionInitial));

        if (bestAsk.compareTo(myBuyPrice)<0){
            LOGGER.info("should buy at: " + bestAsk);
            return this.createBuyOrder(myBuyPrice,new BigDecimal(Objects.requireNonNull(env.getProperty("quote.size"))));

        }
        if (bestBid.compareTo(mySellPrice)>0){
            LOGGER.info("should sell at: " + bestBid);
            return this.createSellOrder(mySellPrice,new BigDecimal(Objects.requireNonNull(env.getProperty("quote.size"))));
        }
        LOGGER.info("no order created for best buy price at: " + bestBid + "   and order price at: " + myBuyPrice);
        LOGGER.info("no order created for best sell price at: " + bestAsk + "   and order price at: " + mySellPrice);

        return null;
    }

    private PlaceNewOrder createOrder(BigDecimal buyPrice, BigDecimal size) {

        PlaceNewOrder placeNewOrder = new PlaceNewOrder();
        placeNewOrder.setId(1001);
        placeNewOrder.setMethod(9);
        placeNewOrder.getParams().getData().setMethod("placeOrder");
        //TODO read from properties file
        placeNewOrder.getParams().getData().getParams().setInstrument(1);
        placeNewOrder.getParams().getData().getParams().getPrice().setExponent(0);
        placeNewOrder.getParams().getData().getParams().getPrice().setMantissa(buyPrice.intValue());
        placeNewOrder.getParams().getData().getParams().setSize(size.intValue());
        //TODO read from properties file
        placeNewOrder.getParams().getData().getParams().setType("LIMIT");
        placeNewOrder.getParams().getData().getParams().setTimeInForce("GTC");
        this.isOrderPlaced = true;

        return placeNewOrder;
    }

    private List<WebSocketMessage<String>> createSellOrder(BigDecimal buyPrice, BigDecimal size) {
        PlaceNewOrder placeNewOrder = this.createOrder(buyPrice,size);
        placeNewOrder.getParams().getData().getParams().setSide("SELL");
        WebSocketMessage<String> message = new TextMessage(placeNewOrder.toString());
        List<WebSocketMessage<String>> messages = new ArrayList<>();
        messages.add(message);
        buyOrder = new OpenOrder();
        buyOrder.setTotalSize(size);
        buyOrder.setRemainingSize(size);

        return messages;
    }

    private List<WebSocketMessage<String>> createBuyOrder(BigDecimal buyPrice, BigDecimal size) {
        PlaceNewOrder placeNewOrder = this.createOrder(buyPrice,size);
        placeNewOrder.getParams().getData().getParams().setSide("BUY");
        WebSocketMessage<String> message = new TextMessage(placeNewOrder.toString());
        List<WebSocketMessage<String>> messages = new ArrayList<>();
        messages.add(message);
        sellOrder = new OpenOrder();
        sellOrder.setTotalSize(size);
        sellOrder.setRemainingSize(size);
        return messages;
    }


    public void updateOrderStatus(OrderResponse order) {
        switch (order.getStatus()){
            case "CANCELLED" :
                LOGGER.info("Order canceled: " + order.toString());
                if (order.getSide().equalsIgnoreCase("BUY")){
                    this.buyOrder = null;
                }else {
                    this.sellOrder =null;
                }
                break;
            case "FILLED" :
                LOGGER.info("Order completely filled: " + order.toString());
                break;
            case "PARTIALLY_FILLED" :
                LOGGER.info("Order partially filled: " + order.toString());
                break;


        }
    }

    public boolean isOrderPlaced() {
        return isOrderPlaced;
    }

    public void setOrderPlaced(boolean orderPlaced) {
        isOrderPlaced = orderPlaced;
    }

    public BigDecimal getPosition() {
        return position;
    }

    public void setPosition(BigDecimal position) {
        this.position = position;
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

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
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

}

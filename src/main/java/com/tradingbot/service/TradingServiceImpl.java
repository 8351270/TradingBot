package com.tradingbot.service;

import com.tradingbot.entity.orderbook.OrderBookItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketMessage;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class TradingServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    final
    Environment env;

    public TradingServiceImpl(Environment env) {
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
        BigDecimal mySellPrice = average.add(interest).add(shift.multiply(positionInitial)) ;

        if (bestAsk.compareTo(myBuyPrice)<0){
            LOGGER.info("should buy at: " + bestAsk);
            return this.createBuyOrder(myBuyPrice,new BigDecimal(Objects.requireNonNull(env.getProperty("position"))));

        }
        if (bestBid.compareTo(mySellPrice)>0){
            LOGGER.info("should sell at: " + bestBid);
            return this.createSellOrder(mySellPrice,new BigDecimal(Objects.requireNonNull(env.getProperty("position.max"))));
        }
        LOGGER.info("no order created for best buy price at: " + bestBid + "   and order price at: " + myBuyPrice);
        LOGGER.info("no order created for best sell price at: " + bestAsk + "   and order price at: " + mySellPrice);


//        bestAsk.add(result)
//        current best purchase price + current best sale price) / 2 - interest + shift * position
//        BUY order with the volume specified in the configuration file at a price equal to (current best purchase price + current best sale price) / 2 - interest + shift * position;
//        SELL order with the volume specified in the configuration file at a price equal to (current best purchase price + current best sale price) / 2 + interest + shift * position.
//        env.getProperty("")
//        for (OrderBookItem ask : asks) {
//            LOGGER.info("ask" + ask.toString());
//        }
//        for (OrderBookItem bid : bids) {
//            LOGGER.info("bid" + bid.toString());
//        }
//        LOGGER.info("best ask: " + bestAsk.toString());
//        LOGGER.info("best bid: " + bestBid.toString());
        return null;
    }

    private List<WebSocketMessage<String>> createBuyOrder(BigDecimal buyPrice, BigDecimal bigDecimal) {

        return null;
    }

    private List<WebSocketMessage<String>> createSellOrder(BigDecimal buyPrice, BigDecimal bigDecimal) {
        return null;
    }


}

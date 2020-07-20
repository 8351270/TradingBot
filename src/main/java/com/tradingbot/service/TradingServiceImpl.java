package com.tradingbot.service;

import com.tradingbot.entity.orderBook.OrderBookItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TradingServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    public boolean checkForConditions(List<OrderBookItem> asks,List<OrderBookItem> bids){

        OrderBookItem bestBid = bids.stream().min(Comparator.comparing(OrderBookItem::getMantissa)).orElseThrow(NoSuchElementException::new);
        OrderBookItem bestAsk = asks.stream().max(Comparator.comparing(OrderBookItem::getMantissa)).orElseThrow(NoSuchElementException::new);
        for (OrderBookItem ask : asks) {
            LOGGER.info("ask" + ask.toString());
        }
        for (OrderBookItem bid : bids) {
            LOGGER.info("bid" + bid.toString());
        }
        LOGGER.info("best ask: " + bestAsk.toString());
        LOGGER.info("best bid: " + bestBid.toString());
        return false;
    }

}

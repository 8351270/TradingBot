package com.tradingbot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionServiceImpl {

    //TODO keep all the active subscriptions

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    public List<WebSocketMessage<String>> initialSubscriptions() {

       return this.getPublicSubscriptions();
    }

    public List<WebSocketMessage<String>> getPublicSubscriptions() {

        List<WebSocketMessage<String>> ret = new ArrayList<>();


        //TODO NOT WORKING on UAT
        WebSocketMessage<String> orderBook = new TextMessage("{\"method\":1,\"params\":{\"channel\":\"orderbook\"},\"id\":1}");
        ret.add(orderBook);
        // Supports few data periods: 1 and 60 minutes and 1 day.
//        WebSocketMessage<String> candles = new TextMessage("{\"method\":1,\"params\":{\"channel\":\"candles:1:D\"},\"id\":3}");
//        ret.add(candles);
//        WebSocketMessage<String> tickers = new TextMessage("{\"method\":1,\"params\":{\"channel\":\"tickers\"},\"id\":4}");
//        ret.add(tickers);
//        WebSocketMessage<String> instruments = new TextMessage("{\"method\":1,\"params\":{\"channel\":\"instruments\"},\"id\":5}");
//        ret.add(instruments);

        // TODO NOT WORKING (it will close connection)
//        WebSocketMessage<String> lastTrades = new TextMessage("{\"method\":1,\"params\":{\"channel\":\"lasttrades\"},\"id\":2}");
//        ret.add(lastTrades);
        return ret;
    }

}
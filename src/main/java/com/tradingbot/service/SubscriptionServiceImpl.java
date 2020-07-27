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

    public List<WebSocketMessage<String>> getPublicSubscriptions() {

        List<WebSocketMessage<String>> ret = new ArrayList<>();

        //TODO NOT WORKING on UAT
        WebSocketMessage<String> orderBook = new TextMessage("{\"method\":1,\"params\":{\"channel\":\"orderbook\"},\"id\":1}");
//        ret.add(orderBook);
        //TODO NOT WORKING (it will close connection)
        WebSocketMessage<String> lastTrades = new TextMessage("{\"method\":1,\"params\":{\"channel\":\"lasttrades\"},\"id\":2}");
        ret.add(lastTrades);

//      Supports few data periods: 1 and 60 minutes and 1 day.
        WebSocketMessage<String> candles = new TextMessage("{\"method\":1,\"params\":{\"channel\":\"candles:1:D\"},\"id\":3}");
        ret.add(candles);
        WebSocketMessage<String> tickers = new TextMessage("{\"method\":1,\"params\":{\"channel\":\"tickers\"},\"id\":4}");
        ret.add(tickers);
        WebSocketMessage<String> instruments = new TextMessage("{\"method\":1,\"params\":{\"channel\":\"instruments\"},\"id\":5}");
        ret.add(instruments);
        WebSocketMessage<String> orders = new TextMessage("{\"method\":1,\"params\":{\"channel\":\"orders\"},\"id\":6}");
        ret.add(orders);
        WebSocketMessage<String> orderFills = new TextMessage("{\"method\":1,\"params\":{\"channel\":\"orderFills\"},\"id\":7}");
        ret.add(orderFills);
        WebSocketMessage<String> balance = new TextMessage("{\"method\":1,\"params\":{\"channel\":\"balance\"},\"id\":8}");
        ret.add(balance);

        // NOT WORKING
        WebSocketMessage<String> riskSettings = new TextMessage("{\"method\":1,\"params\":{\"riskSettings\":\"instruments\"},\"id\":9}");
//        ret.add(riskSettings);

        for (WebSocketMessage<String> m : ret){
            LOGGER.info("Subscribing to channel: "+ m.getPayload());
        }

        return ret;
    }

}
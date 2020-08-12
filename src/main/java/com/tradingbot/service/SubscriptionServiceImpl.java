package com.tradingbot.service;

import com.tradingbot.entity.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionServiceImpl {

    private final List<Channel> publicChannels;

    private final List<Channel> privateChannels;

    public SubscriptionServiceImpl() {
        this.publicChannels = new ArrayList<>();
        this.privateChannels = new ArrayList<>();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    public List<WebSocketMessage<String>> getPrivateSubscriptions() {

        List<WebSocketMessage<String>> ret = new ArrayList<>();
        Channel ch;
        ch = new Channel(6,"orders");
        privateChannels.add(ch);
        ch = new Channel(7,"orderFills");
        privateChannels.add(ch);
        ch = new Channel(8,"balance");
        privateChannels.add(ch);
//        ch = new Channel(9,"positions");
//        privateChannels.add(ch);
//        ch = new Channel(10,"riskSettings");
//        privateChannels.add(ch);

        for (Channel channel:privateChannels) {
            WebSocketMessage<String> subsMessage = new TextMessage(channel.toSubscribeString());
            LOGGER.info("Subscribing to channel: "+ channel.getName());
            ret.add(subsMessage);
        }
        return ret;
    }

    public List<WebSocketMessage<String>> getPublicSubscriptions() {

        List<WebSocketMessage<String>> ret = new ArrayList<>();
        Channel ch = new Channel(1,"orderbook");
        publicChannels.add(ch);
//        ch = new Channel(2,"lasttrades");
//        publicChannels.add(ch);
//        ch = new Channel(3,"candles");
//        publicChannels.add(ch);
//        ch = new Channel(4,"tickers");
//        publicChannels.add(ch);
//        ch = new Channel(5,"instruments");
//        publicChannels.add(ch);

        for (Channel channel:publicChannels) {
            WebSocketMessage<String> subsMessage = new TextMessage(channel.toSubscribeString());
            LOGGER.info("Subscribing to channel: "+ channel.getName());
            ret.add(subsMessage);
        }
        return ret;
    }

    public void confirmSubscription(int id){

        Optional<Channel> ch = publicChannels.stream().filter(x -> x.getId()==id).findFirst();
        if (ch.isEmpty()){
            ch = privateChannels.stream().filter(x -> x.getId()==id).findFirst();
            if (ch.isEmpty()){
                LOGGER.error("Error confirming subscriptions");
                return;
            }
        }
        ch.get().setSubscribed(true);
        LOGGER.info("Successfully subscribed to channel: " + ch.get().getName());
    }

//    public List<WebSocketMessage<String>> unsubscribe(int id){
//        Optional<Channel> ch = publicChannels.stream().filter(x -> x.getId()==id).findFirst();
//        if (ch.isEmpty()){
//            ch = privateChannels.stream().filter(x -> x.getId()==id).findFirst();
//            if (ch.isEmpty()){
//                LOGGER.error("Error confirming subscriptions");
//                return null;
//            }
//        }
//        ch.get().setSubscribed(false);
//        WebSocketMessage<String> subsMessage = new TextMessage(ch.get().toSubscribeString());
//        LOGGER.info("Unsubscribing from channel: "+ ch.get().getName());
//        List<WebSocketMessage<String>> ret = new ArrayList<>();
//        ret.add(subsMessage);
//        return ret;
//    }
//

}
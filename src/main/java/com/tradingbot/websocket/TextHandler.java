package com.tradingbot.websocket;

import com.tradingbot.service.OrderBookServiceImpl;
import com.tradingbot.service.SubscriptionServiceImpl;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.List;

@Component
public class TextHandler extends TextWebSocketHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TextWebSocketHandler.class);

    final
    SubscriptionServiceImpl subscriptionService;

    final
    OrderBookServiceImpl orderBookService;

    public TextHandler(SubscriptionServiceImpl subscriptionService, OrderBookServiceImpl orderBookService) {
        this.orderBookService = orderBookService;
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        LOGGER.info("Connection established");
        List<WebSocketMessage<String>> messages = this.subscriptionService.initialSubscriptions();
        for (WebSocketMessage<String> message : messages){
            session.sendMessage(message);
        }
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

        if (message instanceof TextMessage) {
            this.handleTextMessage(session, (TextMessage)message);
        } else if (message instanceof PongMessage) {
            this.handlePongMessage(session, (PongMessage)message);
        } else {
                throw new IllegalStateException("Unexpected WebSocket message type: " + message);
            }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        LOGGER.debug("message received: " + message.getPayload());
        JSONObject jsonResponse = new JSONObject(message.getPayload());
        String aux = jsonResponse.getString("result");
        if (aux.equals("{}")){
            String id = jsonResponse.getString("id");
            LOGGER.info("Successfully subscribed to channel with id:" +id );
        }else {
            if (jsonResponse.getJSONObject("result").has("tag")){
                String tag = jsonResponse.getJSONObject("result").getString("tag");
                if (tag.equals("err")){
                    LOGGER.error("Error message received:" + jsonResponse.toString());
                }
            }
            String channel = jsonResponse.getJSONObject("result").getString("channel");
            LOGGER.debug("message received from channel: " + channel);
            if (channel.equals("orderbook")){
                this.orderBookService.processOrderBook(jsonResponse);
            }

        }
    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        LOGGER.info(message.toString());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        LOGGER.info("handleTransportError " + exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        LOGGER.info("connection closed");
    }
}

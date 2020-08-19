package com.tradingbot.websocket;

import com.tradingbot.service.*;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;

@Component
public class WebSocketHandler extends TextWebSocketHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketHandler.class);

    // channels subscription service
    final SubscriptionServiceImpl subscriptionService;
    // authentication service
    final AuthenticationServiceImpl authenticationService;
    // resolve messages for all channels except from the previous ones
    final MessageResolverServiceImpl messageResolverService;


    public WebSocketHandler(MessageResolverServiceImpl messageResolverService, AuthenticationServiceImpl authenticationService, SubscriptionServiceImpl subscriptionService) {
        this.messageResolverService = messageResolverService;
        this.authenticationService = authenticationService;
        this.subscriptionService = subscriptionService;
    }

    public void onShutDown(WebSocketSession session) throws IOException, InterruptedException {
        this.messageResolverService.cancelAllOrders(session);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        LOGGER.info("Connection established with server");
        WebSocketMessage<String> auth = this.authenticationService.authentication();
        List<WebSocketMessage<String>> subs = subscriptionService.getPublicSubscriptions();
        for (WebSocketMessage<String> m : subs) {
            session.sendMessage(m);
        }
        session.sendMessage(auth);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

        if (message instanceof TextMessage) {
            this.handleTextMessage(session, (TextMessage) message);
        } else if (message instanceof PongMessage) {
            this.handlePongMessage(session, (PongMessage) message);
        } else {
            throw new IllegalStateException("Unexpected message type: " + message);
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {

        LOGGER.debug("message received: " + message.getPayload());
        JSONObject jsonResponse = new JSONObject(message.getPayload());
        List<WebSocketMessage<String>> messages = this.messageResolverService.processMessage(jsonResponse);
        if (messages != null && !messages.isEmpty()) {
            for (WebSocketMessage<String> m : messages) {
                session.sendMessage(m);
            }
        }
    }

    @Override
    protected void handlePongMessage(WebSocketSession session, PongMessage message) throws Exception {
        LOGGER.debug("pong message received " + message.getPayload());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        LOGGER.error("handleTransportError " + exception.getMessage());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        LOGGER.error("connection closed");
    }

    public void sendPingMessage(WebSocketSession session) throws IOException {
        WebSocketMessage<String> ping = new TextMessage("{ping}");
        session.sendMessage(ping);
    }

}

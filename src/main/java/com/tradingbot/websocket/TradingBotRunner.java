package com.tradingbot.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.WebSocketSession;

public class TradingBotRunner implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(TradingBotRunner.class);

    final WebsocketConnection websocketConnectionService;
    WebSocketSession webSocketSession;

    TradingBotRunner(WebsocketConnection websocketConnectionService) {
        this.websocketConnectionService = websocketConnectionService;
    }

    private void createConnection() {
        this.webSocketSession = this.websocketConnectionService.connect();
        // the message size will exceed the default value and close connection
        webSocketSession.setTextMessageSizeLimit(1000000);
        LOGGER.info("Connected to websocket");
    }

    @Override
    public void run() {
        this.createConnection();
    }

    @Scheduled(fixedDelay = 5000)
    private void checkConnection() {
        if (this.webSocketSession == null || !this.webSocketSession.isOpen()) {
            LOGGER.error("Connection was lost, will attempt to reconnect");
            this.createConnection();
        }
    }

}

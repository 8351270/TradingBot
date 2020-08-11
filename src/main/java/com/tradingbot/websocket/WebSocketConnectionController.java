package com.tradingbot.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;

@Component
public class WebSocketConnectionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketConnectionController.class);

    final WebsocketConnection websocketConnectionService;

    WebSocketSession webSocketSession ;

    public WebSocketConnectionController(WebsocketConnection websocketConnectionService){
        this.websocketConnectionService = websocketConnectionService;
    }

    @PostConstruct
    private void init (){
        this.CreateConnection();
    }

    @Scheduled(fixedDelay = 5000)
    private void checkConnection(){
        if (this.webSocketSession==null || !this.webSocketSession.isOpen()){
            LOGGER.error("Connection was lost, will attempt to reconnect");
            this.init();
        }
    }

    private void CreateConnection() {
        this.webSocketSession = this.websocketConnectionService.connect();
        webSocketSession.setTextMessageSizeLimit(1000000);
    }

}
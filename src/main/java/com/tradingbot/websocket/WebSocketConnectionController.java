package com.tradingbot.websocket;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;

// MAY BE A REST CONTROLLER IN THE FUTURE
@Controller
public class WebSocketConnectionController {

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
        if (!this.webSocketSession.isOpen()){
            this.init();
        }
    }

    private void CreateConnection() {
        this.webSocketSession = this.websocketConnectionService.connect();
    }

}
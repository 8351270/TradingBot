package com.tradingbot.websocket;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class WebSocketConnectionHandler {

    final WebsocketConnection websocketConnectionService;

    public WebSocketConnectionHandler(WebsocketConnection websocketConnectionService){
        this.websocketConnectionService = websocketConnectionService;
    }

    @PostConstruct
    public void run() {
        // run everything on a single thread
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(new TradingBotRunner(this.websocketConnectionService));
    }

}
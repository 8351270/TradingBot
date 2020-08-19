package com.tradingbot.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.concurrent.*;

@Component
public class WebSocketConnectionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketConnectionHandler.class);

    final WebsocketConnection websocketConnectionService;

    private TradingBotRunner runner;

    private ExecutorService executor;

    public WebSocketConnectionHandler(WebsocketConnection websocketConnectionService) {
        this.websocketConnectionService = websocketConnectionService;
    }

    @PostConstruct
    public void run() {
        this.executor = Executors.newSingleThreadExecutor();
        this.runner = new TradingBotRunner(this.websocketConnectionService);
        executor.execute(runner);
    }

    @PreDestroy
    public void destroy() throws IOException, InterruptedException {
        runner.websocketConnectionService.destroy();
    }

    @Scheduled(initialDelay = 20000, fixedDelay = 2000)
    private void checkConnection() throws InterruptedException, IOException {
        if (!this.runner.webSocketSession.isOpen()) {
            executor.shutdown();
            executor.awaitTermination(500, TimeUnit.MILLISECONDS);
            this.run();
        }
    }

}
package com.tradingbot.service;

import com.tradingbot.websocket.TextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.*;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import javax.annotation.PostConstruct;
import java.net.URI;

@Service
public class WebsocketConnectionServiceImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketConnectionServiceImpl.class);

    final
    TextHandler sessionHandler;

    final
    Environment environment;

    public WebsocketConnectionServiceImpl(TextHandler sessionHandler, Environment env) {
        this.sessionHandler = sessionHandler;
        this.environment = env;
    }

    @PostConstruct
    public void tryRequest(){
        this.connect();
    }

    public void connect() {
        try {
            WebSocketClient webSocketClient = new StandardWebSocketClient();
            WebSocketHttpHeaders handshakeHeaders = new WebSocketHttpHeaders();
            String headerOrigin = this.environment.getProperty("api.origin");
            String url = this.environment.getProperty("api.url");
            assert url != null;
            URI uri =URI.create(url);

            handshakeHeaders.setOrigin(headerOrigin);
            handshakeHeaders.set("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36");
            WebSocketSession webSocketSession = webSocketClient.doHandshake(
                    sessionHandler, handshakeHeaders, uri).get();
        } catch (Exception e) {
            LOGGER.error("Exception while accessing websocket", e);
        }
    }
}
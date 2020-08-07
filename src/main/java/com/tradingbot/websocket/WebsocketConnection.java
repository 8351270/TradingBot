package com.tradingbot.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.net.URI;

@Component
public class WebsocketConnection {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketConnection.class);

    final
    WebSocketHandler sessionHandler;

    final
    Environment environment;

    public WebsocketConnection(WebSocketHandler sessionHandler, Environment env) {
        this.sessionHandler = sessionHandler;
        this.environment = env;
    }

    public WebSocketSession connect() {
        try {
            WebSocketClient webSocketClient = new StandardWebSocketClient();
            WebSocketHttpHeaders handshakeHeaders = new WebSocketHttpHeaders();
            String headerOrigin = this.environment.getProperty("api.origin");
            String url = this.environment.getProperty("api.url");
            assert url != null;
            URI uri =URI.create(url);

            handshakeHeaders.setOrigin(headerOrigin);
            handshakeHeaders.set("User-Agent", "Node.JS MM Bot Example");
//            Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36
            return webSocketClient.doHandshake(
                    sessionHandler, handshakeHeaders, uri).get();

        } catch (Exception e) {
            LOGGER.error("Exception while accessing websocket", e);
        }
        return null;
    }
}
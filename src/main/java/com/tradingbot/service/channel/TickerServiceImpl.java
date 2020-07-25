package com.tradingbot.service.channel;

import com.google.gson.Gson;
import com.tradingbot.entity.authentication.response.AuthenticationResponse;
import com.tradingbot.entity.tickers.TickersResponse;
import com.tradingbot.service.MessageProcessingI;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketMessage;

import java.util.List;

@Service
public class TickerServiceImpl implements MessageProcessingI {

    @Override
    public List<WebSocketMessage<String>> processMessage(JSONObject jsonResponse) {
        Gson g = new Gson();
        TickersResponse ar = g.fromJson(jsonResponse.toString(), TickersResponse.class);
        return null;
    }
}

package com.tradingbot.service.channel;

import com.google.gson.Gson;
import com.tradingbot.entity.lasttrades.LastTradesResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketMessage;

import java.util.List;

@Service
public class LastTradesServiceImpl implements MessageProcessingI {

    @Override
    public List<WebSocketMessage<String>> processMessage(JSONObject jsonResponse) {

        Gson g = new Gson();
//        LastTradesResponse lastTradesResponse = g.fromJson(jsonResponse.toString(), LastTradesResponse.class);
        return null;
    }
}

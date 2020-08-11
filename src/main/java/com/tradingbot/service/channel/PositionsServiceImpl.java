package com.tradingbot.service.channel;

import com.google.gson.Gson;
import com.tradingbot.entity.positions.PositionsResponse;
import com.tradingbot.entity.risksettings.RiskSettingsResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketMessage;

import java.util.List;

@Service
public class PositionsServiceImpl implements MessageProcessingI {

    @Override
    public List<WebSocketMessage<String>> processMessage(JSONObject jsonResponse) {

        Gson g = new Gson();
        PositionsResponse positionsResponse = g.fromJson(jsonResponse.toString(),PositionsResponse.class);
        return null;
    }
}

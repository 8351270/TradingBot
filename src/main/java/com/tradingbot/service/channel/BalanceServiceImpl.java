package com.tradingbot.service.channel;

import com.google.gson.Gson;
import com.tradingbot.entity.balance.BalanceResponse;
import com.tradingbot.service.MessageProcessingI;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketMessage;

import java.util.List;

@Service
public class BalanceServiceImpl implements MessageProcessingI {

    private static final Logger LOGGER = LoggerFactory.getLogger(BalanceServiceImpl.class);

    @Override
    public List<WebSocketMessage<String>> processMessage(JSONObject jsonResponse) {

        Gson g = new Gson();
        BalanceResponse balanceResponse = g.fromJson(jsonResponse.toString(), BalanceResponse.class);
        return null;
    }
}
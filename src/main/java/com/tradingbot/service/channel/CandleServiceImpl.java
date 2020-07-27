package com.tradingbot.service.channel;

import com.google.gson.Gson;
import com.tradingbot.entity.candles.CandlesResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketMessage;

import java.util.List;

@Service
public class CandleServiceImpl extends AbstractChannelService<CandlesResponse>  implements MessageProcessingI {

    private static final Logger LOGGER = LoggerFactory.getLogger(CandleServiceImpl.class);

    @Override
    public List<WebSocketMessage<String>> processMessage(JSONObject jsonResponse) {

        Gson g = new Gson();
        CandlesResponse candlesResponse = g.fromJson(jsonResponse.toString(), CandlesResponse.class);
        return null;
    }
}

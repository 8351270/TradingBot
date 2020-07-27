package com.tradingbot.service.channel;

import com.google.gson.Gson;
import com.tradingbot.entity.tickers.TickersResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketMessage;

import java.util.List;

@Service
public class TickerServiceImpl extends AbstractChannelService<TickersResponse>  implements MessageProcessingI {

    @Override
    public List<WebSocketMessage<String>> processMessage(JSONObject jsonResponse) {
        Gson g = new Gson();
        TickersResponse tickersResponse = g.fromJson(jsonResponse.toString(), TickersResponse.class);
        this.addElement(tickersResponse);
        return null;
    }


}

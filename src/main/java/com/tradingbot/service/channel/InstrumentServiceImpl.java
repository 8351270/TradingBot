package com.tradingbot.service.channel;

import com.google.gson.Gson;
import com.tradingbot.entity.instruments.InstrumentsResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketMessage;

import java.util.List;

@Service
public class InstrumentServiceImpl implements MessageProcessingI {



    @Override
    public List<WebSocketMessage<String>> processMessage(JSONObject jsonResponse) {

        Gson g = new Gson();
        InstrumentsResponse instrumentsResponse = g.fromJson(jsonResponse.toString(), InstrumentsResponse.class);
        return null;
    }

    //"id": 1,
    //"symbol": "BTCUSD",
    //"currency": "USD",
    //"settlementCurrency": "BTC",
    //"id": 2,
    //"symbol": "ETHUSD",
    //"currency": "USD",
    //"settlementCurrency": "BTC",
    //"id": 3,
    //"symbol": "ETHBTC",
    //"currency": "BTC",
    //"settlementCurrency": "BTC",


}

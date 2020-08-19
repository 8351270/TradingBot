package com.tradingbot.service.channel;

import com.google.gson.Gson;
import com.tradingbot.entity.positions.Positions;
import com.tradingbot.entity.positions.inner.Payload;
import com.tradingbot.service.TradingServiceImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PositionsServiceImpl implements MessageProcessingI {

    private static final Logger LOGGER = LoggerFactory.getLogger(PositionsServiceImpl.class);

    final TradingServiceImpl tradingService;

    final Environment env;

    public PositionsServiceImpl(TradingServiceImpl tradingService, Environment env) {
        this.tradingService = tradingService;
        this.env = env;
    }


    @Override
    public List<WebSocketMessage<String>> processMessage(JSONObject jsonResponse) {


        try {
            String type = jsonResponse.getJSONObject("result").getJSONObject("data").getJSONObject("value").getString("type");
            JSONObject positionsObject = jsonResponse.getJSONObject("result").getJSONObject("data").getJSONObject("value");
            String payloadString = positionsObject.getString("payload");
            if ("{}".equals(payloadString)) {
                this.tradingService.setPositionInitialized(true);
                this.tradingService.setPositionSize(0);
                return null;
            }
            positionsObject = positionsObject.getJSONObject("payload");
            ArrayList<Payload> payloadList = new ArrayList<>();
            Gson g = new Gson();
            if (type.equals("snapshot")) {
                JSONArray jsonArray = positionsObject.toJSONArray(positionsObject.names());
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Payload payload = g.fromJson((jsonArray).getString(i), Payload.class);
                        if (payload.getInstrumentId() == Integer.parseInt(Objects.requireNonNull(env.getProperty("instrument.code")))) {
                            payloadList.add(payload);
                        }
                    }
                }
                // is update
            } else {
                Payload position = g.fromJson(String.valueOf(positionsObject), Payload.class);
                payloadList.add(position);
            }
            if (!payloadList.isEmpty()) {
                Positions positions = new Positions();
                positions.setType(type);
                positions.setPayload(payloadList);
                Payload p = positions.getPayload().get(0);
                if (type.equals("update")) {
                    if (!this.tradingService.getPositionSize().equals(p.getSize())){
                        this.tradingService.setPositionSize(p.getSize());
                        LOGGER.info("position size updated, new value is: " + p.getSize());
                    }
                }else {
                    this.tradingService.setPositionSize(p.getSize());
                    LOGGER.info("initial position size is: " + p.getSize());
                }
            }
            this.tradingService.setPositionInitialized(true);

        } catch (JSONException e) {
            LOGGER.error("error while parsing positions JSON message, JSON object is: " + jsonResponse.toString());
        }

        return null;
    }
}

package com.tradingbot.service.channel;

import com.google.gson.Gson;
import com.tradingbot.entity.risksettings.inner.Payload;
import com.tradingbot.entity.risksettings.RiskSettings;
import com.tradingbot.service.TradingServiceImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketMessage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class RiskSettingsServiceImpl implements MessageProcessingI {


    final
    TradingServiceImpl tradingService;

    @Autowired
    Environment env;

    public RiskSettingsServiceImpl(TradingServiceImpl tradingService) {
        this.tradingService = tradingService;
    }

    @Override
    public List<WebSocketMessage<String>> processMessage(JSONObject jsonResponse) {

        Gson g = new Gson();
        try {
            String tag = jsonResponse.getJSONObject("result").getJSONObject("data").getString("tag");
            if (tag.equals("ok")) {
                JSONObject riskSettingsJsonObj = jsonResponse.getJSONObject("result").getJSONObject("data").getJSONObject("value");
                String type = riskSettingsJsonObj.getString("type");
                riskSettingsJsonObj = riskSettingsJsonObj.getJSONObject("payload");
                RiskSettings riskSettings = new RiskSettings();
                ArrayList<Payload> payloadList = new ArrayList<>();
                if (type.equals("snapshot")) {
                    JSONArray jsonArray = riskSettingsJsonObj.toJSONArray(riskSettingsJsonObj.names());
                    if (jsonArray != null) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            String s = (jsonArray).getJSONObject(i).toString();
                            Payload payload = g.fromJson(s, Payload.class);
                            if (payload.getInstrumentId() == Integer.parseInt(Objects.requireNonNull(env.getProperty("instrument.code")))) {
                                payloadList.add(payload);
                            }
                        }
                    }
                    if (!this.tradingService.isRiskSettingsInitialized()) {
                        this.tradingService.setRiskSettingsInitialized(true);
                    }
                } else {
                    Payload p = g.fromJson(String.valueOf(riskSettingsJsonObj), Payload.class);
                    payloadList.add(p);
                }
                riskSettings.setPayload(payloadList);

                for (Payload p : riskSettings.getPayload()) {
                    tradingService.setRiskLimit(toBigDecimal(p.getRiskLimit().getMantissa(), p.getRiskLimit().getExponent()));
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    // TODO make a util class with this
    public static BigDecimal toBigDecimal(Long mantissa, Long exponent) {
        BigDecimal ret = new BigDecimal(mantissa);
        if (ret.compareTo(BigDecimal.valueOf(0)) == 0) {
            return ret;
        }
        exponent = exponent * -1;
        BigDecimal base = new BigDecimal(10);
        base = base.pow(Math.toIntExact(exponent));
        ret = ret.divide(base);
        return ret;

    }
}
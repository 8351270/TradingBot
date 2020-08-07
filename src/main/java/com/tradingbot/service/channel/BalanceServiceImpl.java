package com.tradingbot.service.channel;

import com.google.gson.Gson;
import com.tradingbot.entity.balance.BalanceResponse;
import com.tradingbot.entity.orderbook.OrderBookItem;
import com.tradingbot.service.TradingServiceImpl;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketMessage;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;

@Service
public class BalanceServiceImpl extends AbstractChannelService<BalanceResponse>  implements MessageProcessingI {

    private static final Logger LOGGER = LoggerFactory.getLogger(BalanceServiceImpl.class);

    private final TradingServiceImpl tradingService;

    public BalanceServiceImpl(TradingServiceImpl tradingService) {
        this.tradingService = tradingService;
    }

    @Override
    public List<WebSocketMessage<String>> processMessage(JSONObject jsonResponse) {

        Gson g = new Gson();
        try {
            JSONObject jsonObject =null;
            if (jsonResponse.getJSONObject("result").getJSONObject("data").getJSONObject("value").getJSONObject("payload").has("BTC")){
                jsonObject =jsonResponse.getJSONObject("result").getJSONObject("data").getJSONObject("value").getJSONObject("payload").getJSONObject("BTC");
            }else {
                jsonObject =jsonResponse.getJSONObject("result").getJSONObject("data").getJSONObject("value").getJSONObject("payload");
            }

            BigDecimal ordersMargin = toBigDecimal(jsonObject.getJSONObject("ordersMargin"));
            BigDecimal wallet = toBigDecimal(jsonObject.getJSONObject("wallet"));
            BigDecimal positionsMargin = toBigDecimal(jsonObject.getJSONObject("positionsMargin"));
            BigDecimal unrealizedPnl = toBigDecimal(jsonObject.getJSONObject("unrealizedPnl"));
            BigDecimal available = toBigDecimal(jsonObject.getJSONObject("available"));
            String currency = jsonObject.getString("currency");
            Long updateTime = jsonObject.getJSONObject("updateTime").getLong("seconds");
            BigDecimal borrowed = toBigDecimal(jsonObject.getJSONObject("borrowed"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BigDecimal toBigDecimal(JSONObject item) throws JSONException {
        BigDecimal ret = new BigDecimal(item.getLong("mantissa"));
        if (ret.compareTo(BigDecimal.valueOf(0))==0){
            return ret;
        }
        int exponent = item.getInt("exponent") * -1;
        BigDecimal base = new BigDecimal(10);
        base = base.pow(exponent);
        ret = ret.divide(base);
        return ret;

    }
}

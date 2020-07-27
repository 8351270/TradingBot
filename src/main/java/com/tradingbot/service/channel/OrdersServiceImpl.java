package com.tradingbot.service.channel;

import com.google.gson.Gson;
import com.tradingbot.entity.order.OrdersResponse;
import com.tradingbot.entity.risksettings.RiskSettingsResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketMessage;

import java.util.List;

@Service
public class OrdersServiceImpl extends AbstractChannelService<RiskSettingsResponse>  implements MessageProcessingI  {


    @Override
    public List<WebSocketMessage<String>> processMessage(JSONObject jsonResponse){
        Gson g = new Gson();
        OrdersResponse ordersResponse = g.fromJson(jsonResponse.toString(), OrdersResponse.class);
        return null;
    }

}

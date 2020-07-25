package com.tradingbot.service.channel;

import com.google.gson.Gson;
import com.tradingbot.entity.order.OrdersResponse;
import com.tradingbot.entity.orderfils.OrderFillsResponse;
import com.tradingbot.service.MessageProcessingI;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketMessage;

import java.util.List;

@Service
public class OrdersServiceImpl implements MessageProcessingI {

    public void processOrders(JSONObject jsonResponse) {
    }

    @Override
    public List<WebSocketMessage<String>> processMessage(JSONObject jsonResponse){
        Gson g = new Gson();
        OrdersResponse ordersResponse = g.fromJson(jsonResponse.toString(), OrdersResponse.class);
        return null;
    }

}

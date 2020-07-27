package com.tradingbot.service.channel;

import com.google.gson.Gson;
import com.tradingbot.entity.orderfils.OrderFillsResponse;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketMessage;

import java.util.List;

@Service
public class OrderFillsServiceImpl extends AbstractChannelService<OrderFillsResponse>  implements MessageProcessingI  {



    @Override
    public List<WebSocketMessage<String>> processMessage(JSONObject jsonResponse) {

        Gson g = new Gson();
        OrderFillsResponse orderFillsResponse = g.fromJson(jsonResponse.toString(), OrderFillsResponse.class);
        this.addElement(orderFillsResponse);
        return null;

    }

}

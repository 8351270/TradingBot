package com.tradingbot.service.channel;

import com.google.gson.Gson;
import com.tradingbot.entity.order.response.OrderResponse;
import com.tradingbot.entity.risksettings.RiskSettingsResponse;
import com.tradingbot.service.TradingServiceImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketMessage;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersServiceImpl extends AbstractChannelService<RiskSettingsResponse>  implements MessageProcessingI  {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrdersServiceImpl.class);

    final
    TradingServiceImpl tradingService;

    public OrdersServiceImpl(TradingServiceImpl tradingService) {
        this.tradingService = tradingService;
    }

    @Override
    public List<WebSocketMessage<String>> processMessage(JSONObject jsonResponse){

        try {

            JSONObject isError = jsonResponse.getJSONObject("result").getJSONObject("data");
            if (isError.getString("tag").equalsIgnoreCase("err")){
                String error = isError.getJSONObject("value").getString("code");
                LOGGER.error(error);
                return null;
            }
            JSONObject ordersObject = jsonResponse.getJSONObject("result").getJSONObject("data").getJSONObject("value").getJSONObject("payload");
            JSONArray jsonArray = ordersObject.toJSONArray(ordersObject.names());

            Gson g = new Gson();
            ArrayList<OrderResponse> lastOrders = new ArrayList<>();
            if (jsonArray != null) {
                for (int i = 0; i< jsonArray.length(); i++){
                    OrderResponse order = g.fromJson((jsonArray).getString(i), OrderResponse.class);
                    lastOrders.add(order);
                }
            }
            if (this.tradingService.isOrderPlaced()){
                for (OrderResponse order: lastOrders) {
//                "FILLED", "CANCELLED" or "PARTIALLY_FILLED"
                    Long lastUpdate = this.tradingService.getUpdateTime();
                    if (order.getCreationTime().getSeconds() >= lastUpdate){
                        // ORDER SHOULD BE UPDATED IN
                        this.tradingService.updateOrderStatus(order);
                    }

                }

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}

package com.tradingbot.service.channel;

import com.google.gson.Gson;
import com.tradingbot.entity.orderfils.OrderFillsResponse;
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
public class OrderFillsServiceImpl implements MessageProcessingI {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderFillsServiceImpl.class);

    final
    TradingServiceImpl tradingService;

    final Environment env;

    public OrderFillsServiceImpl(TradingServiceImpl tradingService, Environment env) {
        this.tradingService = tradingService;
        this.env = env;
    }

    @Override
    public List<WebSocketMessage<String>> processMessage(JSONObject jsonResponse) {

        try {
            JSONObject isError = jsonResponse.getJSONObject("result").getJSONObject("data");
            if (isError.getString("tag").equalsIgnoreCase("err")) {
                String error = isError.getJSONObject("value").getString("code");
                LOGGER.error("Error in order fills channel, error code is: " + error);
                return null;
            }
            JSONObject orderFillsObject = jsonResponse.getJSONObject("result").getJSONObject("data").getJSONObject("value");
            String type = orderFillsObject.getString("type");
            // discard the first one with the snapshot
            if (type.equals("snapshot")) {
                if (!this.tradingService.isOrderFillInitialized()) {
                    this.tradingService.setOrderFillInitialized(true);
                }
                return null;
            }
            JSONArray jsonArray = orderFillsObject.toJSONArray(orderFillsObject.names());

            Gson g = new Gson();
            ArrayList<OrderFillsResponse> lastOrderFill = new ArrayList<>();
            if (jsonArray != null) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    String s = jsonArray.getString(i);
                    if (!s.equals("update") && !s.equals("snapshot")){
                        OrderFillsResponse orderFill = g.fromJson((jsonArray).getString(i), OrderFillsResponse.class);
                        //we only work with instrument id 1
                        if (orderFill.getInstrument() == Integer.parseInt(Objects.requireNonNull(env.getProperty("instrument.code")))) {
                            lastOrderFill.add(orderFill);
                        }
                    }

                }
            }

            for (OrderFillsResponse orderFill : lastOrderFill) {
                // this is in case an order was open and the bot went down
                // so the next time bot starts, it won't create any order until checking for open ones
                if (orderFill.getSide().equals("SELL")) {
                    if (this.tradingService.getSellOrder() != null) {
                        if (this.tradingService.getSellOrder().getUpdateTime() <= orderFill.getTime().getSeconds()) {
                            if (tradingService.getSellOrder().getExchangeOrderId() == null) {
                                LOGGER.warn("Order fill received before order confirmation, order confirmation Id should be: " + orderFill.getOrderId());
                                tradingService.getSellOrder().setExchangeOrderId(orderFill.getOrderId());
                            } else
                            if (tradingService.getSellOrder().getExchangeOrderId().equals(orderFill.getOrderId())) {
                                this.tradingService.getSellOrder().setUpdateTime(orderFill.getTime().getSeconds());
                                this.tradingService.getSellOrder().setRemainingSize(orderFill.getRemainingQty());
//                                if (tradingService.getSellOrder().getRemainingSize()==0 && tradingService.getSellOrder().isConfirmed()){
//                                    tradingService.setSellOrder(null);
//                                }
                            }
                        }
                    }
                    if (!this.tradingService.isOrderFillInitialized()) {
                        this.tradingService.setOrderFillInitialized(true);
                    }
                }
                if (orderFill.getSide().equals("BUY")) {
                    if (this.tradingService.getBuyOrder() != null) {
                        if (this.tradingService.getBuyOrder().getUpdateTime() <= orderFill.getTime().getSeconds()) {
                            if (tradingService.getBuyOrder().getExchangeOrderId() == null) {
                                LOGGER.warn("Order fill received before order confirmation, order confirmation Id should be: " + orderFill.getOrderId());
                                tradingService.getBuyOrder().setExchangeOrderId(orderFill.getOrderId());
                            } else
                            if (tradingService.getBuyOrder().getExchangeOrderId().equals(orderFill.getOrderId())) {
                                this.tradingService.getBuyOrder().setUpdateTime(orderFill.getTime().getSeconds());
                                this.tradingService.getBuyOrder().setRemainingSize(orderFill.getRemainingQty());
//                                if (tradingService.getBuyOrder().getRemainingSize()==0){
//                                    tradingService.setBuyOrder(null);
//                                }
                            }
                        }
                    }
                    if (!this.tradingService.isOrderFillInitialized()) {
                        this.tradingService.setOrderFillInitialized(true);
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
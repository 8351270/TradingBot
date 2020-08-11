package com.tradingbot.service.channel;

import com.google.gson.Gson;
import com.tradingbot.entity.order.OpenOrder;
import com.tradingbot.entity.order.response.OrderResponse;
import com.tradingbot.service.TradingServiceImpl;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketMessage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersServiceImpl implements MessageProcessingI {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrdersServiceImpl.class);

    final
    TradingServiceImpl tradingService;

    public OrdersServiceImpl(TradingServiceImpl tradingService) {
        this.tradingService = tradingService;
    }

    @Override
    public List<WebSocketMessage<String>> processMessage(JSONObject jsonResponse) {

        try {
            JSONObject isError = jsonResponse.getJSONObject("result").getJSONObject("data");
            if (isError.getString("tag").equalsIgnoreCase("err")) {
                String error = isError.getJSONObject("value").getString("code");
                LOGGER.error("Error in orders channel, error code is: " + error);
                return null;
            }
            String type = jsonResponse.getJSONObject("result").getJSONObject("data").getJSONObject("value").getString("type");
            JSONObject ordersObject = jsonResponse.getJSONObject("result").getJSONObject("data").getJSONObject("value").getJSONObject("payload");
            ArrayList<OrderResponse> lastOrders = new ArrayList<>();
            Gson g = new Gson();
            if (type.equals("snapshot")){
                JSONArray jsonArray = ordersObject.toJSONArray(ordersObject.names());
                if (jsonArray != null) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        OrderResponse order = g.fromJson((jsonArray).getString(i), OrderResponse.class);
                        //we only work with instrument id 1
                        if (order.getInstrument() == 1) {
                            lastOrders.add(order);
                        }
                    }
                }
                // is update
            }else {
                OrderResponse order = g.fromJson(String.valueOf(ordersObject), OrderResponse.class);
                lastOrders.add(order);
            }

            for (OrderResponse order : lastOrders) {
                // this is in case an order was open and the bot went down
                // so the next time bot starts, it won't create any order until checking for open ones
                if (order.getSide().equals("SELL")) {
                    if (this.tradingService.getSellOrder() == null && order.getStatus().equals("PARTIALLY_FILLED")) {
                        OpenOrder oo = new OpenOrder();
                        oo.setUpdateTime(order.getUpdateTime().getSeconds());
                        oo.setExchangeOrderId(order.getId());
                        // in this case we don't have the real id
                        oo.setInternalOrderId(0);
                        oo.setRemainingSize(order.getRemainingSize());
                        oo.setTotalSize((order.getInitialSize()));
                        this.tradingService.setSellOrder(oo);
                    } else if (this.tradingService.getSellOrder() != null) {
                        if (this.tradingService.getSellOrder().getUpdateTime() <= order.getUpdateTime().getSeconds()) {
                            if (tradingService.getSellOrder().getExchangeOrderId().equals(order.getId())) {
                                this.tradingService.updateOrderStatus(order);
                            }
                        }
                    }
                }
                if (order.getSide().equals("BUY")) {
                    if (this.tradingService.getBuyOrder() == null && order.getStatus().equals("PARTIALLY_FILLED")) {
                        OpenOrder oo = new OpenOrder();
                        oo.setUpdateTime(order.getUpdateTime().getSeconds());
                        oo.setExchangeOrderId(order.getId());
                        // in this case we don't have the real id
                        oo.setInternalOrderId(0);
                        oo.setRemainingSize(order.getRemainingSize());
                        oo.setTotalSize((order.getInitialSize()));
                        this.tradingService.setBuyOrder(oo);
                    } else if (this.tradingService.getBuyOrder() != null) {
                        if (this.tradingService.getBuyOrder().getUpdateTime() <= order.getUpdateTime().getSeconds()) {
                            if (this.tradingService.getBuyOrder().getExchangeOrderId()!=null){
                                if (tradingService.getBuyOrder().getExchangeOrderId().equals(order.getId())) {
                                    this.tradingService.updateOrderStatus(order);
                                }
                                // the buy update is received before from this channel that the confirmation
                                // so the is not exchange id yet
                                //TODO REVIEW THIS
                            }else {
                                tradingService.getBuyOrder().setExchangeOrderId(order.getId());
                            }


                        }
                    }
                }

            }
            if (!this.tradingService.isOrderInitialized()) {
                this.tradingService.setOrderInitialized(true);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

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

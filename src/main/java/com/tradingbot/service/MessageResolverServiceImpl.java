package com.tradingbot.service;

import com.google.gson.Gson;
import com.tradingbot.entity.order.request.cancelorder.CancelOrder;
import com.tradingbot.service.channel.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageResolverServiceImpl implements MessageProcessingI {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageResolverServiceImpl.class);

    final AuthenticationServiceImpl authenticationService;
    final BalanceServiceImpl balanceService;
    final CandleServiceImpl candleService;
    final InstrumentServiceImpl instrumentService;
    final LastTradesServiceImpl lastTradesService;
    final OrderBookServiceImpl orderBookService;
    final OrderFillsServiceImpl orderFillsService;
    final OrdersServiceImpl orderService;
    final RiskSettingsServiceImpl riskSettingsServiceImpl;
    final SubscriptionServiceImpl subscriptionService;
    final TickerServiceImpl tickerService;
    final TradingServiceImpl tradingService;
    final PositionsServiceImpl positionsService;

    public MessageResolverServiceImpl(BalanceServiceImpl balanceService, SubscriptionServiceImpl subscriptionService, OrderBookServiceImpl orderBookService,
                                      TickerServiceImpl tickerService, CandleServiceImpl candleService, InstrumentServiceImpl instrumentService,
                                      LastTradesServiceImpl lastTradesService, AuthenticationServiceImpl authenticationService, OrderFillsServiceImpl orderFillsService,
                                      OrdersServiceImpl orderService, RiskSettingsServiceImpl riskSettingsService, TradingServiceImpl tradingService, PositionsServiceImpl positionsService) {

        this.balanceService = balanceService;
        this.orderBookService = orderBookService;
        this.subscriptionService = subscriptionService;
        this.tickerService = tickerService;
        this.candleService = candleService;
        this.instrumentService = instrumentService;
        this.lastTradesService = lastTradesService;
        this.authenticationService = authenticationService;
        this.orderFillsService = orderFillsService;
        this.orderService = orderService;
        this.riskSettingsServiceImpl = riskSettingsService;
        this.tradingService = tradingService;
        this.positionsService = positionsService;
    }

    // to be call when app is shutting down to cancel all open orders
    public void cancelAllOrders(WebSocketSession session) throws IOException {
        if (this.tradingService.getSellOrder() != null) {
            if (this.tradingService.getSellOrder().getExchangeOrderId() != null) {
                session.sendMessage(CreateCancelOrder(this.tradingService.getSellOrder().getExchangeOrderId()));
                LOGGER.info("cancelling open sell order");
            }
        }
        if (this.tradingService.getBuyOrder() != null) {
            if (this.tradingService.getBuyOrder().getExchangeOrderId() != null) {
                session.sendMessage(CreateCancelOrder(this.tradingService.getBuyOrder().getExchangeOrderId()));
                LOGGER.info("cancelling open buy order");
            }
        }
        LOGGER.info("open orders were cancelled");
    }

    private WebSocketMessage<String> CreateCancelOrder(Long exchangeOrderId) {
        CancelOrder cancelOrder = new CancelOrder();
        Gson gson = new Gson();
        cancelOrder.setMethod(9);
        tradingService.setOrderId(tradingService.getOrderId() + 1);
        cancelOrder.setId(tradingService.getOrderId());
        cancelOrder.getParams().getData().setMethod("cancelOrder");
        cancelOrder.getParams().getData().setParams(exchangeOrderId);
        String jsonCancelOrder = gson.toJson(cancelOrder);
        return new TextMessage(jsonCancelOrder);
    }

    // will process the message and determine the chanel were it should be processed
    // id < 20 for channel subscriptions
    // id = 100 for authentication
    // id > 100 for orders
    @Override
    public List<WebSocketMessage<String>> processMessage(JSONObject jsonResponse) {

        try {
            String aux = jsonResponse.getString("result");
            if (aux.equals("{}")) {
                int id = jsonResponse.getInt("id");
                if (id < 20) {
                    this.subscriptionService.confirmSubscription(id);
                }
            } else {
                if (jsonResponse.getJSONObject("result").has("tag")) {
                    String tag = jsonResponse.getJSONObject("result").getString("tag");
                    if (tag.equals("err")) {
                        LOGGER.error("Error message received:" + jsonResponse.toString());
                    }
                    if (tag.equals("ok")) {

                        if (jsonResponse.getInt("id") == 100) {
                            // log the authentication response
                            this.authenticationService.logAuthentication(jsonResponse);
                            // once authenticated subscribe to private channels
                            return this.subscriptionService.getPrivateSubscriptions();

                        } else if (jsonResponse.getInt("id") > 100) {
                            this.tradingService.setOrderConfirm(jsonResponse);
                        }
                    }
                } else {
//                    if (jsonResponse.has("id") && jsonResponse.getInt("id") == 100) {
//                        LOGGER.info(jsonResponse.toString());
//                    } else {
                        String channel = jsonResponse.getJSONObject("result").getString("channel");
                        LOGGER.debug("message received from channel: " + channel);
                        switch (channel) {
                            case "orderbook":
                                return this.orderBookService.processMessage(jsonResponse);
                            case "lasttrades":
                                return this.lastTradesService.processMessage(jsonResponse);
                            case "tickers":
                                return this.tickerService.processMessage(jsonResponse);
                            case "candles":
                                return this.candleService.processMessage(jsonResponse);
                            case "instruments":
                                return this.instrumentService.processMessage(jsonResponse);
                            case "orders":
                                return this.orderService.processMessage(jsonResponse);
                            case "orderFills":
                                return this.orderFillsService.processMessage(jsonResponse);
                            case "balance":
                                return this.balanceService.processMessage(jsonResponse);
                            case "positions":
                                return this.positionsService.processMessage(jsonResponse);
                            case "riskSettings":
                                return this.riskSettingsServiceImpl.processMessage(jsonResponse);

                        }
                    }

//                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

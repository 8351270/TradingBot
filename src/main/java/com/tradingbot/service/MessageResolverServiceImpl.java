package com.tradingbot.service;

import com.tradingbot.service.channel.*;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;

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
                                      OrdersServiceImpl orderService, RiskSettingsServiceImpl riskSettingsService, TradingServiceImpl tradingService,PositionsServiceImpl positionsService) {

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
        this.positionsService =  positionsService;
    }


    @Override
    public List<WebSocketMessage<String>> processMessage(JSONObject jsonResponse) {

        try {
            String aux = jsonResponse.getString("result");
            if (aux.equals("{}")) {
                int id = jsonResponse.getInt("id");
                if (id<20){
                    this.subscriptionService.confirmSubscription(id);
                }
//                else {
//                    this.tradingService.setOrderConfirm(jsonResponse);
//                }

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
                        }else if (jsonResponse.getInt("id")>100){
                            this.tradingService.setOrderConfirm(jsonResponse);
                        }
                    }
                } else {
                    if (jsonResponse.has("id") && jsonResponse.getInt("id") == 100) {
                        LOGGER.info(jsonResponse.toString());
                    } else {
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

                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

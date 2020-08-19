package com.tradingbot.service.channel;

import com.tradingbot.service.TradingServiceImpl;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketMessage;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BalanceServiceImpl implements MessageProcessingI {

    @Autowired
    private ApplicationContext appContext;

    private static final Logger LOGGER = LoggerFactory.getLogger(BalanceServiceImpl.class);

    private final TradingServiceImpl tradingService;

    private Long lastUpdated;

    public BalanceServiceImpl(TradingServiceImpl tradingService) {
        this.tradingService = tradingService;
        lastUpdated = 0L;
    }

    @Override
    public List<WebSocketMessage<String>> processMessage(JSONObject jsonResponse) {

        try {
            String tag = jsonResponse.getJSONObject("result").getJSONObject("data").getString("tag");
            if (tag.equals("err")){
                LOGGER.error("error message received from channel balances, message is: " +jsonResponse.getJSONObject("result").getJSONObject("data").getJSONObject("value").getString("code"));
                SpringApplication.exit(appContext, () -> 0);
//                return null;
            }
            String type= jsonResponse.getJSONObject("result").getJSONObject("data").getJSONObject("value").getString("type");
            JSONObject jsonObject;
            if (type.equals("snapshot")){
                jsonObject =jsonResponse.getJSONObject("result").getJSONObject("data").getJSONObject("value").getJSONObject("payload").getJSONObject("BTC");
            }else {
                jsonObject =jsonResponse.getJSONObject("result").getJSONObject("data").getJSONObject("value").getJSONObject("payload");
            }
            long updateTime = jsonObject.getJSONObject("updateTime").getLong("seconds");

//            public BigDecimal(BigInteger unscaledVal, int scale)
            BigDecimal ordersMargin = toBigDecimal(jsonObject.getJSONObject("ordersMargin"));
            if (this.lastUpdated==0){
                this.tradingService.setOrdersMargin(ordersMargin);
                BigDecimal wallet = toBigDecimal(jsonObject.getJSONObject("wallet"));
                this.tradingService.setWallet(wallet);
                BigDecimal positionsMargin = toBigDecimal(jsonObject.getJSONObject("positionsMargin"));
                this.tradingService.setPositionsMargin(positionsMargin);
                //Unrealized profit and loss value components
                BigDecimal unrealizedPnl = toBigDecimal(jsonObject.getJSONObject("unrealizedPnl"));
                this.tradingService.setUnrealizedPnl(unrealizedPnl);
                BigDecimal available = toBigDecimal(jsonObject.getJSONObject("available"));
                this.tradingService.setAvailable(available);
                BigDecimal borrowed = toBigDecimal(jsonObject.getJSONObject("borrowed"));
                this.tradingService.setBorrowed(borrowed);

                this.lastUpdated = updateTime;

                LOGGER.info("Balance initialized with values: " );
                LOGGER.info("Order Margin: " + ordersMargin + "  wallet: " + wallet + "  positionsMargin: " + positionsMargin + "  unrealizedPnl:" + unrealizedPnl+ "  available: " + available + "  borrowed: " + borrowed );
                this.tradingService.setBalanceInitialized(true);
            }else {
                if (!ordersMargin.equals(this.tradingService.getOrdersMargin())){
                    LOGGER.debug("order margin from balance channel updated, old value: " + this.tradingService.getOrdersMargin() + " new value: " + ordersMargin);
                    this.tradingService.setOrdersMargin(ordersMargin);
                }

                BigDecimal wallet = toBigDecimal(jsonObject.getJSONObject("wallet"));
                if (!wallet.equals(this.tradingService.getWallet())){
                    LOGGER.debug("wallet from balance channel updated, old value: " + this.tradingService.getWallet() + " new value: " + wallet);
                    this.tradingService.setWallet(wallet);
                }

                BigDecimal positionsMargin = toBigDecimal(jsonObject.getJSONObject("positionsMargin"));
                if (!positionsMargin.equals(this.tradingService.getPositionsMargin())){
                    LOGGER.debug("positions margin from balance channel updated, old value: " + this.tradingService.getPositionsMargin() + " new value: " + positionsMargin);
                    this.tradingService.setPositionsMargin(positionsMargin);
                }

                //Unrealized profit and loss value components
                BigDecimal unrealizedPnl = toBigDecimal(jsonObject.getJSONObject("unrealizedPnl"));
                if (!unrealizedPnl.equals(this.tradingService.getUnrealizedPnl())){
                    LOGGER.debug("Unrealized profit and loss from balance channel updated, old value: " + this.tradingService.getUnrealizedPnl() + " new value: " + unrealizedPnl);
                    this.tradingService.setUnrealizedPnl(unrealizedPnl);
                }

                BigDecimal available = toBigDecimal(jsonObject.getJSONObject("available"));
                if (!available.equals(this.tradingService.getAvailable())){
                    LOGGER.debug("available from balance channel updated, old value: " + this.tradingService.getAvailable() + " new value: " + available);
                    this.tradingService.setAvailable(available);
                }
                BigDecimal borrowed = toBigDecimal(jsonObject.getJSONObject("borrowed"));
                if (!borrowed.equals(this.tradingService.getBorrowed())){
                    LOGGER.debug("borrowed from balance channel updated, old value: " + this.tradingService.getBorrowed() + " new value: " + borrowed);
                    this.tradingService.setBorrowed(borrowed);
                }
            }

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

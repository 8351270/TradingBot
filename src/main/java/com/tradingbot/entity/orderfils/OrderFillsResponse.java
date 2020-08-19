
package com.tradingbot.entity.orderfils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tradingbot.entity.orderfils.inner.*;

public class OrderFillsResponse {

    @SerializedName("orderId")
    @Expose
    private Long orderId;
    @SerializedName("accountId")
    @Expose
    private Long accountId;
    @SerializedName("tradeId")
    @Expose
    private Long tradeId;
    @SerializedName("instrument")
    @Expose
    private Integer instrument;
    @SerializedName("orderPrice")
    @Expose
    private OrderPrice orderPrice;
    @SerializedName("tradePrice")
    @Expose
    private TradePrice tradePrice;
    @SerializedName("initialQty")
    @Expose
    private Integer initialQty;
    @SerializedName("remainingQty")
    @Expose
    private Integer remainingQty;
    @SerializedName("tradeQty")
    @Expose
    private Long tradeQty;
    @SerializedName("exchangeFee")
    @Expose
    private ExchangeFee exchangeFee;
    @SerializedName("brokerFee")
    @Expose
    private BrokerFee brokerFee;
    @SerializedName("time")
    @Expose
    private Time time;
    @SerializedName("side")
    @Expose
    private String side;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OrderFillsResponse() {
    }

    /**
     * 
     * @param side
     * @param orderId
     * @param exchangeFee
     * @param instrument
     * @param brokerFee
     * @param accountId
     * @param tradeQty
     * @param initialQty
     * @param orderPrice
     * @param time
     * @param tradePrice
     * @param remainingQty
     * @param tradeId
     */
    public OrderFillsResponse(Long orderId, Long accountId, Long tradeId, Integer instrument, OrderPrice orderPrice, TradePrice tradePrice, Integer initialQty, Integer remainingQty, Long tradeQty, ExchangeFee exchangeFee, BrokerFee brokerFee, Time time, String side) {
        super();
        this.orderId = orderId;
        this.accountId = accountId;
        this.tradeId = tradeId;
        this.instrument = instrument;
        this.orderPrice = orderPrice;
        this.tradePrice = tradePrice;
        this.initialQty = initialQty;
        this.remainingQty = remainingQty;
        this.tradeQty = tradeQty;
        this.exchangeFee = exchangeFee;
        this.brokerFee = brokerFee;
        this.time = time;
        this.side = side;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public OrderFillsResponse withOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public OrderFillsResponse withAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public OrderFillsResponse withTradeId(Long tradeId) {
        this.tradeId = tradeId;
        return this;
    }

    public Integer getInstrument() {
        return instrument;
    }

    public void setInstrument(Integer instrument) {
        this.instrument = instrument;
    }

    public OrderFillsResponse withInstrument(Integer instrument) {
        this.instrument = instrument;
        return this;
    }

    public OrderPrice getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(OrderPrice orderPrice) {
        this.orderPrice = orderPrice;
    }

    public OrderFillsResponse withOrderPrice(OrderPrice orderPrice) {
        this.orderPrice = orderPrice;
        return this;
    }

    public TradePrice getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(TradePrice tradePrice) {
        this.tradePrice = tradePrice;
    }

    public OrderFillsResponse withTradePrice(TradePrice tradePrice) {
        this.tradePrice = tradePrice;
        return this;
    }

    public Integer getInitialQty() {
        return initialQty;
    }

    public void setInitialQty(Integer initialQty) {
        this.initialQty = initialQty;
    }

    public OrderFillsResponse withInitialQty(Integer initialQty) {
        this.initialQty = initialQty;
        return this;
    }

    public Integer getRemainingQty() {
        return remainingQty;
    }

    public void setRemainingQty(Integer remainingQty) {
        this.remainingQty = remainingQty;
    }

    public OrderFillsResponse withRemainingQty(Integer remainingQty) {
        this.remainingQty = remainingQty;
        return this;
    }

    public Long getTradeQty() {
        return tradeQty;
    }

    public void setTradeQty(Long tradeQty) {
        this.tradeQty = tradeQty;
    }

    public OrderFillsResponse withTradeQty(Long tradeQty) {
        this.tradeQty = tradeQty;
        return this;
    }

    public ExchangeFee getExchangeFee() {
        return exchangeFee;
    }

    public void setExchangeFee(ExchangeFee exchangeFee) {
        this.exchangeFee = exchangeFee;
    }

    public OrderFillsResponse withExchangeFee(ExchangeFee exchangeFee) {
        this.exchangeFee = exchangeFee;
        return this;
    }

    public BrokerFee getBrokerFee() {
        return brokerFee;
    }

    public void setBrokerFee(BrokerFee brokerFee) {
        this.brokerFee = brokerFee;
    }

    public OrderFillsResponse withBrokerFee(BrokerFee brokerFee) {
        this.brokerFee = brokerFee;
        return this;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public OrderFillsResponse withTime(Time time) {
        this.time = time;
        return this;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public OrderFillsResponse withSide(String side) {
        this.side = side;
        return this;
    }

}

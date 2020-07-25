
package com.tradingbot.entity.orderfils.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payload {

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
    private Long instrument;
    @SerializedName("orderPrice")
    @Expose
    private OrderPrice orderPrice;
    @SerializedName("tradePrice")
    @Expose
    private TradePrice tradePrice;
    @SerializedName("initialQty")
    @Expose
    private Long initialQty;
    @SerializedName("remainingQty")
    @Expose
    private Long remainingQty;
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
    public Payload() {
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
    public Payload(Long orderId, Long accountId, Long tradeId, Long instrument, OrderPrice orderPrice, TradePrice tradePrice, Long initialQty, Long remainingQty, Long tradeQty, ExchangeFee exchangeFee, BrokerFee brokerFee, Time time, String side) {
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

    public Payload withOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Payload withAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }

    public Long getTradeId() {
        return tradeId;
    }

    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    public Payload withTradeId(Long tradeId) {
        this.tradeId = tradeId;
        return this;
    }

    public Long getInstrument() {
        return instrument;
    }

    public void setInstrument(Long instrument) {
        this.instrument = instrument;
    }

    public Payload withInstrument(Long instrument) {
        this.instrument = instrument;
        return this;
    }

    public OrderPrice getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(OrderPrice orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Payload withOrderPrice(OrderPrice orderPrice) {
        this.orderPrice = orderPrice;
        return this;
    }

    public TradePrice getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(TradePrice tradePrice) {
        this.tradePrice = tradePrice;
    }

    public Payload withTradePrice(TradePrice tradePrice) {
        this.tradePrice = tradePrice;
        return this;
    }

    public Long getInitialQty() {
        return initialQty;
    }

    public void setInitialQty(Long initialQty) {
        this.initialQty = initialQty;
    }

    public Payload withInitialQty(Long initialQty) {
        this.initialQty = initialQty;
        return this;
    }

    public Long getRemainingQty() {
        return remainingQty;
    }

    public void setRemainingQty(Long remainingQty) {
        this.remainingQty = remainingQty;
    }

    public Payload withRemainingQty(Long remainingQty) {
        this.remainingQty = remainingQty;
        return this;
    }

    public Long getTradeQty() {
        return tradeQty;
    }

    public void setTradeQty(Long tradeQty) {
        this.tradeQty = tradeQty;
    }

    public Payload withTradeQty(Long tradeQty) {
        this.tradeQty = tradeQty;
        return this;
    }

    public ExchangeFee getExchangeFee() {
        return exchangeFee;
    }

    public void setExchangeFee(ExchangeFee exchangeFee) {
        this.exchangeFee = exchangeFee;
    }

    public Payload withExchangeFee(ExchangeFee exchangeFee) {
        this.exchangeFee = exchangeFee;
        return this;
    }

    public BrokerFee getBrokerFee() {
        return brokerFee;
    }

    public void setBrokerFee(BrokerFee brokerFee) {
        this.brokerFee = brokerFee;
    }

    public Payload withBrokerFee(BrokerFee brokerFee) {
        this.brokerFee = brokerFee;
        return this;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Payload withTime(Time time) {
        this.time = time;
        return this;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Payload withSide(String side) {
        this.side = side;
        return this;
    }

}

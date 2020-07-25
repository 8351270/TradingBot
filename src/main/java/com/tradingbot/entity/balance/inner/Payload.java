
package com.tradingbot.entity.balance.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payload {

    @SerializedName("accountId")
    @Expose
    private Long accountId;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("unrealizedPnl")
    @Expose
    private UnrealizedPnl unrealizedPnl;
    @SerializedName("ordersMargin")
    @Expose
    private OrdersMargin ordersMargin;
    @SerializedName("positionsMargin")
    @Expose
    private PositionsMargin positionsMargin;
    @SerializedName("wallet")
    @Expose
    private Wallet wallet;
    @SerializedName("borrowed")
    @Expose
    private Borrowed borrowed;
    @SerializedName("available")
    @Expose
    private Available available;
    @SerializedName("updateTime")
    @Expose
    private UpdateTime updateTime;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Payload() {
    }

    /**
     * 
     * @param accountId
     * @param ordersMargin
     * @param wallet
     * @param positionsMargin
     * @param unrealizedPnl
     * @param available
     * @param currency
     * @param updateTime
     * @param borrowed
     */
    public Payload(Long accountId, String currency, UnrealizedPnl unrealizedPnl, OrdersMargin ordersMargin, PositionsMargin positionsMargin, Wallet wallet, Borrowed borrowed, Available available, UpdateTime updateTime) {
        super();
        this.accountId = accountId;
        this.currency = currency;
        this.unrealizedPnl = unrealizedPnl;
        this.ordersMargin = ordersMargin;
        this.positionsMargin = positionsMargin;
        this.wallet = wallet;
        this.borrowed = borrowed;
        this.available = available;
        this.updateTime = updateTime;
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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Payload withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public UnrealizedPnl getUnrealizedPnl() {
        return unrealizedPnl;
    }

    public void setUnrealizedPnl(UnrealizedPnl unrealizedPnl) {
        this.unrealizedPnl = unrealizedPnl;
    }

    public Payload withUnrealizedPnl(UnrealizedPnl unrealizedPnl) {
        this.unrealizedPnl = unrealizedPnl;
        return this;
    }

    public OrdersMargin getOrdersMargin() {
        return ordersMargin;
    }

    public void setOrdersMargin(OrdersMargin ordersMargin) {
        this.ordersMargin = ordersMargin;
    }

    public Payload withOrdersMargin(OrdersMargin ordersMargin) {
        this.ordersMargin = ordersMargin;
        return this;
    }

    public PositionsMargin getPositionsMargin() {
        return positionsMargin;
    }

    public void setPositionsMargin(PositionsMargin positionsMargin) {
        this.positionsMargin = positionsMargin;
    }

    public Payload withPositionsMargin(PositionsMargin positionsMargin) {
        this.positionsMargin = positionsMargin;
        return this;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Payload withWallet(Wallet wallet) {
        this.wallet = wallet;
        return this;
    }

    public Borrowed getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(Borrowed borrowed) {
        this.borrowed = borrowed;
    }

    public Payload withBorrowed(Borrowed borrowed) {
        this.borrowed = borrowed;
        return this;
    }

    public Available getAvailable() {
        return available;
    }

    public void setAvailable(Available available) {
        this.available = available;
    }

    public Payload withAvailable(Available available) {
        this.available = available;
        return this;
    }

    public UpdateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(UpdateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Payload withUpdateTime(UpdateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

}


package com.tradingbot.entity.balance;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "accountId",
    "currency",
    "unrealizedPnl",
    "ordersMargin",
    "positionsMargin",
    "wallet",
    "borrowed",
    "available",
    "updateTime"
})
public class Payload implements Serializable
{

    @JsonProperty("accountId")
    private Long accountId;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("unrealizedPnl")
    private UnrealizedPnl unrealizedPnl;
    @JsonProperty("ordersMargin")
    private OrdersMargin ordersMargin;
    @JsonProperty("positionsMargin")
    private PositionsMargin positionsMargin;
    @JsonProperty("wallet")
    private Wallet wallet;
    @JsonProperty("borrowed")
    private Borrowed borrowed;
    @JsonProperty("available")
    private Available available;
    @JsonProperty("updateTime")
    private UpdateTime updateTime;
    private final static long serialVersionUID = -2618545143229623150L;

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

    @JsonProperty("accountId")
    public Long getAccountId() {
        return accountId;
    }

    @JsonProperty("accountId")
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Payload withAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }

    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Payload withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    @JsonProperty("unrealizedPnl")
    public UnrealizedPnl getUnrealizedPnl() {
        return unrealizedPnl;
    }

    @JsonProperty("unrealizedPnl")
    public void setUnrealizedPnl(UnrealizedPnl unrealizedPnl) {
        this.unrealizedPnl = unrealizedPnl;
    }

    public Payload withUnrealizedPnl(UnrealizedPnl unrealizedPnl) {
        this.unrealizedPnl = unrealizedPnl;
        return this;
    }

    @JsonProperty("ordersMargin")
    public OrdersMargin getOrdersMargin() {
        return ordersMargin;
    }

    @JsonProperty("ordersMargin")
    public void setOrdersMargin(OrdersMargin ordersMargin) {
        this.ordersMargin = ordersMargin;
    }

    public Payload withOrdersMargin(OrdersMargin ordersMargin) {
        this.ordersMargin = ordersMargin;
        return this;
    }

    @JsonProperty("positionsMargin")
    public PositionsMargin getPositionsMargin() {
        return positionsMargin;
    }

    @JsonProperty("positionsMargin")
    public void setPositionsMargin(PositionsMargin positionsMargin) {
        this.positionsMargin = positionsMargin;
    }

    public Payload withPositionsMargin(PositionsMargin positionsMargin) {
        this.positionsMargin = positionsMargin;
        return this;
    }

    @JsonProperty("wallet")
    public Wallet getWallet() {
        return wallet;
    }

    @JsonProperty("wallet")
    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    public Payload withWallet(Wallet wallet) {
        this.wallet = wallet;
        return this;
    }

    @JsonProperty("borrowed")
    public Borrowed getBorrowed() {
        return borrowed;
    }

    @JsonProperty("borrowed")
    public void setBorrowed(Borrowed borrowed) {
        this.borrowed = borrowed;
    }

    public Payload withBorrowed(Borrowed borrowed) {
        this.borrowed = borrowed;
        return this;
    }

    @JsonProperty("available")
    public Available getAvailable() {
        return available;
    }

    @JsonProperty("available")
    public void setAvailable(Available available) {
        this.available = available;
    }

    public Payload withAvailable(Available available) {
        this.available = available;
        return this;
    }

    @JsonProperty("updateTime")
    public UpdateTime getUpdateTime() {
        return updateTime;
    }

    @JsonProperty("updateTime")
    public void setUpdateTime(UpdateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Payload withUpdateTime(UpdateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

}

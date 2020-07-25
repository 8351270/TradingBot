
package com.tradingbot.entity.instruments.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class _1 {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("symbol")
    @Expose
    private String symbol;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("settlementCurrency")
    @Expose
    private String settlementCurrency;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("minPriceIncrement")
    @Expose
    private MinPriceIncrement minPriceIncrement;
    @SerializedName("contractMultiplier")
    @Expose
    private ContractMultiplier contractMultiplier;
    @SerializedName("minPrice")
    @Expose
    private MinPrice minPrice;
    @SerializedName("maxPrice")
    @Expose
    private MaxPrice maxPrice;

    /**
     * No args constructor for use in serialization
     * 
     */
    public _1() {
    }

    /**
     * 
     * @param symbol
     * @param contractMultiplier
     * @param settlementCurrency
     * @param minPrice
     * @param currency
     * @param id
     * @param maxPrice
     * @param status
     * @param minPriceIncrement
     */
    public _1(Long id, String symbol, String currency, String settlementCurrency, String status, MinPriceIncrement minPriceIncrement, ContractMultiplier contractMultiplier, MinPrice minPrice, MaxPrice maxPrice) {
        super();
        this.id = id;
        this.symbol = symbol;
        this.currency = currency;
        this.settlementCurrency = settlementCurrency;
        this.status = status;
        this.minPriceIncrement = minPriceIncrement;
        this.contractMultiplier = contractMultiplier;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public _1 withId(Long id) {
        this.id = id;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public _1 withSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public _1 withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public String getSettlementCurrency() {
        return settlementCurrency;
    }

    public void setSettlementCurrency(String settlementCurrency) {
        this.settlementCurrency = settlementCurrency;
    }

    public _1 withSettlementCurrency(String settlementCurrency) {
        this.settlementCurrency = settlementCurrency;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public _1 withStatus(String status) {
        this.status = status;
        return this;
    }

    public MinPriceIncrement getMinPriceIncrement() {
        return minPriceIncrement;
    }

    public void setMinPriceIncrement(MinPriceIncrement minPriceIncrement) {
        this.minPriceIncrement = minPriceIncrement;
    }

    public _1 withMinPriceIncrement(MinPriceIncrement minPriceIncrement) {
        this.minPriceIncrement = minPriceIncrement;
        return this;
    }

    public ContractMultiplier getContractMultiplier() {
        return contractMultiplier;
    }

    public void setContractMultiplier(ContractMultiplier contractMultiplier) {
        this.contractMultiplier = contractMultiplier;
    }

    public _1 withContractMultiplier(ContractMultiplier contractMultiplier) {
        this.contractMultiplier = contractMultiplier;
        return this;
    }

    public MinPrice getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(MinPrice minPrice) {
        this.minPrice = minPrice;
    }

    public _1 withMinPrice(MinPrice minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public MaxPrice getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(MaxPrice maxPrice) {
        this.maxPrice = maxPrice;
    }

    public _1 withMaxPrice(MaxPrice maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

}

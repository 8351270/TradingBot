
package com.tradingbot.entity.instruments.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class _2 {

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
    private MinPriceIncrement_ minPriceIncrement;
    @SerializedName("contractMultiplier")
    @Expose
    private ContractMultiplier_ contractMultiplier;
    @SerializedName("minPrice")
    @Expose
    private MinPrice_ minPrice;
    @SerializedName("maxPrice")
    @Expose
    private MaxPrice_ maxPrice;

    /**
     * No args constructor for use in serialization
     * 
     */
    public _2() {
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
    public _2(Long id, String symbol, String currency, String settlementCurrency, String status, MinPriceIncrement_ minPriceIncrement, ContractMultiplier_ contractMultiplier, MinPrice_ minPrice, MaxPrice_ maxPrice) {
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

    public _2 withId(Long id) {
        this.id = id;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public _2 withSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public _2 withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public String getSettlementCurrency() {
        return settlementCurrency;
    }

    public void setSettlementCurrency(String settlementCurrency) {
        this.settlementCurrency = settlementCurrency;
    }

    public _2 withSettlementCurrency(String settlementCurrency) {
        this.settlementCurrency = settlementCurrency;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public _2 withStatus(String status) {
        this.status = status;
        return this;
    }

    public MinPriceIncrement_ getMinPriceIncrement() {
        return minPriceIncrement;
    }

    public void setMinPriceIncrement(MinPriceIncrement_ minPriceIncrement) {
        this.minPriceIncrement = minPriceIncrement;
    }

    public _2 withMinPriceIncrement(MinPriceIncrement_ minPriceIncrement) {
        this.minPriceIncrement = minPriceIncrement;
        return this;
    }

    public ContractMultiplier_ getContractMultiplier() {
        return contractMultiplier;
    }

    public void setContractMultiplier(ContractMultiplier_ contractMultiplier) {
        this.contractMultiplier = contractMultiplier;
    }

    public _2 withContractMultiplier(ContractMultiplier_ contractMultiplier) {
        this.contractMultiplier = contractMultiplier;
        return this;
    }

    public MinPrice_ getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(MinPrice_ minPrice) {
        this.minPrice = minPrice;
    }

    public _2 withMinPrice(MinPrice_ minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public MaxPrice_ getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(MaxPrice_ maxPrice) {
        this.maxPrice = maxPrice;
    }

    public _2 withMaxPrice(MaxPrice_ maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

}

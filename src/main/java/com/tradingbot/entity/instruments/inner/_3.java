
package com.tradingbot.entity.instruments.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class _3 {

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
    private MinPriceIncrement__ minPriceIncrement;
    @SerializedName("contractMultiplier")
    @Expose
    private ContractMultiplier__ contractMultiplier;
    @SerializedName("minPrice")
    @Expose
    private MinPrice__ minPrice;
    @SerializedName("maxPrice")
    @Expose
    private MaxPrice__ maxPrice;

    /**
     * No args constructor for use in serialization
     * 
     */
    public _3() {
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
    public _3(Long id, String symbol, String currency, String settlementCurrency, String status, MinPriceIncrement__ minPriceIncrement, ContractMultiplier__ contractMultiplier, MinPrice__ minPrice, MaxPrice__ maxPrice) {
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

    public _3 withId(Long id) {
        this.id = id;
        return this;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public _3 withSymbol(String symbol) {
        this.symbol = symbol;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public _3 withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public String getSettlementCurrency() {
        return settlementCurrency;
    }

    public void setSettlementCurrency(String settlementCurrency) {
        this.settlementCurrency = settlementCurrency;
    }

    public _3 withSettlementCurrency(String settlementCurrency) {
        this.settlementCurrency = settlementCurrency;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public _3 withStatus(String status) {
        this.status = status;
        return this;
    }

    public MinPriceIncrement__ getMinPriceIncrement() {
        return minPriceIncrement;
    }

    public void setMinPriceIncrement(MinPriceIncrement__ minPriceIncrement) {
        this.minPriceIncrement = minPriceIncrement;
    }

    public _3 withMinPriceIncrement(MinPriceIncrement__ minPriceIncrement) {
        this.minPriceIncrement = minPriceIncrement;
        return this;
    }

    public ContractMultiplier__ getContractMultiplier() {
        return contractMultiplier;
    }

    public void setContractMultiplier(ContractMultiplier__ contractMultiplier) {
        this.contractMultiplier = contractMultiplier;
    }

    public _3 withContractMultiplier(ContractMultiplier__ contractMultiplier) {
        this.contractMultiplier = contractMultiplier;
        return this;
    }

    public MinPrice__ getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(MinPrice__ minPrice) {
        this.minPrice = minPrice;
    }

    public _3 withMinPrice(MinPrice__ minPrice) {
        this.minPrice = minPrice;
        return this;
    }

    public MaxPrice__ getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(MaxPrice__ maxPrice) {
        this.maxPrice = maxPrice;
    }

    public _3 withMaxPrice(MaxPrice__ maxPrice) {
        this.maxPrice = maxPrice;
        return this;
    }

}

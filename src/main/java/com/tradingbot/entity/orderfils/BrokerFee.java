
package com.tradingbot.entity.orderfils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BrokerFee {

    @SerializedName("amount")
    @Expose
    private Amount_ amount;
    @SerializedName("currency")
    @Expose
    private String currency;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BrokerFee() {
    }

    /**
     * 
     * @param amount
     * @param currency
     */
    public BrokerFee(Amount_ amount, String currency) {
        super();
        this.amount = amount;
        this.currency = currency;
    }

    public Amount_ getAmount() {
        return amount;
    }

    public void setAmount(Amount_ amount) {
        this.amount = amount;
    }

    public BrokerFee withAmount(Amount_ amount) {
        this.amount = amount;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BrokerFee withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

}

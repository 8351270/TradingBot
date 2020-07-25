
package com.tradingbot.entity.orderfils.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExchangeFee {

    @SerializedName("amount")
    @Expose
    private Amount amount;
    @SerializedName("currency")
    @Expose
    private String currency;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ExchangeFee() {
    }

    /**
     * 
     * @param amount
     * @param currency
     */
    public ExchangeFee(Amount amount, String currency) {
        super();
        this.amount = amount;
        this.currency = currency;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public ExchangeFee withAmount(Amount amount) {
        this.amount = amount;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public ExchangeFee withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

}

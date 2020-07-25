
package com.tradingbot.entity.tickers.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastPrice {

    @SerializedName("mantissa")
    @Expose
    private Long mantissa;
    @SerializedName("exponent")
    @Expose
    private Long exponent;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LastPrice() {
    }

    /**
     * 
     * @param mantissa
     * @param exponent
     */
    public LastPrice(Long mantissa, Long exponent) {
        super();
        this.mantissa = mantissa;
        this.exponent = exponent;
    }

    public Long getMantissa() {
        return mantissa;
    }

    public void setMantissa(Long mantissa) {
        this.mantissa = mantissa;
    }

    public LastPrice withMantissa(Long mantissa) {
        this.mantissa = mantissa;
        return this;
    }

    public Long getExponent() {
        return exponent;
    }

    public void setExponent(Long exponent) {
        this.exponent = exponent;
    }

    public LastPrice withExponent(Long exponent) {
        this.exponent = exponent;
        return this;
    }

}


package com.tradingbot.entity.instruments.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContractMultiplier_ {

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
    public ContractMultiplier_() {
    }

    /**
     * 
     * @param mantissa
     * @param exponent
     */
    public ContractMultiplier_(Long mantissa, Long exponent) {
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

    public ContractMultiplier_ withMantissa(Long mantissa) {
        this.mantissa = mantissa;
        return this;
    }

    public Long getExponent() {
        return exponent;
    }

    public void setExponent(Long exponent) {
        this.exponent = exponent;
    }

    public ContractMultiplier_ withExponent(Long exponent) {
        this.exponent = exponent;
        return this;
    }

}

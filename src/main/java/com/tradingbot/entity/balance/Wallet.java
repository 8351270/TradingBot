
package com.tradingbot.entity.balance;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "mantissa",
    "exponent"
})
public class Wallet implements Serializable
{

    @JsonProperty("mantissa")
    private Integer mantissa;
    @JsonProperty("exponent")
    private Integer exponent;
    private final static long serialVersionUID = 6473922977562884952L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Wallet() {
    }

    /**
     * 
     * @param mantissa
     * @param exponent
     */
    public Wallet(Integer mantissa, Integer exponent) {
        super();
        this.mantissa = mantissa;
        this.exponent = exponent;
    }

    @JsonProperty("mantissa")
    public Integer getMantissa() {
        return mantissa;
    }

    @JsonProperty("mantissa")
    public void setMantissa(Integer mantissa) {
        this.mantissa = mantissa;
    }

    public Wallet withMantissa(Integer mantissa) {
        this.mantissa = mantissa;
        return this;
    }

    @JsonProperty("exponent")
    public Integer getExponent() {
        return exponent;
    }

    @JsonProperty("exponent")
    public void setExponent(Integer exponent) {
        this.exponent = exponent;
    }

    public Wallet withExponent(Integer exponent) {
        this.exponent = exponent;
        return this;
    }

}

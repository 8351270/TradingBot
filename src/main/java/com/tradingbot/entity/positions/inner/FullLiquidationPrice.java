
package com.tradingbot.entity.positions.inner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "mantissa",
    "exponent"
})
public class FullLiquidationPrice implements Serializable
{

    @JsonProperty("mantissa")
    private Long mantissa;
    @JsonProperty("exponent")
    private Long exponent;
    private final static long serialVersionUID = 7989082510859242475L;

    @JsonProperty("mantissa")
    public Long getMantissa() {
        return mantissa;
    }

    @JsonProperty("mantissa")
    public void setMantissa(Long mantissa) {
        this.mantissa = mantissa;
    }

    public FullLiquidationPrice withMantissa(Long mantissa) {
        this.mantissa = mantissa;
        return this;
    }

    @JsonProperty("exponent")
    public Long getExponent() {
        return exponent;
    }

    @JsonProperty("exponent")
    public void setExponent(Long exponent) {
        this.exponent = exponent;
    }

    public FullLiquidationPrice withExponent(Long exponent) {
        this.exponent = exponent;
        return this;
    }

    @Override
    public String toString() {
        return "EntryPrice{" +
                "mantissa=" + mantissa +
                ", exponent=" + exponent +
                '}';
    }
}

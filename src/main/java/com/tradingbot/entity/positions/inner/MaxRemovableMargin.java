
package com.tradingbot.entity.positions.inner;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "mantissa",
    "exponent"
})
public class MaxRemovableMargin implements Serializable
{

    @JsonProperty("mantissa")
    private Long mantissa;
    @JsonProperty("exponent")
    private Long exponent;
    private final static long serialVersionUID = 6179697691128581649L;

    @JsonProperty("mantissa")
    public Long getMantissa() {
        return mantissa;
    }

    @JsonProperty("mantissa")
    public void setMantissa(Long mantissa) {
        this.mantissa = mantissa;
    }

    public MaxRemovableMargin withMantissa(Long mantissa) {
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

    public MaxRemovableMargin withExponent(Long exponent) {
        this.exponent = exponent;
        return this;
    }

    @Override
    public String toString() {
        return "MaxRemovableMargin{" +
                "mantissa=" + mantissa +
                ", exponent=" + exponent +
                '}';
    }
}

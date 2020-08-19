
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
public class EntryNotionalValue implements Serializable
{

    @JsonProperty("mantissa")
    private Long mantissa;
    @JsonProperty("exponent")
    private Long exponent;
    private final static long serialVersionUID = -4955405239795487532L;

    @JsonProperty("mantissa")
    public Long getMantissa() {
        return mantissa;
    }

    @JsonProperty("mantissa")
    public void setMantissa(Long mantissa) {
        this.mantissa = mantissa;
    }

    public EntryNotionalValue withMantissa(Long mantissa) {
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

    public EntryNotionalValue withExponent(Long exponent) {
        this.exponent = exponent;
        return this;
    }

    @Override
    public String toString() {
        return "EntryNotionalValue{" +
                "mantissa=" + mantissa +
                ", exponent=" + exponent +
                '}';
    }
}


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
public class UnrealizedPnl implements Serializable
{

    @JsonProperty("mantissa")
    private Long mantissa;
    @JsonProperty("exponent")
    private Long exponent;
    private final static long serialVersionUID = -5651553775336282602L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UnrealizedPnl() {
    }

    /**
     * 
     * @param mantissa
     * @param exponent
     */
    public UnrealizedPnl(Long mantissa, Long exponent) {
        super();
        this.mantissa = mantissa;
        this.exponent = exponent;
    }

    @JsonProperty("mantissa")
    public Long getMantissa() {
        return mantissa;
    }

    @JsonProperty("mantissa")
    public void setMantissa(Long mantissa) {
        this.mantissa = mantissa;
    }

    public UnrealizedPnl withMantissa(Long mantissa) {
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

    public UnrealizedPnl withExponent(Long exponent) {
        this.exponent = exponent;
        return this;
    }

    @Override
    public String toString() {
        return "UnrealizedPnl{" +
                "mantissa=" + mantissa +
                ", exponent=" + exponent +
                '}';
    }
}

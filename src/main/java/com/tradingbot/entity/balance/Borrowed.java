
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
public class Borrowed implements Serializable
{

    @JsonProperty("mantissa")
    private Integer mantissa;
    @JsonProperty("exponent")
    private Integer exponent;
    private final static long serialVersionUID = -938308284133633274L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Borrowed() {
    }

    /**
     * 
     * @param mantissa
     * @param exponent
     */
    public Borrowed(Integer mantissa, Integer exponent) {
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

    public Borrowed withMantissa(Integer mantissa) {
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

    public Borrowed withExponent(Integer exponent) {
        this.exponent = exponent;
        return this;
    }

}

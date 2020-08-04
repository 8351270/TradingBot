
package com.tradingbot.entity.balance;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "seconds",
    "nanos"
})
public class UpdateTime implements Serializable
{

    @JsonProperty("seconds")
    private Integer seconds;
    @JsonProperty("nanos")
    private Integer nanos;
    private final static long serialVersionUID = 5393582058722999026L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public UpdateTime() {
    }

    /**
     * 
     * @param seconds
     * @param nanos
     */
    public UpdateTime(Integer seconds, Integer nanos) {
        super();
        this.seconds = seconds;
        this.nanos = nanos;
    }

    @JsonProperty("seconds")
    public Integer getSeconds() {
        return seconds;
    }

    @JsonProperty("seconds")
    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public UpdateTime withSeconds(Integer seconds) {
        this.seconds = seconds;
        return this;
    }

    @JsonProperty("nanos")
    public Integer getNanos() {
        return nanos;
    }

    @JsonProperty("nanos")
    public void setNanos(Integer nanos) {
        this.nanos = nanos;
    }

    public UpdateTime withNanos(Integer nanos) {
        this.nanos = nanos;
        return this;
    }

}

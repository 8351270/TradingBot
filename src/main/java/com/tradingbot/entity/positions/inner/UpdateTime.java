
package com.tradingbot.entity.positions.inner;

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
    private Long seconds;
    @JsonProperty("nanos")
    private Long nanos;
    private final static long serialVersionUID = -1653056888652451964L;

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
    public UpdateTime(Long seconds, Long nanos) {
        super();
        this.seconds = seconds;
        this.nanos = nanos;
    }

    @JsonProperty("seconds")
    public Long getSeconds() {
        return seconds;
    }

    @JsonProperty("seconds")
    public void setSeconds(Long seconds) {
        this.seconds = seconds;
    }

    public UpdateTime withSeconds(Long seconds) {
        this.seconds = seconds;
        return this;
    }

    @JsonProperty("nanos")
    public Long getNanos() {
        return nanos;
    }

    @JsonProperty("nanos")
    public void setNanos(Long nanos) {
        this.nanos = nanos;
    }

    public UpdateTime withNanos(Long nanos) {
        this.nanos = nanos;
        return this;
    }

    @Override
    public String toString() {
        return "UpdateTime{" +
                "seconds=" + seconds +
                ", nanos=" + nanos +
                '}';
    }
}

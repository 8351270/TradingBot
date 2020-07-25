package com.tradingbot.entity.authentication.request.inner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "seconds",
        "nanos"
})
@JsonRootName(value = "time")
public class Time {

    @JsonProperty("seconds")
    private Long seconds;
    @JsonProperty("nanos")
    private Long nanos;

    public Time(){

    }

    @JsonProperty("seconds")
    public Long getSeconds() {
        return seconds;
    }

    @JsonProperty("seconds")
    public void setSeconds(Long seconds) {
        this.seconds = seconds;
    }

    public Time withSeconds(Long seconds) {
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

    public Time withNanos(Long nanos) {
        this.nanos = nanos;
        return this;
    }
}

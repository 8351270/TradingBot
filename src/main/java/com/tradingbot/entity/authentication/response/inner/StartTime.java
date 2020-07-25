package com.tradingbot.entity.authentication.response.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StartTime {

    @SerializedName("seconds")
    @Expose
    private Long seconds;
    @SerializedName("nanos")
    @Expose
    private Long nanos;

    public Long getSeconds() {
        return seconds;
    }

    public void setSeconds(Long seconds) {
        this.seconds = seconds;
    }

    public StartTime withSeconds(Long seconds) {
        this.seconds = seconds;
        return this;
    }

    public Long getNanos() {
        return nanos;
    }

    public void setNanos(Long nanos) {
        this.nanos = nanos;
    }

    public StartTime withNanos(Long nanos) {
        this.nanos = nanos;
        return this;
    }

}

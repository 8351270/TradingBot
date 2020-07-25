
package com.tradingbot.entity.lasttrades.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Time {

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

    public Time withSeconds(Long seconds) {
        this.seconds = seconds;
        return this;
    }

    public Long getNanos() {
        return nanos;
    }

    public void setNanos(Long nanos) {
        this.nanos = nanos;
    }

    public Time withNanos(Long nanos) {
        this.nanos = nanos;
        return this;
    }

}

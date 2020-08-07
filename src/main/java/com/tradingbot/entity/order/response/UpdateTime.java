
package com.tradingbot.entity.order.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateTime {

    @SerializedName("seconds")
    @Expose
    private Long seconds;
    @SerializedName("nanos")
    @Expose
    private Long nanos;

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

    public Long getSeconds() {
        return seconds;
    }

    public void setSeconds(Long seconds) {
        this.seconds = seconds;
    }

    public UpdateTime withSeconds(Long seconds) {
        this.seconds = seconds;
        return this;
    }

    public Long getNanos() {
        return nanos;
    }

    public void setNanos(Long nanos) {
        this.nanos = nanos;
    }

    public UpdateTime withNanos(Long nanos) {
        this.nanos = nanos;
        return this;
    }

}

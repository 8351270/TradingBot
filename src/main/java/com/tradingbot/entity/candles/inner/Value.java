
package com.tradingbot.entity.candles.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("time")
    @Expose
    private Long time;
    @SerializedName("close")
    @Expose
    private Float close;
    @SerializedName("open")
    @Expose
    private Long open;
    @SerializedName("high")
    @Expose
    private Long high;
    @SerializedName("low")
    @Expose
    private Float low;
    @SerializedName("volume")
    @Expose
    private Long volume;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Value() {
    }

    /**
     * 
     * @param volume
     * @param high
     * @param low
     * @param time
     * @param close
     * @param open
     */
    public Value(Long time, Float close, Long open, Long high, Float low, Long volume) {
        super();
        this.time = time;
        this.close = close;
        this.open = open;
        this.high = high;
        this.low = low;
        this.volume = volume;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Value withTime(Long time) {
        this.time = time;
        return this;
    }

    public Float getClose() {
        return close;
    }

    public void setClose(Float close) {
        this.close = close;
    }

    public Value withClose(Float close) {
        this.close = close;
        return this;
    }

    public Long getOpen() {
        return open;
    }

    public void setOpen(Long open) {
        this.open = open;
    }

    public Value withOpen(Long open) {
        this.open = open;
        return this;
    }

    public Long getHigh() {
        return high;
    }

    public void setHigh(Long high) {
        this.high = high;
    }

    public Value withHigh(Long high) {
        this.high = high;
        return this;
    }

    public Float getLow() {
        return low;
    }

    public void setLow(Float low) {
        this.low = low;
    }

    public Value withLow(Float low) {
        this.low = low;
        return this;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Value withVolume(Long volume) {
        this.volume = volume;
        return this;
    }

}

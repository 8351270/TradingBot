
package com.tradingbot.entity.candles.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("channel")
    @Expose
    private String channel;
    @SerializedName("data")
    @Expose
    private Data data;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     * 
     * @param data
     * @param channel
     */
    public Result(String channel, Data data) {
        super();
        this.channel = channel;
        this.data = data;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Result withChannel(String channel) {
        this.channel = channel;
        return this;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Result withData(Data data) {
        this.data = data;
        return this;
    }

}

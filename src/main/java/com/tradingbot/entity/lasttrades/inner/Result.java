
package com.tradingbot.entity.lasttrades.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("channel")
    @Expose
    private String channel;
    @SerializedName("data")
    @Expose
    private Data data;

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

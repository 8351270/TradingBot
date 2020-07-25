package com.tradingbot.entity.subscription;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Params {

    @SerializedName("channel")
    @Expose
    private String channel;

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Params withChannel(String channel) {
        this.channel = channel;
        return this;
    }
}
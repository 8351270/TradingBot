package com.tradingbot.entity.authentication.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("value")
    @Expose
    private Value value;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Result withTag(String tag) {
        this.tag = tag;
        return this;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Result withValue(Value value) {
        this.value = value;
        return this;
    }

}


package com.tradingbot.entity.lasttrades.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

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

    public Data withTag(String tag) {
        this.tag = tag;
        return this;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Data withValue(Value value) {
        this.value = value;
        return this;
    }

}

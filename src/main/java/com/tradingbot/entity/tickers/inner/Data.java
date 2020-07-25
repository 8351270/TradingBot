
package com.tradingbot.entity.tickers.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("value")
    @Expose
    private Value value;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param tag
     * @param value
     */
    public Data(String tag, Value value) {
        super();
        this.tag = tag;
        this.value = value;
    }

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

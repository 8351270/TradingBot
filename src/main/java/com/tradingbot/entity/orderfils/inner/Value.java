
package com.tradingbot.entity.orderfils.inner;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("payload")
    @Expose
    private List<Payload> payload = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Value() {
    }

    /**
     * 
     * @param payload
     * @param type
     */
    public Value(String type, List<Payload> payload) {
        super();
        this.type = type;
        this.payload = payload;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Value withType(String type) {
        this.type = type;
        return this;
    }

    public List<Payload> getPayload() {
        return payload;
    }

    public void setPayload(List<Payload> payload) {
        this.payload = payload;
    }

    public Value withPayload(List<Payload> payload) {
        this.payload = payload;
        return this;
    }

}

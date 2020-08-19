
package com.tradingbot.entity.risksettings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tradingbot.entity.risksettings.inner.Payload;

import java.util.ArrayList;
import java.util.List;

public class RiskSettings {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("payload")
    @Expose
    private List<Payload> payload;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RiskSettings() {
        this.payload = new ArrayList<>();
    }

    /**
     * 
     * @param payload
     * @param type
     */
    public RiskSettings(String type, List<Payload> payload) {
        super();
        this.type = type;
        this.payload = new ArrayList<>();
        this.payload.addAll(payload);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Payload> getPayload() {
        return payload;
    }

    public void setPayload(List<Payload> payload) {
        this.payload = payload;
    }

    public RiskSettings withType(String type) {
        this.type = type;
        return this;
    }

    public RiskSettings withPayload(List<Payload> payload) {
        this.payload.addAll(payload);
        return this;
    }

}

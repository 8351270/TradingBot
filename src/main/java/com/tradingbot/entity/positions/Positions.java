
package com.tradingbot.entity.positions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.tradingbot.entity.positions.inner.Payload;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "payload"
})
public class Positions implements Serializable
{

    @JsonProperty("type")
    private String type;
    @JsonProperty("payload")
    private List<Payload> payload;
    private final static long serialVersionUID = 2403024069465191317L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Positions() {
        this.payload = new ArrayList<Payload>();
    }

    /**
     * 
     * @param payload
     * @param type
     */
    public Positions(String type, List<Payload> payload) {
        super();
        this.type = type;
        this.payload = payload;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public Positions withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("payload")
    public List<Payload> getPayload() {
        return payload;
    }

    @JsonProperty("payload")
    public void setPayload(List<Payload> payload) {
        this.payload = payload;
    }

    public Positions withPayload(List<Payload> payload) {
        this.payload = payload;
        return this;
    }

    @Override
    public String toString() {
        return "Positions{" +
                "type='" + type + '\'' +
                ", payload=" + payload.toString() +
                '}';
    }
}

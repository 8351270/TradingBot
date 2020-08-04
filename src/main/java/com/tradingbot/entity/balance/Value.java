
package com.tradingbot.entity.balance;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "payload"
})
public class Value implements Serializable
{

    @JsonProperty("type")
    private String type;
    @JsonProperty("payload")
    private Payload payload;
    private final static long serialVersionUID = 1027006841743380473L;

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
    public Value(String type, Payload payload) {
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

    public Value withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("payload")
    public Payload getPayload() {
        return payload;
    }

    @JsonProperty("payload")
    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    public Value withPayload(Payload payload) {
        this.payload = payload;
        return this;
    }

}

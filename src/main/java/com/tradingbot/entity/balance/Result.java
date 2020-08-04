
package com.tradingbot.entity.balance;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "channel",
    "data"
})
public class Result implements Serializable
{

    @JsonProperty("channel")
    private String channel;
    @JsonProperty("data")
    private Data data;
    private final static long serialVersionUID = 6336226687799934881L;

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

    @JsonProperty("channel")
    public String getChannel() {
        return channel;
    }

    @JsonProperty("channel")
    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Result withChannel(String channel) {
        this.channel = channel;
        return this;
    }

    @JsonProperty("data")
    public Data getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Data data) {
        this.data = data;
    }

    public Result withData(Data data) {
        this.data = data;
        return this;
    }

}

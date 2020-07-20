package com.tradingbot.entity.authentication.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "apiKey",
        "time",
        "hmacDigest"
})
@JsonRootName(value = "params")
public class Params_ {

    @JsonProperty("apiKey")
    private String apiKey;
    @JsonProperty("time")
    private Time time;
    @JsonProperty("hmacDigest")
    private String hmacDigest;

    public Params_(){
        this.time = new Time();
    }


    @JsonProperty("apiKey")
    public String getApiKey() {
        return apiKey;
    }

    @JsonProperty("apiKey")
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public Params_ withApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    @JsonProperty("time")
    public Time getTime() {
        return time;
    }

    @JsonProperty("time")
    public void setTime(Time time) {
        this.time = time;
    }

    public Params_ withTime(Time time) {
        this.time = time;
        return this;
    }

    @JsonProperty("hmacDigest")
    public String getHmacDigest() {
        return hmacDigest;
    }

    @JsonProperty("hmacDigest")
    public void setHmacDigest(String hmacDigest) {
        this.hmacDigest = hmacDigest;
    }

    public Params_ withHmacDigest(String hmacDigest) {
        this.hmacDigest = hmacDigest;
        return this;
    }
}

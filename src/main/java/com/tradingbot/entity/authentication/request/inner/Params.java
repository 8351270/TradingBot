package com.tradingbot.entity.authentication.request.inner;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "data"
})
@JsonRootName(value = "params")
public class Params {

    @JsonProperty("data")
    private Data data;

    public Params(){
        this.data = new Data();
    }
    @JsonProperty("data")
    public Data getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Data data) {
        this.data = data;
    }

    public Params withData(Data data) {
        this.data = data;
        return this;
    }
}

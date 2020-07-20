package com.tradingbot.entity.authentication.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;


// this code was generated from JSON with service http://www.jsonschema2pojo.org/


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "method",
        "params"

})
@JsonRootName(value = "data")
public class Data {

    @JsonProperty("method")
    private String method;
    @JsonProperty("params")
    private Params_ params;

    public Data(){
        this.method = "";
        this.params = new Params_();
    }

    @JsonProperty("method")
    public String getMethod() {
        return method;
    }

    @JsonProperty("method")
    public void setMethod(String method) {
        this.method = method;
    }

    public Data withMethod(String method) {
        this.method = method;
        return this;
    }

    @JsonProperty("params")
    public Params_ getParams() {
        return params;
    }

    @JsonProperty("params")
    public void setParams(Params_ params) {
        this.params = params;
    }

    public Data withParams(Params_ params) {
        this.params = params;
        return this;
    }


}



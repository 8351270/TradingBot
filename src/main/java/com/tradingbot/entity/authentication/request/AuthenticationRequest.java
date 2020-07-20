package com.tradingbot.entity.authentication.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "method",
        "params",
        "id"
})
@JsonRootName(value = "authentication")
public class AuthenticationRequest {

    @JsonProperty("method")
    private Integer method;
    @JsonProperty("params")
    private Params params;
    @JsonProperty("id")
    private Integer id;

    public AuthenticationRequest(){
        this.params = new Params();
    }

    @JsonProperty("method")
    public Integer getMethod() {
        return method;
    }

    @JsonProperty("method")
    public void setMethod(Integer method) {
        this.method = method;
    }

    public AuthenticationRequest withMethod(Integer method) {
        this.method = method;
        return this;
    }

    @JsonProperty("params")
    public Params getParams() {
        return params;
    }

    @JsonProperty("params")
    public void setParams(Params params) {
        this.params = params;
    }

    public AuthenticationRequest withParams(Params params) {
        this.params = params;
        return this;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public AuthenticationRequest withId(Integer id) {
        this.id = id;
        return this;
    }

}
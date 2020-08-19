
package com.tradingbot.entity.order.request.neworder.inner;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "method",
    "params"
})
public class Data implements Serializable
{

    @JsonProperty("method")
    private String method;
    @JsonProperty("params")
    private Params_ params;
    private final static long serialVersionUID = 2600691869416639361L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
        this.params = new Params_();
    }

    /**
     * 
     * @param method
     * @param params
     */
    public Data(String method, Params_ params) {
        super();
        this.method = method;
        this.params = params;
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

    @Override
    public String toString() {
        return "Data{" +
                "method='" + method + '\'' +
                ", params=" + params.toString() +
                '}';
    }
}


package com.tradingbot.entity.order.request.cancelorder;

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
    private Long params;
    private final static long serialVersionUID = 7290772611557267376L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param method
     * @param params
     */
    public Data(String method, Long params) {
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
    public Long getParams() {
        return params;
    }

    @JsonProperty("params")
    public void setParams(Long params) {
        this.params = params;
    }

    public Data withParams(Long params) {
        this.params = params;
        return this;
    }

}

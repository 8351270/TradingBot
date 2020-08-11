
package com.tradingbot.entity.order.request.updateorder;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "method",
    "params",
    "id"
})
public class ReplaceOrder implements Serializable
{

    @JsonProperty("method")
    private Integer method;
    @JsonProperty("params")
    private Params params;
    @JsonProperty("id")
    private Integer id;
    private final static long serialVersionUID = -1638863475204838628L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ReplaceOrder() {
        this.params = new Params();
    }

    /**
     * 
     * @param method
     * @param id
     * @param params
     */
    public ReplaceOrder(Integer method, Params params, Integer id) {
        super();
        this.method = method;
        this.params = params;
        this.id = id;
    }

    @JsonProperty("method")
    public Integer getMethod() {
        return method;
    }

    @JsonProperty("method")
    public void setMethod(Integer method) {
        this.method = method;
    }

    public ReplaceOrder withMethod(Integer method) {
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

    public ReplaceOrder withParams(Params params) {
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

    public ReplaceOrder withId(Integer id) {
        this.id = id;
        return this;
    }

}

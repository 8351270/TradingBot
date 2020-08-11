
package com.tradingbot.entity.order.request.neworder;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.json.JSONObject;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "method",
    "params",
    "id"
})
public class PlaceNewOrder extends JSONObject implements Serializable
{

    @JsonProperty("method")
    private Integer method;
    @JsonProperty("params")
    private Params params;
    @JsonProperty("id")
    private Integer id;
    private final static long serialVersionUID = -5098475604088403243L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PlaceNewOrder() {
        this.params = new Params();
    }

    /**
     * 
     * @param method
     * @param id
     * @param params
     */
    public PlaceNewOrder(Integer method, Integer id) {
        super();
        this.method = method;
        this.id = id;
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

    public PlaceNewOrder withMethod(Integer method) {
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

    public PlaceNewOrder withParams(Params params) {
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

    public PlaceNewOrder withId(Integer id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "PlaceNewOrder{" +
                "method=" + method +
                ", params=" + params.toString() +
                ", id=" + id +
                '}';
    }
}

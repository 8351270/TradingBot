
package com.tradingbot.entity.order.request.updateorder.inner;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "orderId",
    "price",
    "size"
})
public class Params_ implements Serializable
{

    @JsonProperty("orderId")
    private Long orderId;
    @JsonProperty("price")
    private Price price;
    @JsonProperty("size")
    private Integer size;
    private final static long serialVersionUID = -3598360265558032833L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Params_() {
        this.price = new Price();
    }

    /**
     * 
     * @param size
     * @param orderId
     * @param price
     */
    public Params_(Long orderId, Price price, Integer size) {
        super();
        this.orderId = orderId;
        this.price = price;
        this.size = size;
    }

    @JsonProperty("orderId")
    public Long getOrderId() {
        return orderId;
    }

    @JsonProperty("orderId")
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Params_ withOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    @JsonProperty("price")
    public Price getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Price price) {
        this.price = price;
    }

    public Params_ withPrice(Price price) {
        this.price = price;
        return this;
    }

    @JsonProperty("size")
    public Integer getSize() {
        return size;
    }

    @JsonProperty("size")
    public void setSize(Integer size) {
        this.size = size;
    }

    public Params_ withSize(Integer size) {
        this.size = size;
        return this;
    }

}


package com.tradingbot.entity.order.request.neworder.inner;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "instrument",
    "price",
    "size",
    "type",
    "timeInForce",
    "side"
})
public class Params_ implements Serializable
{

    @JsonProperty("instrument")
    private Integer instrument;
    @JsonProperty("price")
    private Price price;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("type")
    private String type;
    @JsonProperty("timeInForce")
    private String timeInForce;
    @JsonProperty("side")
    private String side;
    private final static long serialVersionUID = -5928613657645562346L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Params_() {
        this.price = new Price();
    }

    /**
     * 
     * @param side
     * @param size
     * @param price
     * @param instrument
     * @param type
     * @param timeInForce
     */
    public Params_(Integer instrument, Price price, Integer size, String type, String timeInForce, String side) {
        super();
        this.instrument = instrument;
        this.price = price;
        this.size = size;
        this.type = type;
        this.timeInForce = timeInForce;
        this.side = side;
    }

    @JsonProperty("instrument")
    public Integer getInstrument() {
        return instrument;
    }

    @JsonProperty("instrument")
    public void setInstrument(Integer instrument) {
        this.instrument = instrument;
    }

    public Params_ withInstrument(Integer instrument) {
        this.instrument = instrument;
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
    public Integer geze() {
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

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    public Params_ withType(String type) {
        this.type = type;
        return this;
    }

    @JsonProperty("timeInForce")
    public String getTimeInForce() {
        return timeInForce;
    }

    @JsonProperty("timeInForce")
    public void setTimeInForce(String timeInForce) {
        this.timeInForce = timeInForce;
    }

    public Params_ withTimeInForce(String timeInForce) {
        this.timeInForce = timeInForce;
        return this;
    }

    @JsonProperty("side")
    public String getSide() {
        return side;
    }

    @JsonProperty("side")
    public void setSide(String side) {
        this.side = side;
    }

    public Params_ withSide(String side) {
        this.side = side;
        return this;
    }

    @Override
    public String toString() {
        return "Params_{" +
                "instrument=" + instrument +
                ", price=" + price +
                ", size=" + size +
                ", type='" + type + '\'' +
                ", timeInForce='" + timeInForce + '\'' +
                ", side='" + side + '\'' +
                '}';
    }
}

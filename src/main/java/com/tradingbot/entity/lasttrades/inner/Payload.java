
package com.tradingbot.entity.lasttrades.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payload {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("side")
    @Expose
    private String side;
    @SerializedName("price")
    @Expose
    private Price price;
    @SerializedName("size")
    @Expose
    private Long size;
    @SerializedName("time")
    @Expose
    private Time time;
    @SerializedName("instrumentId")
    @Expose
    private Long instrumentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Payload withId(Long id) {
        this.id = id;
        return this;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Payload withSide(String side) {
        this.side = side;
        return this;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Payload withPrice(Price price) {
        this.price = price;
        return this;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Payload withSize(Long size) {
        this.size = size;
        return this;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Payload withTime(Time time) {
        this.time = time;
        return this;
    }

    public Long getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(Long instrumentId) {
        this.instrumentId = instrumentId;
    }

    public Payload withInstrumentId(Long instrumentId) {
        this.instrumentId = instrumentId;
        return this;
    }

}

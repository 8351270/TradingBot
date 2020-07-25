
package com.tradingbot.entity.order.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payload {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("accountId")
    @Expose
    private Long accountId;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("timeInForce")
    @Expose
    private String timeInForce;
    @SerializedName("side")
    @Expose
    private String side;
    @SerializedName("initialSize")
    @Expose
    private Long initialSize;
    @SerializedName("remainingSize")
    @Expose
    private Long remainingSize;
    @SerializedName("price")
    @Expose
    private Price price;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("updateTime")
    @Expose
    private UpdateTime updateTime;
    @SerializedName("creationTime")
    @Expose
    private CreationTime creationTime;
    @SerializedName("instrument")
    @Expose
    private Long instrument;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Payload() {
    }

    /**
     * 
     * @param accountId
     * @param side
     * @param creationTime
     * @param initialSize
     * @param price
     * @param updateTime
     * @param instrument
     * @param id
     * @param remainingSize
     * @param type
     * @param timeInForce
     * @param status
     */
    public Payload(Long id, Long accountId, String type, String timeInForce, String side, Long initialSize, Long remainingSize, Price price, String status, UpdateTime updateTime, CreationTime creationTime, Long instrument) {
        super();
        this.id = id;
        this.accountId = accountId;
        this.type = type;
        this.timeInForce = timeInForce;
        this.side = side;
        this.initialSize = initialSize;
        this.remainingSize = remainingSize;
        this.price = price;
        this.status = status;
        this.updateTime = updateTime;
        this.creationTime = creationTime;
        this.instrument = instrument;
    }

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

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Payload withAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Payload withType(String type) {
        this.type = type;
        return this;
    }

    public String getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(String timeInForce) {
        this.timeInForce = timeInForce;
    }

    public Payload withTimeInForce(String timeInForce) {
        this.timeInForce = timeInForce;
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

    public Long getInitialSize() {
        return initialSize;
    }

    public void setInitialSize(Long initialSize) {
        this.initialSize = initialSize;
    }

    public Payload withInitialSize(Long initialSize) {
        this.initialSize = initialSize;
        return this;
    }

    public Long getRemainingSize() {
        return remainingSize;
    }

    public void setRemainingSize(Long remainingSize) {
        this.remainingSize = remainingSize;
    }

    public Payload withRemainingSize(Long remainingSize) {
        this.remainingSize = remainingSize;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Payload withStatus(String status) {
        this.status = status;
        return this;
    }

    public UpdateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(UpdateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Payload withUpdateTime(UpdateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public CreationTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(CreationTime creationTime) {
        this.creationTime = creationTime;
    }

    public Payload withCreationTime(CreationTime creationTime) {
        this.creationTime = creationTime;
        return this;
    }

    public Long getInstrument() {
        return instrument;
    }

    public void setInstrument(Long instrument) {
        this.instrument = instrument;
    }

    public Payload withInstrument(Long instrument) {
        this.instrument = instrument;
        return this;
    }

}
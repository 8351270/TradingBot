
package com.tradingbot.entity.positions.inner;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "accountId",
    "instrumentId",
    "size",
    "unrealizedPnl",
    "realizedPnl",
    "margin",
    "maxRemovableMargin",
    "entryPrice",
    "entryNotionalValue",
    "currentNotionalValue",
    "partialLiquidationPrice",
    "fullLiquidationPrice",
    "updateTime"
})
public class Payload implements Serializable
{

    @JsonProperty("accountId")
    private Long accountId;
    @JsonProperty("instrumentId")
    private Long instrumentId;
    @JsonProperty("size")
    private Integer size;
    @JsonProperty("unrealizedPnl")
    private UnrealizedPnl unrealizedPnl;
    @JsonProperty("realizedPnl")
    private RealizedPnl realizedPnl;
    @JsonProperty("margin")
    private Margin margin;
    @JsonProperty("maxRemovableMargin")
    private MaxRemovableMargin maxRemovableMargin;
    @JsonProperty("entryPrice")
    private EntryPrice entryPrice;
    @JsonProperty("entryNotionalValue")
    private EntryNotionalValue entryNotionalValue;
    @JsonProperty("currentNotionalValue")
    private CurrentNotionalValue currentNotionalValue;
    @JsonProperty("partialLiquidationPrice")
    private PartialLiquidationPrice partialLiquidationPrice;
    @JsonProperty("fullLiquidationPrice")
    private FullLiquidationPrice fullLiquidationPrice;
    @JsonProperty("updateTime")
    private UpdateTime updateTime;
    private final static long serialVersionUID = 7774239582037562135L;

    /**
     * No args constructor for use in serialization
     *
     */
//    public Payload() {
//    }


    public Payload() {
        unrealizedPnl =  new UnrealizedPnl();
        realizedPnl =  new RealizedPnl();
        margin =  new Margin();
        maxRemovableMargin = new MaxRemovableMargin();
        entryPrice = new EntryPrice();
        entryNotionalValue = new EntryNotionalValue();
        currentNotionalValue = new CurrentNotionalValue();
        updateTime = new UpdateTime();
    }
    /**
     * 
     * @param entryNotionalValue
     * @param margin
     * @param instrumentId
     * @param updateTime
     * @param maxRemovableMargin
     * @param currentNotionalValue
     * @param entryPrice
     * @param partialLiquidationPrice
     * @param accountId
     * @param fullLiquidationPrice
     * @param size
     * @param unrealizedPnl
     * @param realizedPnl
     */
    public Payload(Long accountId, Long instrumentId, Integer size, UnrealizedPnl unrealizedPnl, RealizedPnl realizedPnl, Margin margin, MaxRemovableMargin maxRemovableMargin, EntryPrice entryPrice, EntryNotionalValue entryNotionalValue, CurrentNotionalValue currentNotionalValue, PartialLiquidationPrice partialLiquidationPrice, FullLiquidationPrice fullLiquidationPrice, UpdateTime updateTime) {
        super();
        this.accountId = accountId;
        this.instrumentId = instrumentId;
        this.size = size;
        this.unrealizedPnl = unrealizedPnl;
        this.realizedPnl = realizedPnl;
        this.margin = margin;
        this.maxRemovableMargin = maxRemovableMargin;
        this.entryPrice = entryPrice;
        this.entryNotionalValue = entryNotionalValue;
        this.currentNotionalValue = currentNotionalValue;
        this.partialLiquidationPrice = partialLiquidationPrice;
        this.fullLiquidationPrice = fullLiquidationPrice;
        this.updateTime = updateTime;
    }

    @JsonProperty("accountId")
    public Long getAccountId() {
        return accountId;
    }

    @JsonProperty("accountId")
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Payload withAccountId(Long accountId) {
        this.accountId = accountId;
        return this;
    }

    @JsonProperty("instrumentId")
    public Long getInstrumentId() {
        return instrumentId;
    }

    @JsonProperty("instrumentId")
    public void setInstrumentId(Long instrumentId) {
        this.instrumentId = instrumentId;
    }

    public Payload withInstrumentId(Long instrumentId) {
        this.instrumentId = instrumentId;
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

    public Payload withSize(Integer size) {
        this.size = size;
        return this;
    }

    @JsonProperty("unrealizedPnl")
    public UnrealizedPnl getUnrealizedPnl() {
        return unrealizedPnl;
    }

    @JsonProperty("unrealizedPnl")
    public void setUnrealizedPnl(UnrealizedPnl unrealizedPnl) {
        this.unrealizedPnl = unrealizedPnl;
    }

    public Payload withUnrealizedPnl(UnrealizedPnl unrealizedPnl) {
        this.unrealizedPnl = unrealizedPnl;
        return this;
    }

    @JsonProperty("realizedPnl")
    public RealizedPnl getRealizedPnl() {
        return realizedPnl;
    }

    @JsonProperty("realizedPnl")
    public void setRealizedPnl(RealizedPnl realizedPnl) {
        this.realizedPnl = realizedPnl;
    }

    public Payload withRealizedPnl(RealizedPnl realizedPnl) {
        this.realizedPnl = realizedPnl;
        return this;
    }

    @JsonProperty("margin")
    public Margin getMargin() {
        return margin;
    }

    @JsonProperty("margin")
    public void setMargin(Margin margin) {
        this.margin = margin;
    }

    public Payload withMargin(Margin margin) {
        this.margin = margin;
        return this;
    }

    @JsonProperty("maxRemovableMargin")
    public MaxRemovableMargin getMaxRemovableMargin() {
        return maxRemovableMargin;
    }

    @JsonProperty("maxRemovableMargin")
    public void setMaxRemovableMargin(MaxRemovableMargin maxRemovableMargin) {
        this.maxRemovableMargin = maxRemovableMargin;
    }

    public Payload withMaxRemovableMargin(MaxRemovableMargin maxRemovableMargin) {
        this.maxRemovableMargin = maxRemovableMargin;
        return this;
    }

    @JsonProperty("entryPrice")
    public EntryPrice getEntryPrice() {
        return entryPrice;
    }

    @JsonProperty("entryPrice")
    public void setEntryPrice(EntryPrice entryPrice) {
        this.entryPrice = entryPrice;
    }

    public Payload withEntryPrice(EntryPrice entryPrice) {
        this.entryPrice = entryPrice;
        return this;
    }

    @JsonProperty("entryNotionalValue")
    public EntryNotionalValue getEntryNotionalValue() {
        return entryNotionalValue;
    }

    @JsonProperty("entryNotionalValue")
    public void setEntryNotionalValue(EntryNotionalValue entryNotionalValue) {
        this.entryNotionalValue = entryNotionalValue;
    }

    public Payload withEntryNotionalValue(EntryNotionalValue entryNotionalValue) {
        this.entryNotionalValue = entryNotionalValue;
        return this;
    }

    @JsonProperty("currentNotionalValue")
    public CurrentNotionalValue getCurrentNotionalValue() {
        return currentNotionalValue;
    }

    @JsonProperty("currentNotionalValue")
    public void setCurrentNotionalValue(CurrentNotionalValue currentNotionalValue) {
        this.currentNotionalValue = currentNotionalValue;
    }

    public Payload withCurrentNotionalValue(CurrentNotionalValue currentNotionalValue) {
        this.currentNotionalValue = currentNotionalValue;
        return this;
    }

    @JsonProperty("partialLiquidationPrice")
    public PartialLiquidationPrice getPartialLiquidationPrice() {
        return partialLiquidationPrice;
    }

    @JsonProperty("partialLiquidationPrice")
    public void setPartialLiquidationPrice(PartialLiquidationPrice partialLiquidationPrice) {
        this.partialLiquidationPrice = partialLiquidationPrice;
    }

    public Payload withPartialLiquidationPrice(PartialLiquidationPrice partialLiquidationPrice) {
        this.partialLiquidationPrice = partialLiquidationPrice;
        return this;
    }

    @JsonProperty("fullLiquidationPrice")
    public FullLiquidationPrice getFullLiquidationPrice() {
        return fullLiquidationPrice;
    }

    @JsonProperty("fullLiquidationPrice")
    public void setFullLiquidationPrice(FullLiquidationPrice fullLiquidationPrice) {
        this.fullLiquidationPrice = fullLiquidationPrice;
    }

    public Payload withFullLiquidationPrice(FullLiquidationPrice fullLiquidationPrice) {
        this.fullLiquidationPrice = fullLiquidationPrice;
        return this;
    }

    @JsonProperty("updateTime")
    public UpdateTime getUpdateTime() {
        return updateTime;
    }

    @JsonProperty("updateTime")
    public void setUpdateTime(UpdateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Payload withUpdateTime(UpdateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

}

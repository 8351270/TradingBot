
package com.tradingbot.entity.positions.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payload {

    @SerializedName("accountId")
    @Expose
    private Long accountId;
    @SerializedName("instrumentId")
    @Expose
    private Long instrumentId;
    @SerializedName("size")
    @Expose
    private Long size;
    @SerializedName("unrealizedPnl")
    @Expose
    private UnrealizedPnl unrealizedPnl;
    @SerializedName("realizedPnl")
    @Expose
    private RealizedPnl realizedPnl;
    @SerializedName("margin")
    @Expose
    private Margin margin;
    @SerializedName("maxRemovableMargin")
    @Expose
    private MaxRemovableMargin maxRemovableMargin;
    @SerializedName("entryPrice")
    @Expose
    private EntryPrice entryPrice;
    @SerializedName("entryNotionalValue")
    @Expose
    private EntryNotionalValue entryNotionalValue;
    @SerializedName("currentNotionalValue")
    @Expose
    private CurrentNotionalValue currentNotionalValue;
    @SerializedName("partialLiquidationPrice")
    @Expose
    private Object partialLiquidationPrice;
    @SerializedName("fullLiquidationPrice")
    @Expose
    private Object fullLiquidationPrice;
    @SerializedName("updateTime")
    @Expose
    private UpdateTime updateTime;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Payload() {
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
    public Payload(Long accountId, Long instrumentId, Long size, UnrealizedPnl unrealizedPnl, RealizedPnl realizedPnl, Margin margin, MaxRemovableMargin maxRemovableMargin, EntryPrice entryPrice, EntryNotionalValue entryNotionalValue, CurrentNotionalValue currentNotionalValue, Object partialLiquidationPrice, Object fullLiquidationPrice, UpdateTime updateTime) {
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

    public UnrealizedPnl getUnrealizedPnl() {
        return unrealizedPnl;
    }

    public void setUnrealizedPnl(UnrealizedPnl unrealizedPnl) {
        this.unrealizedPnl = unrealizedPnl;
    }

    public Payload withUnrealizedPnl(UnrealizedPnl unrealizedPnl) {
        this.unrealizedPnl = unrealizedPnl;
        return this;
    }

    public RealizedPnl getRealizedPnl() {
        return realizedPnl;
    }

    public void setRealizedPnl(RealizedPnl realizedPnl) {
        this.realizedPnl = realizedPnl;
    }

    public Payload withRealizedPnl(RealizedPnl realizedPnl) {
        this.realizedPnl = realizedPnl;
        return this;
    }

    public Margin getMargin() {
        return margin;
    }

    public void setMargin(Margin margin) {
        this.margin = margin;
    }

    public Payload withMargin(Margin margin) {
        this.margin = margin;
        return this;
    }

    public MaxRemovableMargin getMaxRemovableMargin() {
        return maxRemovableMargin;
    }

    public void setMaxRemovableMargin(MaxRemovableMargin maxRemovableMargin) {
        this.maxRemovableMargin = maxRemovableMargin;
    }

    public Payload withMaxRemovableMargin(MaxRemovableMargin maxRemovableMargin) {
        this.maxRemovableMargin = maxRemovableMargin;
        return this;
    }

    public EntryPrice getEntryPrice() {
        return entryPrice;
    }

    public void setEntryPrice(EntryPrice entryPrice) {
        this.entryPrice = entryPrice;
    }

    public Payload withEntryPrice(EntryPrice entryPrice) {
        this.entryPrice = entryPrice;
        return this;
    }

    public EntryNotionalValue getEntryNotionalValue() {
        return entryNotionalValue;
    }

    public void setEntryNotionalValue(EntryNotionalValue entryNotionalValue) {
        this.entryNotionalValue = entryNotionalValue;
    }

    public Payload withEntryNotionalValue(EntryNotionalValue entryNotionalValue) {
        this.entryNotionalValue = entryNotionalValue;
        return this;
    }

    public CurrentNotionalValue getCurrentNotionalValue() {
        return currentNotionalValue;
    }

    public void setCurrentNotionalValue(CurrentNotionalValue currentNotionalValue) {
        this.currentNotionalValue = currentNotionalValue;
    }

    public Payload withCurrentNotionalValue(CurrentNotionalValue currentNotionalValue) {
        this.currentNotionalValue = currentNotionalValue;
        return this;
    }

    public Object getPartialLiquidationPrice() {
        return partialLiquidationPrice;
    }

    public void setPartialLiquidationPrice(Object partialLiquidationPrice) {
        this.partialLiquidationPrice = partialLiquidationPrice;
    }

    public Payload withPartialLiquidationPrice(Object partialLiquidationPrice) {
        this.partialLiquidationPrice = partialLiquidationPrice;
        return this;
    }

    public Object getFullLiquidationPrice() {
        return fullLiquidationPrice;
    }

    public void setFullLiquidationPrice(Object fullLiquidationPrice) {
        this.fullLiquidationPrice = fullLiquidationPrice;
    }

    public Payload withFullLiquidationPrice(Object fullLiquidationPrice) {
        this.fullLiquidationPrice = fullLiquidationPrice;
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

}

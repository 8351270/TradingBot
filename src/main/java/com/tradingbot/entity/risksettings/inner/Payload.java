
package com.tradingbot.entity.risksettings.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Payload {

    @SerializedName("instrumentId")
    @Expose
    private Long instrumentId;
    @SerializedName("marginMode")
    @Expose
    private String marginMode;
    @SerializedName("leverage")
    @Expose
    private Leverage leverage;
    @SerializedName("riskLimit")
    @Expose
    private RiskLimit riskLimit;
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
     * @param leverage
     * @param riskLimit
     * @param instrumentId
     * @param updateTime
     * @param marginMode
     */
    public Payload(Long instrumentId, String marginMode, Leverage leverage, RiskLimit riskLimit, UpdateTime updateTime) {
        super();
        this.instrumentId = instrumentId;
        this.marginMode = marginMode;
        this.leverage = leverage;
        this.riskLimit = riskLimit;
        this.updateTime = updateTime;
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

    public String getMarginMode() {
        return marginMode;
    }

    public void setMarginMode(String marginMode) {
        this.marginMode = marginMode;
    }

    public Payload withMarginMode(String marginMode) {
        this.marginMode = marginMode;
        return this;
    }

    public Leverage getLeverage() {
        return leverage;
    }

    public void setLeverage(Leverage leverage) {
        this.leverage = leverage;
    }

    public Payload withLeverage(Leverage leverage) {
        this.leverage = leverage;
        return this;
    }

    public RiskLimit getRiskLimit() {
        return riskLimit;
    }

    public void setRiskLimit(RiskLimit riskLimit) {
        this.riskLimit = riskLimit;
    }

    public Payload withRiskLimit(RiskLimit riskLimit) {
        this.riskLimit = riskLimit;
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

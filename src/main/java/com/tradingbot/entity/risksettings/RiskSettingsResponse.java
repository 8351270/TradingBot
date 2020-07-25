
package com.tradingbot.entity.risksettings;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tradingbot.entity.risksettings.inner.Result;

public class RiskSettingsResponse {

    @SerializedName("result")
    @Expose
    private Result result;

    /**
     * No args constructor for use in serialization
     * 
     */
    public RiskSettingsResponse() {
    }

    /**
     * 
     * @param result
     */
    public RiskSettingsResponse(Result result) {
        super();
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public RiskSettingsResponse withResult(Result result) {
        this.result = result;
        return this;
    }

}

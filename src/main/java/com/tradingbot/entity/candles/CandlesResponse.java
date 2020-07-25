
package com.tradingbot.entity.candles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tradingbot.entity.candles.inner.Result;

public class CandlesResponse {

    @SerializedName("result")
    @Expose
    private Result result;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CandlesResponse() {
    }

    /**
     * 
     * @param result
     */
    public CandlesResponse(Result result) {
        super();
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public CandlesResponse withResult(Result result) {
        this.result = result;
        return this;
    }

}


package com.tradingbot.entity.tickers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tradingbot.entity.tickers.inner.Result;

public class TickersResponse {

    @SerializedName("result")
    @Expose
    private Result result;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TickersResponse() {
    }

    /**
     * 
     * @param result
     */
    public TickersResponse(Result result) {
        super();
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public TickersResponse withResult(Result result) {
        this.result = result;
        return this;
    }

}

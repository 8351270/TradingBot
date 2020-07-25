
package com.tradingbot.entity.instruments;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tradingbot.entity.instruments.inner.Result;

public class InstrumentsResponse {

    @SerializedName("result")
    @Expose
    private Result result;

    /**
     * No args constructor for use in serialization
     * 
     */
    public InstrumentsResponse() {
    }

    /**
     * 
     * @param result
     */
    public InstrumentsResponse(Result result) {
        super();
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public InstrumentsResponse withResult(Result result) {
        this.result = result;
        return this;
    }

}

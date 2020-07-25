
package com.tradingbot.entity.positions;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tradingbot.entity.positions.inner.Result;

public class PositionsResponse {

    @SerializedName("result")
    @Expose
    private Result result;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PositionsResponse() {
    }

    /**
     * 
     * @param result
     */
    public PositionsResponse(Result result) {
        super();
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public PositionsResponse withResult(Result result) {
        this.result = result;
        return this;
    }

}

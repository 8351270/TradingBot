
package com.tradingbot.entity.orderfils;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tradingbot.entity.orderfils.inner.Result;

public class OrderFillsResponse {

    @SerializedName("result")
    @Expose
    private Result result;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OrderFillsResponse() {
    }

    /**
     * 
     * @param result
     */
    public OrderFillsResponse(Result result) {
        super();
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public OrderFillsResponse withResult(Result result) {
        this.result = result;
        return this;
    }

}


package com.tradingbot.entity.balance;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tradingbot.entity.balance.inner.Result;

public class BalanceResponse {

    @SerializedName("result")
    @Expose
    private Result result;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BalanceResponse() {
    }

    /**
     * 
     * @param result
     */
    public BalanceResponse(Result result) {
        super();
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public BalanceResponse withResult(Result result) {
        this.result = result;
        return this;
    }

}

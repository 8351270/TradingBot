
package com.tradingbot.entity.lasttrades;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tradingbot.entity.lasttrades.inner.Result;

public class LastTradesResponse {

    @SerializedName("result")
    @Expose
    private Result result;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public LastTradesResponse withResult(Result result) {
        this.result = result;
        return this;
    }

}

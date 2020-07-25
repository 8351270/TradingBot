
package com.tradingbot.entity.order;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tradingbot.entity.order.inner.Result;

public class OrdersResponse {

    @SerializedName("result")
    @Expose
    private Result result;

    public OrdersResponse() {
    }

    public OrdersResponse(Result result) {
        super();
        this.result = result;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public OrdersResponse withResult(Result result) {
        this.result = result;
        return this;
    }

}

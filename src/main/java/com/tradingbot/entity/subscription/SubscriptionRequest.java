package com.tradingbot.entity.subscription;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubscriptionRequest {

    @SerializedName("method")
    @Expose
    private Long method;
    @SerializedName("params")
    @Expose
    private Params params;
    @SerializedName("id")
    @Expose
    private Long id;

    public Long getMethod() {
        return method;
    }

    public void setMethod(Long method) {
        this.method = method;
    }

    public SubscriptionRequest withMethod(Long method) {
        this.method = method;
        return this;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public SubscriptionRequest withParams(Params params) {
        this.params = params;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SubscriptionRequest withId(Long id) {
        this.id = id;
        return this;
    }

}

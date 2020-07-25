package com.tradingbot.entity.authentication.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tradingbot.entity.authentication.response.inner.Result;

public class AuthenticationResponse {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("result")
    @Expose
    private Result result;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuthenticationResponse withId(Long id) {
        this.id = id;
        return this;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public AuthenticationResponse withResult(Result result) {
        this.result = result;
        return this;
    }

}
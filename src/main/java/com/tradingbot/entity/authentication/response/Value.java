package com.tradingbot.entity.authentication.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Value {

    @SerializedName("permissions")
    @Expose
    private List<String> permissions = null;
    @SerializedName("startTime")
    @Expose
    private StartTime startTime;
    @SerializedName("expirationTime")
    @Expose
    private ExpirationTime expirationTime;
    @SerializedName("userId")
    @Expose
    private Long userId;

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public Value withPermissions(List<String> permissions) {
        this.permissions = permissions;
        return this;
    }

    public StartTime getStartTime() {
        return startTime;
    }

    public void setStartTime(StartTime startTime) {
        this.startTime = startTime;
    }

    public Value withStartTime(StartTime startTime) {
        this.startTime = startTime;
        return this;
    }

    public ExpirationTime getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(ExpirationTime expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Value withExpirationTime(ExpirationTime expirationTime) {
        this.expirationTime = expirationTime;
        return this;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Value withUserId(Long userId) {
        this.userId = userId;
        return this;
    }

}

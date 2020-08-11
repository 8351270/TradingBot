package com.tradingbot.entity.channel;

public class Channel {

    private int id;
    private String name;
    private boolean subscribed;

    public Channel(int id, String name) {
        this.id = id;
        this.name = name;
        this.subscribed = false;
    }

    public String toSubscribeString() {
    return "{\"method\":1,\"params\":{\"channel\":\"" + this.name +"\"" +"},\"id\":" + this.id + "}";
    }

    public String toUnSubscribeString() {
        return "{\"method\":2,\"params\":{\"channel\":\"" + this.name +"\"" +"},\"id\":" + this.id + "}";
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSubscribed() {
        return subscribed;
    }

    public void setSubscribed(boolean subscribed) {
        this.subscribed = subscribed;
    }
}

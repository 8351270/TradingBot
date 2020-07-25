
package com.tradingbot.entity.tickers.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class _1 {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("lastPrice")
    @Expose
    private LastPrice lastPrice;
    @SerializedName("priceChange24h")
    @Expose
    private Float priceChange24h;
    @SerializedName("lowPrice24h")
    @Expose
    private LowPrice24h lowPrice24h;
    @SerializedName("highPrice24h")
    @Expose
    private HighPrice24h highPrice24h;
    @SerializedName("volume24h")
    @Expose
    private Long volume24h;
    @SerializedName("markPrice")
    @Expose
    private MarkPrice markPrice;

    /**
     * No args constructor for use in serialization
     * 
     */
    public _1() {
    }

    /**
     * 
     * @param markPrice
     * @param lowPrice24h
     * @param volume24h
     * @param priceChange24h
     * @param highPrice24h
     * @param id
     * @param lastPrice
     */
    public _1(Long id, LastPrice lastPrice, Float priceChange24h, LowPrice24h lowPrice24h, HighPrice24h highPrice24h, Long volume24h, MarkPrice markPrice) {
        super();
        this.id = id;
        this.lastPrice = lastPrice;
        this.priceChange24h = priceChange24h;
        this.lowPrice24h = lowPrice24h;
        this.highPrice24h = highPrice24h;
        this.volume24h = volume24h;
        this.markPrice = markPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public _1 withId(Long id) {
        this.id = id;
        return this;
    }

    public LastPrice getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(LastPrice lastPrice) {
        this.lastPrice = lastPrice;
    }

    public _1 withLastPrice(LastPrice lastPrice) {
        this.lastPrice = lastPrice;
        return this;
    }

    public Float getPriceChange24h() {
        return priceChange24h;
    }

    public void setPriceChange24h(Float priceChange24h) {
        this.priceChange24h = priceChange24h;
    }

    public _1 withPriceChange24h(Float priceChange24h) {
        this.priceChange24h = priceChange24h;
        return this;
    }

    public LowPrice24h getLowPrice24h() {
        return lowPrice24h;
    }

    public void setLowPrice24h(LowPrice24h lowPrice24h) {
        this.lowPrice24h = lowPrice24h;
    }

    public _1 withLowPrice24h(LowPrice24h lowPrice24h) {
        this.lowPrice24h = lowPrice24h;
        return this;
    }

    public HighPrice24h getHighPrice24h() {
        return highPrice24h;
    }

    public void setHighPrice24h(HighPrice24h highPrice24h) {
        this.highPrice24h = highPrice24h;
    }

    public _1 withHighPrice24h(HighPrice24h highPrice24h) {
        this.highPrice24h = highPrice24h;
        return this;
    }

    public Long getVolume24h() {
        return volume24h;
    }

    public void setVolume24h(Long volume24h) {
        this.volume24h = volume24h;
    }

    public _1 withVolume24h(Long volume24h) {
        this.volume24h = volume24h;
        return this;
    }

    public MarkPrice getMarkPrice() {
        return markPrice;
    }

    public void setMarkPrice(MarkPrice markPrice) {
        this.markPrice = markPrice;
    }

    public _1 withMarkPrice(MarkPrice markPrice) {
        this.markPrice = markPrice;
        return this;
    }

}

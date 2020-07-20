package com.tradingbot.entity.orderBook;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderBookItem {

    private Long size;
    private Long mantissa;
    private Integer exponent;

    public OrderBookItem(JSONObject jsonItem) throws JSONException {
        this.size = jsonItem.getLong("size");
        this.mantissa = jsonItem.getJSONObject("price").getLong("mantissa");
        this.exponent = jsonItem.getJSONObject("price").getInt("exponent");
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Long getMantissa() {
        return mantissa;
    }

    public void setMantissa(Long mantissa) {
        this.mantissa = mantissa;
    }

    public Integer getExponent() {
        return exponent;
    }

    public void setExponent(Integer exponent) {
        this.exponent = exponent;
    }

    @Override
    public String toString() {
        return "OrderBookItem{" +
                "size=" + size +
                ", mantissa=" + mantissa +
                ", exponent=" + exponent +
                '}';
    }
}
package com.tradingbot.entity.orderbook;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

    public static BigDecimal toBigDecimal(OrderBookItem orderBookItem) {
        BigDecimal ret = new BigDecimal(orderBookItem.getMantissa());
        int exponent = orderBookItem.getExponent() * -1;
        BigDecimal base = new BigDecimal(10);
        base = base.pow(exponent);
        ret = ret.divide(base, RoundingMode.FLOOR);
        return ret;

    }
}

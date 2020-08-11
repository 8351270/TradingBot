package com.tradingbot.entity.order;

import java.math.BigDecimal;
import java.util.Date;

public class OpenOrder {

    private Integer internalOrderId;
    private Long exchangeOrderId;
    private Integer totalSize;
    private Integer remainingSize;
    private Long updateTime;
    private BigDecimal price;
    // set true once there is a confirmation from order fills channel
    private boolean orderFilled;

    public OpenOrder(Integer orderId, Integer size, BigDecimal price){
        this.internalOrderId = orderId;
        this.totalSize = size;
        this.remainingSize = size;
        this.price = price;
        this.updateTime = new Date().getTime()/1000;
    }

    public OpenOrder() {

    }


    @Override
    public String toString() {
        return "OpenOrder{" +
                "internalOrderId=" + internalOrderId +
                ", exchangeOrderId=" + exchangeOrderId +
                ", totalSize=" + totalSize +
                ", remainingSize=" + remainingSize +
                ", updateTime=" + updateTime +
                ", price=" + price +
                ", is order confirmed from order fills channels ?: " + orderFilled +
                '}';
    }

    public Integer getInternalOrderId() {
        return internalOrderId;
    }

    public void setInternalOrderId(Integer internalOrderId) {
        this.internalOrderId = internalOrderId;
    }

    public Long getExchangeOrderId() {
        return exchangeOrderId;
    }

    public void setExchangeOrderId(Long exchangeOrderId) {
        this.exchangeOrderId = exchangeOrderId;
    }

    public Integer getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(Integer totalSize) {
        this.totalSize = totalSize;
    }

    public Integer getRemainingSize() {
        return remainingSize;
    }

    public void setRemainingSize(Integer remainingSize) {
        this.remainingSize = remainingSize;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isOrderFilled() {
        return orderFilled;
    }

    public void setOrderFilled(boolean orderFilled) {
        this.orderFilled = orderFilled;
    }
}

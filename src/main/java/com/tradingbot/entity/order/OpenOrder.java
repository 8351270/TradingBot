package com.tradingbot.entity.order;

import com.tradingbot.entity.order.response.OrderResponse;

import java.math.BigDecimal;
import java.util.Date;

public class OpenOrder {

    private Integer internalOrderId;
    private Long exchangeOrderId;
    private Integer totalSize;
    private Integer remainingSize;
    private Long updateTime;
    private BigDecimal price;

    // false when created and true once we have the OK response from de server
    private boolean isConfirmed;

    public OpenOrder(Integer orderId, Integer size, BigDecimal price){
        this.internalOrderId = orderId;
        this.totalSize = size;
        this.remainingSize = size;
        this.price = price;
        this.updateTime = new Date().getTime()/1000;
        this.isConfirmed = false;
    }

    public OpenOrder() {

    }

    public OpenOrder(OrderResponse order, int orderId) {
        this.setUpdateTime(order.getUpdateTime().getSeconds());
        this.setExchangeOrderId(order.getId());
        this.setInternalOrderId(orderId);
        this.setRemainingSize(order.getRemainingSize());
        this.setTotalSize((order.getInitialSize()));
        this.setPrice(toBigDecimal(order.getPrice().getMantissa(), order.getPrice().getExponent()));
        this.setConfirmed(true);
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
                ", is create/update request confirmed by exchange ?=" + isConfirmed +
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

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    //  TODO make a util class with this
    public static BigDecimal toBigDecimal(Long mantissa, Long exponent) {
        BigDecimal ret = new BigDecimal(mantissa);
        if (ret.compareTo(BigDecimal.valueOf(0)) == 0) {
            return ret;
        }
        exponent = exponent * -1;
        BigDecimal base = new BigDecimal(10);
        base = base.pow(Math.toIntExact(exponent));
        ret = ret.divide(base);
        return ret;

    }
}

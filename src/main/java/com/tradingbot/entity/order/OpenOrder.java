package com.tradingbot.entity.order;

import java.math.BigDecimal;

public class OpenOrder {

    private BigDecimal totalSize;
    private BigDecimal remainingSize;

    public BigDecimal getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(BigDecimal totalSize) {
        this.totalSize = totalSize;
    }

    public BigDecimal getRemainingSize() {
        return remainingSize;
    }

    public void setRemainingSize(BigDecimal remainingSize) {
        this.remainingSize = remainingSize;
    }
}

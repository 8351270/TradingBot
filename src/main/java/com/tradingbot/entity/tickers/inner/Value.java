
package com.tradingbot.entity.tickers.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("1")
    @Expose
    private com.tradingbot.entity.tickers.inner._1 _1;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Value() {
    }

    public Value(_1 _1) {
        super();
        this._1 = _1;
    }

    public _1 get1() {
        return _1;
    }

    public void set1(_1 _1) {
        this._1 = _1;
    }

    public Value with1(_1 _1) {
        this._1 = _1;
        return this;
    }

}

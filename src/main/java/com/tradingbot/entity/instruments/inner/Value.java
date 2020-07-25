
package com.tradingbot.entity.instruments.inner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("1")
    @Expose
    private _1 _1;
    @SerializedName("2")
    @Expose
    private _2 _2;
    @SerializedName("3")
    @Expose
    private _3 _3;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Value() {
    }

    /**
     * 
     * @param _1
     * @param _2
     * @param _3
     */
    public Value(_1 _1,_2 _2,_3 _3) {
        super();
        this._1 = _1;
        this._2 = _2;
        this._3 = _3;
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

    public _2 get2() {
        return _2;
    }

    public void set2(_2 _2) {
        this._2 = _2;
    }

    public Value with2(_2 _2) {
        this._2 = _2;
        return this;
    }

    public _3 get3() {
        return _3;
    }

    public void set3(_3 _3) {
        this._3 = _3;
    }

    public Value with3(_3 _3) {
        this._3 = _3;
        return this;
    }

}


package com.tradingbot.entity.balance;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "tag",
    "value"
})
public class Data implements Serializable
{

    @JsonProperty("tag")
    private String tag;
    @JsonProperty("value")
    private Value value;
    private final static long serialVersionUID = 7259566479180170720L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Data() {
    }

    /**
     * 
     * @param tag
     * @param value
     */
    public Data(String tag, Value value) {
        super();
        this.tag = tag;
        this.value = value;
    }

    @JsonProperty("tag")
    public String getTag() {
        return tag;
    }

    @JsonProperty("tag")
    public void setTag(String tag) {
        this.tag = tag;
    }

    public Data withTag(String tag) {
        this.tag = tag;
        return this;
    }

    @JsonProperty("value")
    public Value getValue() {
        return value;
    }

    @JsonProperty("value")
    public void setValue(Value value) {
        this.value = value;
    }

    public Data withValue(Value value) {
        this.value = value;
        return this;
    }

}

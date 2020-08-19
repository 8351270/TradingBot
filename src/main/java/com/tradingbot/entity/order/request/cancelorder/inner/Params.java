
package com.tradingbot.entity.order.request.cancelorder.inner;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data"
})
public class Params implements Serializable
{

    @JsonProperty("data")
    private Data data;
    private final static long serialVersionUID = -332267106763099735L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Params() {
        this.data =  new Data();
    }

    /**
     * 
     * @param data
     */
    public Params(Data data) {
        super();
        this.data = data;
    }

    @JsonProperty("data")
    public Data getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(Data data) {
        this.data = data;
    }

    public Params withData(Data data) {
        this.data = data;
        return this;
    }

}

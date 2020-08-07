
package com.tradingbot.entity.balance;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "result"
})
public class BalanceResponse
{

    @JsonProperty("result")
    private Result result;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BalanceResponse() {
    }

    /**
     * 
     * @param result
     */
    public BalanceResponse(Result result) {
        super();
        this.result = result;
    }

    @JsonProperty("result")
    public Result getResult() {
        return result;
    }

    @JsonProperty("result")
    public void setResult(Result result) {
        this.result = result;
    }

    public BalanceResponse withResult(Result result) {
        this.result = result;
        return this;
    }

}

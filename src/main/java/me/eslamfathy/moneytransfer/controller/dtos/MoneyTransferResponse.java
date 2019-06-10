package me.eslamfathy.moneytransfer.controller.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MoneyTransferResponse {
    @JsonProperty
    private boolean success;
    @JsonProperty
    private javax.ws.rs.core.Response.Status code;
    @JsonProperty
    private String message;

    public MoneyTransferResponse(boolean success, javax.ws.rs.core.Response.Status code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}

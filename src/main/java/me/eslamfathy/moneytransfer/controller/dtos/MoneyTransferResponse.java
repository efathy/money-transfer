package me.eslamfathy.moneytransfer.controller.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.ws.rs.core.Response;

public class MoneyTransferResponse {
    @JsonProperty
    private boolean success;
    @JsonProperty
    private Response.Status code;
    @JsonProperty
    private String message;

    public MoneyTransferResponse(boolean success, Response.Status code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}

package me.eslamfathy.moneytransfer.controller.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class TransferResponse {
    @JsonProperty
    private Long sourceAccountId;
    @JsonProperty
    private Long destinationAccountId;
    @JsonProperty
    private BigDecimal value;
}

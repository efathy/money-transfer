package me.eslamfathy.moneytransfer.dataaccess;

import me.eslamfathy.moneytransfer.model.Account;

import java.math.BigDecimal;

public interface AccountDataAccess {
    Account getAccount(Long accountId) throws AccountNotFoundException;

    Account createAccount(BigDecimal balance);

    boolean transfer(Long sourceId, Long destinationId, BigDecimal value);
}

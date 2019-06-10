package me.eslamfathy.moneytransfer.dataaccess;

import me.eslamfathy.moneytransfer.exceptions.AccountNotFoundException;
import me.eslamfathy.moneytransfer.exceptions.TransferFailureException;
import me.eslamfathy.moneytransfer.model.Account;

import java.math.BigDecimal;

public interface AccountDataAccess {
    Account getAccount(Long accountId) throws AccountNotFoundException;

    Account createAccount(BigDecimal balance);

    void transfer(Long sourceId, Long destinationId, BigDecimal value) throws TransferFailureException;
}

package me.eslamfathy.moneytransfer.service;

import me.eslamfathy.moneytransfer.dataaccess.AccountNotFoundException;
import me.eslamfathy.moneytransfer.model.Transfer;

import java.math.BigDecimal;

public interface MoneyTransferService {

    Transfer makeTransfer(Long sourceAccountId, Long destinationAccountId, BigDecimal value) throws AccountNotFoundException;
}

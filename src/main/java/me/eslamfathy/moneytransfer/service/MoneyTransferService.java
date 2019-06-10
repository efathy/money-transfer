package me.eslamfathy.moneytransfer.service;

import me.eslamfathy.moneytransfer.exceptions.MoneyTransferException;
import me.eslamfathy.moneytransfer.model.Transfer;

import java.math.BigDecimal;

public interface MoneyTransferService {

    Transfer makeTransfer(Long sourceAccountId, Long destinationAccountId, BigDecimal value) throws MoneyTransferException;
}

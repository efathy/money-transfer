package me.eslamfathy.moneytransfer.service.implementation;

import com.google.inject.Inject;
import me.eslamfathy.moneytransfer.dataaccess.AccountDataAccess;
import me.eslamfathy.moneytransfer.dataaccess.AccountNotFoundException;
import me.eslamfathy.moneytransfer.dataaccess.TransferDataAccess;
import me.eslamfathy.moneytransfer.model.Account;
import me.eslamfathy.moneytransfer.model.Transfer;
import me.eslamfathy.moneytransfer.service.MoneyTransferService;

import java.math.BigDecimal;

public class MoneyTransferServiceImpl implements MoneyTransferService {
    @Inject
    private TransferDataAccess transferDataAccess;
    @Inject
    private AccountDataAccess accountDataAccess;

    @Override
    public Transfer makeTransfer(Long sourceAccountId, Long destinationAccountId, BigDecimal value) throws AccountNotFoundException {
        Account sourceAccount = accountDataAccess.getAccount(sourceAccountId);
        Account destinationAccount = accountDataAccess.getAccount(destinationAccountId);
        accountDataAccess.transfer(sourceAccountId, destinationAccountId, value);
        return transferDataAccess.createTransfer(sourceAccount, destinationAccount, value);
    }
}

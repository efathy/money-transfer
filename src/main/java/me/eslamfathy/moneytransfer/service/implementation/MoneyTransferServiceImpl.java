package me.eslamfathy.moneytransfer.service.implementation;

import com.google.inject.Inject;
import me.eslamfathy.moneytransfer.dataaccess.AccountDataAccess;
import me.eslamfathy.moneytransfer.dataaccess.TransferDataAccess;
import me.eslamfathy.moneytransfer.exceptions.MoneyTransferException;
import me.eslamfathy.moneytransfer.exceptions.NoEnoughBalanceException;
import me.eslamfathy.moneytransfer.model.Account;
import me.eslamfathy.moneytransfer.model.Transfer;
import me.eslamfathy.moneytransfer.service.MoneyTransferService;

import java.math.BigDecimal;

public class MoneyTransferServiceImpl implements MoneyTransferService {
    private TransferDataAccess transferDataAccess;
    private AccountDataAccess accountDataAccess;

    @Inject
    MoneyTransferServiceImpl(TransferDataAccess transferDataAccess, AccountDataAccess accountDataAccess) {
        this.transferDataAccess = transferDataAccess;
        this.accountDataAccess = accountDataAccess;
    }

    @Override
    public Transfer makeTransfer(Long sourceAccountId, Long destinationAccountId, BigDecimal value) throws MoneyTransferException {
        Account sourceAccount = accountDataAccess.getAccount(sourceAccountId);
        if(sourceAccount.getBalance().compareTo(value) < 0 ){
            throw new NoEnoughBalanceException("Account id:" + sourceAccountId +" does not have enough balance");
        }
        Account destinationAccount = accountDataAccess.getAccount(destinationAccountId);
        accountDataAccess.transfer(sourceAccountId, destinationAccountId, value);
        return transferDataAccess.createTransfer(sourceAccount, destinationAccount, value);
    }
}

package me.eslamfathy.moneytransfer.service.implementation;

import me.eslamfathy.moneytransfer.dataaccess.implementation.AccountDataAccessImpl;
import me.eslamfathy.moneytransfer.dataaccess.implementation.TransferDataAccessImpl;
import me.eslamfathy.moneytransfer.exceptions.MoneyTransferException;
import me.eslamfathy.moneytransfer.exceptions.NoEnoughBalanceException;
import me.eslamfathy.moneytransfer.model.Account;
import me.eslamfathy.moneytransfer.model.Transfer;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class MoneyTransferServiceImplTest {

    @Test
    public void makeTransfer() throws MoneyTransferException {
        MoneyTransferServiceImpl moneyTransferService = new MoneyTransferServiceImpl(new TransferDataAccessImpl(),
                new AccountDataAccessImpl());
        AccountDataAccessImpl accountDataAccess = new AccountDataAccessImpl();
        Account account1 = accountDataAccess.createAccount(new BigDecimal(300));
        Account account2 = accountDataAccess.createAccount(new BigDecimal(0));

        Transfer transfer = moneyTransferService
                .makeTransfer(account1.getId(), account2.getId(), new BigDecimal("200"));

        Assert.assertNotNull(transfer);
        Assert.assertNotNull(transfer.getId());
        Assert.assertEquals(new BigDecimal("100.0000"), accountDataAccess.getAccount(account1.getId()).getBalance());
        Assert.assertEquals(new BigDecimal("200.0000"), accountDataAccess.getAccount(account2.getId()).getBalance());
    }

    @Test(expected = NoEnoughBalanceException.class)
    public void makeTransferNotEnoughBalance() throws MoneyTransferException {
        MoneyTransferServiceImpl moneyTransferService = new MoneyTransferServiceImpl(new TransferDataAccessImpl(),
                new AccountDataAccessImpl());
        AccountDataAccessImpl accountDataAccess = new AccountDataAccessImpl();
        Account account1 = accountDataAccess.createAccount(new BigDecimal(300));
        Account account2 = accountDataAccess.createAccount(new BigDecimal(0));

        moneyTransferService
                .makeTransfer(account1.getId(), account2.getId(), new BigDecimal("400"));
    }


    @Test(expected = NoEnoughBalanceException.class)
    public void makeTransferNotFoundAccount() throws MoneyTransferException {
        MoneyTransferServiceImpl moneyTransferService = new MoneyTransferServiceImpl(new TransferDataAccessImpl(),
                new AccountDataAccessImpl());
        AccountDataAccessImpl accountDataAccess = new AccountDataAccessImpl();
        Account account1 = accountDataAccess.createAccount(new BigDecimal(300));

        moneyTransferService
                .makeTransfer(account1.getId(), 100L, new BigDecimal("400"));
    }
}
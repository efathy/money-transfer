package me.eslamfathy.moneytransfer.dataaccess.implementation;

import me.eslamfathy.moneytransfer.exceptions.AccountNotFoundException;
import me.eslamfathy.moneytransfer.exceptions.TransferFailureException;
import me.eslamfathy.moneytransfer.model.Account;
import org.junit.Assert;
import org.junit.Test;

import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;

public class AccountDataAccessImplTest {

    @Test
    public void getExistingAccount() throws AccountNotFoundException {
        AccountDataAccessImpl accountDataAccess = new AccountDataAccessImpl();
        Account account = accountDataAccess.createAccount(new BigDecimal(0));
        Assert.assertNotNull(accountDataAccess.getAccount(account.getId()));
    }

    @Test(expected = AccountNotFoundException.class)
    public void getNonExistingAccount() throws AccountNotFoundException {
        AccountDataAccessImpl accountDataAccess = new AccountDataAccessImpl();
        accountDataAccess.getAccount(100L);
    }

    @Test
    public void createValidAccount() {
        AccountDataAccessImpl accountDataAccess = new AccountDataAccessImpl();
        Account account = accountDataAccess.createAccount(new BigDecimal(0));
        Assert.assertNotNull(account);
        Assert.assertNotNull(account.getId());
    }

    @Test(expected = ConstraintViolationException.class)
    public void createNotValidAccount() {
        AccountDataAccessImpl accountDataAccess = new AccountDataAccessImpl();
        accountDataAccess.createAccount(new BigDecimal(-1));
    }

    @Test
    public void successfulTransfer() throws TransferFailureException, AccountNotFoundException {
        AccountDataAccessImpl accountDataAccess = new AccountDataAccessImpl();
        Account account1 = accountDataAccess.createAccount(new BigDecimal(300));
        Account account2 = accountDataAccess.createAccount(new BigDecimal(0));

        accountDataAccess.transfer(account1.getId(), account2.getId(), new BigDecimal(200));

        Assert.assertEquals(new BigDecimal("100.0000"), accountDataAccess.getAccount(account1.getId()).getBalance());
        Assert.assertEquals(new BigDecimal("200.0000"), accountDataAccess.getAccount(account2.getId()).getBalance());
    }

    @Test(expected = TransferFailureException.class)
    public void transferNotFoundAccount() throws TransferFailureException {
        AccountDataAccessImpl accountDataAccess = new AccountDataAccessImpl();
        Account account = accountDataAccess.createAccount(new BigDecimal(0));

        accountDataAccess.transfer(100L, account.getId(), new BigDecimal(200));
    }

    @Test(expected = TransferFailureException.class)
    public void transferNotEnoughBalance() throws TransferFailureException {
        AccountDataAccessImpl accountDataAccess = new AccountDataAccessImpl();
        Account account1 = accountDataAccess.createAccount(new BigDecimal(300));
        Account account2 = accountDataAccess.createAccount(new BigDecimal(0));

        accountDataAccess.transfer(account1.getId(), account2.getId(), new BigDecimal(400));
    }
}
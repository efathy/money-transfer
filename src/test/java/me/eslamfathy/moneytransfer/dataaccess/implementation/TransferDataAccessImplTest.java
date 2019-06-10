package me.eslamfathy.moneytransfer.dataaccess.implementation;

import me.eslamfathy.moneytransfer.model.Account;
import me.eslamfathy.moneytransfer.model.Transfer;
import org.hibernate.TransientPropertyValueException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class TransferDataAccessImplTest {

    @Test
    public void createTransfer() {
        TransferDataAccessImpl transferDataAccess = new TransferDataAccessImpl();
        AccountDataAccessImpl accountDataAccess = new AccountDataAccessImpl();
        Account account1 = accountDataAccess.createAccount(new BigDecimal(300));
        Account account2 = accountDataAccess.createAccount(new BigDecimal(0));

        Transfer transfer = transferDataAccess.createTransfer(account1, account2, new BigDecimal("200"));
        Assert.assertNotNull(transfer);
        Assert.assertNotNull(transfer.getId());
    }

    @Test(expected = TransientPropertyValueException.class)
    public void createTransferNotValidAccount() {
        TransferDataAccessImpl transferDataAccess = new TransferDataAccessImpl();
        AccountDataAccessImpl accountDataAccess = new AccountDataAccessImpl();
        Account account1 = accountDataAccess.createAccount(new BigDecimal(300));
        Account account2 = new Account();

        transferDataAccess.createTransfer(account1, account2, new BigDecimal("200"));
    }
}
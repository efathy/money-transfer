package me.eslamfathy.moneytransfer.controller;

import org.junit.Assert;
import org.junit.Test;

public class MoneyTransferTest {

    @Test
    public void transferTest() {
        MoneyTransferService moneyTransferService = new MoneyTransferTest();

        int oldSourceBalance = 1000;
        int oldDestinationBalance = 0;
        Account sourceAccount = new Account(oldSourceBalance);
        Account destinationAccount = new Account(oldDestinationBalance);
        Long value = 5000L;
        TransferRequest transferRequest = new TransferRequest(sourceAccount, destinationAccount, value);

        TransferResponse transferResponse = moneyTransferService.makeTransfer(transferRequest);

        Assert.assertNotNull(transferResponse);
        Assert.assertEquals(oldSourceBalance - value , transferResponse.getSourceAccount().getBalance());
        Assert.assertEquals(oldDestinationBalance + value , transferResponse.getDestinationAccount().getBalance());
    }

}

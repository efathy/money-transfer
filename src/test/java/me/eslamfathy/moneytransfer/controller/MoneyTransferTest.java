package me.eslamfathy.moneytransfer.controller;

import me.eslamfathy.moneytransfer.Main;
import me.eslamfathy.moneytransfer.controller.dtos.TransferRequest;
import me.eslamfathy.moneytransfer.dataaccess.implementation.AccountDataAccessImpl;
import me.eslamfathy.moneytransfer.exceptions.MoneyTransferException;
import me.eslamfathy.moneytransfer.model.Account;
import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

public class MoneyTransferTest {

    private HttpServer server;
    private WebTarget target;

    @Before
    public void setUp() {
        server = Main.startServerInstance();
        Client c = ClientBuilder.newClient();
        target = c.target(Main.BASE_URI);
    }

    @After
    public void tearDown() {
        server.stop();
    }

    @Test
    public void testTransferMoney() throws MoneyTransferException {
        TransferRequest transferRequest = new TransferRequest();
        AccountDataAccessImpl accountDataAccess = new AccountDataAccessImpl();
        Account account1 = accountDataAccess.createAccount(new BigDecimal("300"));
        Account account2 = accountDataAccess.createAccount(new BigDecimal("0"));
        transferRequest.setSourceAccountId(account1.getId());
        transferRequest.setDestinationAccountId(account2.getId());
        transferRequest.setValue(new BigDecimal("200"));

        Response response = target.path("transfers").request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(transferRequest));

        Assert.assertEquals(201, response.getStatus());
        Assert.assertEquals(new BigDecimal("100.0000"), accountDataAccess.getAccount(account1.getId()).getBalance());
        Assert.assertEquals(new BigDecimal("200.0000"), accountDataAccess.getAccount(account2.getId()).getBalance());
    }

    @Test
    public void testFailedTransferMoney() throws MoneyTransferException {
        TransferRequest transferRequest = new TransferRequest();
        AccountDataAccessImpl accountDataAccess = new AccountDataAccessImpl();
        Account account1 = accountDataAccess.createAccount(new BigDecimal("100"));
        Account account2 = accountDataAccess.createAccount(new BigDecimal("0"));
        transferRequest.setSourceAccountId(account1.getId());
        transferRequest.setDestinationAccountId(account2.getId());
        transferRequest.setValue(new BigDecimal("200"));

        Response response = target.path("transfers").request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(transferRequest));

        Assert.assertEquals(500, response.getStatus());
    }

}

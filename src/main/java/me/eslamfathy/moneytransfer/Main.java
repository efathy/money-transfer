package me.eslamfathy.moneytransfer;

import me.eslamfathy.moneytransfer.dataaccess.AccountNotFoundException;
import me.eslamfathy.moneytransfer.dataaccess.implementation.AccountDataAccessImpl;
import me.eslamfathy.moneytransfer.dataaccess.implementation.TransferDataAccessImpl;
import me.eslamfathy.moneytransfer.model.Account;
import me.eslamfathy.moneytransfer.model.Transfer;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;

public class Main {
    private static final String BASE_URI = "http://localhost:8080/money-transfer/";

    private static HttpServer startServerInstance() {
        final ResourceConfig rc = new ResourceConfig().packages("me.eslamfathy.moneytransfer")
                                                      .register(JacksonFeature.class);
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        startServer();
    }

    private static void startServer() throws IOException{
        HttpServer server = startServerInstance();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        process();
        System.in.read();
        server.stop();
    }

    private static void process(){
        AccountDataAccessImpl accountDataAccess = new AccountDataAccessImpl();
        TransferDataAccessImpl transferDataAccess = new TransferDataAccessImpl();

        Account account2 = accountDataAccess.createAccount(new BigDecimal("1000"));
        System.out.println(account2);

        Account account = accountDataAccess.createAccount(new BigDecimal("500"));
        System.out.println(account);

        Transfer transfer = transferDataAccess.createTransfer(account, account2, new BigDecimal("200"));
        System.out.println(transfer);

        // TODO: 2019-06-09  negative balance
        try {
            System.out.println(accountDataAccess.getAccount(1L));
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
        }
    }
}


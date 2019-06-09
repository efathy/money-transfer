package me.eslamfathy.moneytransfer;

import me.eslamfathy.moneytransfer.dataaccess.AccountNotFoundException;
import me.eslamfathy.moneytransfer.dataaccess.implementation.AccountDataAccessImpl;
import me.eslamfathy.moneytransfer.model.Account;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;

public class Main {
    private static final String BASE_URI = "http://localhost:8080/money-transfer/";

    private static HttpServer getServerInstance() {
        final ResourceConfig rc = new ResourceConfig().packages("me.eslamfathy.moneytransfer")
                                                      .register(JacksonFeature.class);
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        startServer();
    }

    private static void startServer() throws IOException{
        final HttpServer server = getServerInstance();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        process();
        System.in.read();
        server.stop();
    }

    private static void process(){
        AccountDataAccessImpl accountDataAccess = new AccountDataAccessImpl();

        Account account = accountDataAccess.createAccount(new BigDecimal("-1"));
        System.out.println(account);
        // TODO: 2019-06-09  negative balance
        try {
            System.out.println(accountDataAccess.getAccount(1L));
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
        }
    }
}


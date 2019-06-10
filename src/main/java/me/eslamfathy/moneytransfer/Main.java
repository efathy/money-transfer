package me.eslamfathy.moneytransfer;

import me.eslamfathy.moneytransfer.dataaccess.implementation.AccountDataAccessImpl;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;

public class Main {
    public static final String BASE_URI = "http://localhost:8080/money-transfer/";

    public static HttpServer startServerInstance() {
        final ResourceConfig rc = new ResourceConfig().packages("me.eslamfathy.moneytransfer")
                                                      .register(JacksonFeature.class);
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        startServer();
    }

    private static void startServer() throws IOException {
        HttpServer server = startServerInstance();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        createMockData();
        System.in.read();
        server.stop();
    }

    private static void createMockData() {
        AccountDataAccessImpl accountDataAccess = new AccountDataAccessImpl();
        accountDataAccess.createAccount(new BigDecimal("500"));
        accountDataAccess.createAccount(new BigDecimal("1000"));
    }
}


package me.eslamfathy.moneytransfer.controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import me.eslamfathy.moneytransfer.configuration.MoneyTransferModule;
import me.eslamfathy.moneytransfer.controller.dtos.MoneyTransferResponse;
import me.eslamfathy.moneytransfer.controller.dtos.TransferRequest;
import me.eslamfathy.moneytransfer.exceptions.MoneyTransferException;
import me.eslamfathy.moneytransfer.model.Transfer;
import me.eslamfathy.moneytransfer.service.MoneyTransferService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("transfers")
public class MoneyTransferController {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response transfer(TransferRequest transferRequest) {
        Injector injector = Guice.createInjector(new MoneyTransferModule());
        MoneyTransferService moneyTransferService = injector.getInstance(MoneyTransferService.class);
        try {
            Transfer transfer = moneyTransferService
                    .makeTransfer(transferRequest.getSourceAccountId(), transferRequest.getDestinationAccountId(),
                            transferRequest.getValue());

            MoneyTransferResponse moneyTransferResponse = new MoneyTransferResponse(true, Response.Status.CREATED,
                    transfer.getId().toString());
            return Response.status(Response.Status.CREATED).entity(moneyTransferResponse)
                           .build();
        } catch (MoneyTransferException ex) {
            ex.printStackTrace();
            MoneyTransferResponse moneyTransferResponse = new MoneyTransferResponse(false,
                    Response.Status.INTERNAL_SERVER_ERROR, "Exception: " + ex.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(moneyTransferResponse)
                           .build();
        }
    }

}

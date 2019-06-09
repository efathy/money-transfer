package me.eslamfathy.moneytransfer.controller;

import me.eslamfathy.moneytransfer.controller.dtos.TransferRequest;
import me.eslamfathy.moneytransfer.controller.dtos.TransferResponse;
import me.eslamfathy.moneytransfer.dataaccess.AccountNotFoundException;
import me.eslamfathy.moneytransfer.service.MoneyTransferService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("transfers")
public class MoneyTransferController {

    @Inject
    private MoneyTransferService moneyTransferService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public TransferResponse transfer(TransferRequest transferRequest) {
        // TODO: 2019-06-08
        try {
            moneyTransferService.makeTransfer(transferRequest.getSourceAccountId(), transferRequest.getDestinationAccountId(), transferRequest.getValue());
        } catch (AccountNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}

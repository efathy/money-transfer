package me.eslamfathy.moneytransfer.exceptions;

public class TransferFailureException extends MoneyTransferException {
    public TransferFailureException(String message) {
        super(message);
    }

    public TransferFailureException(String message, Exception ex) {
        super(message, ex);
    }
}
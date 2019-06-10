package me.eslamfathy.moneytransfer.exceptions;

public class NoEnoughBalanceException extends MoneyTransferException {
    public NoEnoughBalanceException(String message) {
        super(message);
    }

    public NoEnoughBalanceException(String message, Exception ex) {
        super(message, ex);
    }
}

package me.eslamfathy.moneytransfer.exceptions;

public class NoEnoughBalanceException extends MoneyTransferException {
    public NoEnoughBalanceException(String message) {
        super(message);
    }
}

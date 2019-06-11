package me.eslamfathy.moneytransfer.exceptions;

public class AccountNotFoundException extends MoneyTransferException {
    public AccountNotFoundException(String message, Exception ex){
        super(message, ex);
    }
}

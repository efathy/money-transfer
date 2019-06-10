package me.eslamfathy.moneytransfer.exceptions;

public class MoneyTransferException extends Exception {
    public MoneyTransferException(String message){
        super(message);
    }

    public MoneyTransferException(String message, Exception ex){
        super(message, ex);
    }
}

package me.eslamfathy.moneytransfer.dataaccess;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String message){
        super(message);
    }

    public AccountNotFoundException(String message, Exception ex){
        super(message, ex);
    }
}

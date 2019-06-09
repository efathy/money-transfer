package me.eslamfathy.moneytransfer.configuration;

import com.google.inject.AbstractModule;
import me.eslamfathy.moneytransfer.dataaccess.AccountDataAccess;
import me.eslamfathy.moneytransfer.dataaccess.TransferDataAccess;
import me.eslamfathy.moneytransfer.dataaccess.implementation.AccountDataAccessImpl;
import me.eslamfathy.moneytransfer.dataaccess.implementation.TransferDataAccessImpl;
import me.eslamfathy.moneytransfer.service.MoneyTransferService;
import me.eslamfathy.moneytransfer.service.implementation.MoneyTransferServiceImpl;

public class MoneyTransferModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(MoneyTransferService.class).to(MoneyTransferServiceImpl.class);
        bind(AccountDataAccess.class).to(AccountDataAccessImpl.class);
        bind(TransferDataAccess.class).to(TransferDataAccessImpl.class);
    }
}

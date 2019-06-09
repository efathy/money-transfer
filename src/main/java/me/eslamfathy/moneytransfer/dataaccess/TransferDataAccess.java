package me.eslamfathy.moneytransfer.dataaccess;

import me.eslamfathy.moneytransfer.model.Account;
import me.eslamfathy.moneytransfer.model.Transfer;

import java.math.BigDecimal;

public interface TransferDataAccess {
    Transfer createTransfer(Account sourceAccount, Account destinationAccount, BigDecimal value);
}

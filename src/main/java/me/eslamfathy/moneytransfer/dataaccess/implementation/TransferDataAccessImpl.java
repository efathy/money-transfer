package me.eslamfathy.moneytransfer.dataaccess.implementation;

import me.eslamfathy.moneytransfer.configuration.HibernateUtil;
import me.eslamfathy.moneytransfer.dataaccess.TransferDataAccess;
import me.eslamfathy.moneytransfer.model.Account;
import me.eslamfathy.moneytransfer.model.Transfer;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigDecimal;

public class TransferDataAccessImpl implements TransferDataAccess {

    @Override
    public Transfer createTransfer(Account sourceAccount, Account destinationAccount, BigDecimal value) {
        Transfer transfer = new Transfer(sourceAccount, destinationAccount, value);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(transfer);
            transaction.commit();
            return transfer;
        }
    }
}

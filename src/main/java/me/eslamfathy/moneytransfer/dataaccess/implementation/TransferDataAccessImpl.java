package me.eslamfathy.moneytransfer.dataaccess.implementation;

import me.eslamfathy.moneytransfer.configuration.HibernateConfiguration;
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

        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(transfer);
            transaction.commit();
            return transfer;
            // TODO: 2019-06-09
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }
}

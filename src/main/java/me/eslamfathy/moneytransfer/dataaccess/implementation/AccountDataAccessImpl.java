package me.eslamfathy.moneytransfer.dataaccess.implementation;

import me.eslamfathy.moneytransfer.configuration.HibernateConfiguration;
import me.eslamfathy.moneytransfer.dataaccess.AccountDataAccess;
import me.eslamfathy.moneytransfer.dataaccess.AccountNotFoundException;
import me.eslamfathy.moneytransfer.model.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import java.math.BigDecimal;

public class AccountDataAccessImpl implements AccountDataAccess {
    @Override
    public Account getAccount(Long accountId) throws AccountNotFoundException {
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            return session.createQuery("from Account where id =" + accountId, Account.class).getSingleResult();
        } catch (NoResultException e) {
            throw new AccountNotFoundException("Account with id: " + accountId + " not found", e);
        }
    }

    @Override
    public Account createAccount(BigDecimal balance) {
        Account account = new Account(balance);

        Transaction transaction = null;
        try (Session session = HibernateConfiguration.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
            return account;
            // TODO: 2019-06-09
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        }
    }

    @Override
    public boolean transfer(Long sourceId, Long destinationId, BigDecimal value) {
        // TODO: 2019-06-09
        return false;
    }
}

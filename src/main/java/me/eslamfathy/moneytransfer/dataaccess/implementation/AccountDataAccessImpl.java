package me.eslamfathy.moneytransfer.dataaccess.implementation;

import me.eslamfathy.moneytransfer.configuration.HibernateUtils;
import me.eslamfathy.moneytransfer.dataaccess.AccountDataAccess;
import me.eslamfathy.moneytransfer.exceptions.AccountNotFoundException;
import me.eslamfathy.moneytransfer.exceptions.TransferFailureException;
import me.eslamfathy.moneytransfer.model.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.math.BigDecimal;

public class AccountDataAccessImpl implements AccountDataAccess {
    @Override
    public Account getAccount(Long accountId) throws AccountNotFoundException {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            return session.createQuery(String.format("from Account where id =%d", accountId), Account.class)
                          .getSingleResult();
        } catch (NoResultException e) {
            throw new AccountNotFoundException("Account with id: " + accountId + " not found", e);
        }
    }

    @Override
    public Account createAccount(BigDecimal balance) {
        Account account = new Account(balance);
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(account);
            transaction.commit();
            return account;
        }
    }

    @Override
    public void transfer(Long sourceAccountId, Long destinationAccountId, BigDecimal value) throws TransferFailureException {
        Transaction transaction = null;
        int subtractResult = 0;
        int additionResult = 0;
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            subtractResult = subtractFromSource(sourceAccountId, value, session);
            if (subtractResult > 0) {
                additionResult = addToDestination(destinationAccountId, value, session);
            }

            if (subtractResult > 0 && additionResult > 0) {
                transaction.commit();
            } else {
                transaction.rollback();
                throw new TransferFailureException("Fail to transfer");
            }
        } catch (Exception e) {
            throw new TransferFailureException("Fail to transfer", e);
        }
    }

    private int addToDestination(Long destinationId, BigDecimal value, Session session) {
        Query query;
        int result;
        query = session.createQuery("update Account set balance = (balance + :value)" +
                " where id = :destinationId");
        query.setParameter("value", value);
        query.setParameter("destinationId", destinationId);
        result = query.executeUpdate();
        return result;
    }

    private int subtractFromSource(Long sourceId, BigDecimal value, Session session) {
        Query query = session.createQuery("update Account set balance = (balance - :value)" +
                " where id = :sourceId and balance >= :value");
        query.setParameter("value", value);
        query.setParameter("sourceId", sourceId);
        return query.executeUpdate();
    }
}

package me.eslamfathy.moneytransfer.configuration;

import me.eslamfathy.moneytransfer.model.Account;
import me.eslamfathy.moneytransfer.model.Transfer;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    private HibernateUtil(){}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = getConfiguration();

                addEntities(configuration);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    private static void addEntities(Configuration configuration) {
        configuration.addAnnotatedClass(Account.class);
        configuration.addAnnotatedClass(Transfer.class);
    }

    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        Properties dbConnectionProperties = new Properties();
        try {
            dbConnectionProperties.load(HibernateUtil.class.getClassLoader().getSystemClassLoader()
                                                           .getResourceAsStream("hibernate.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        configuration.setProperties(dbConnectionProperties);
        return configuration;
    }
}

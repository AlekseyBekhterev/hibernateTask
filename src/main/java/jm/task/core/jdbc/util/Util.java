package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Util {
    private static final String MY_URL = "jdbc:mysql://localhost:3306/users";
    private static final String MY_USER = "root";
    private static final String MY_PASSWORD = "";
    private static final String DIALECT = "org.hibernate.dialect.MySQL57Dialect";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    private static Configuration getMySqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", DIALECT);
        configuration.setProperty("hibernate.connection.driver_class", JDBC_DRIVER);
        configuration.setProperty("hibernate.connection.url", MY_URL);
        configuration.setProperty("hibernate.connection.username", MY_USER);
        configuration.setProperty("hibernate.connection.password", MY_PASSWORD);
        configuration.setProperty("hibernate.show_sql", "true");
        return configuration;
    }

    private static SessionFactory createSessionFactory() {
        Configuration configuration;
        ServiceRegistry serviceRegistry;
        try {
            configuration = getMySqlConfiguration();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
            builder.applySettings(configuration.getProperties());
            serviceRegistry = builder.build();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        return configuration.buildSessionFactory(serviceRegistry);
    }

}


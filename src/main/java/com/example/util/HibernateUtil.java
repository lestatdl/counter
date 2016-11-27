package com.example.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.logging.Level;
import java.util.logging.Logger;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static String username = "root";
    private static String password = "root";
    private static Logger logger = Logger.getLogger(HibernateUtil.class.getSimpleName());

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            init();
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory(String name, String password) {
        HibernateUtil.username = name;
        HibernateUtil.password = password;
        init();
        return sessionFactory;
    }

    private static void init() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure()
                    .setProperty("hibernate.connection.username", username)
                    .setProperty("hibernate.connection.password", password)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            logger.log(Level.WARNING, ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }
}

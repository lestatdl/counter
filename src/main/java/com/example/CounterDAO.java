package com.example;

import org.hibernate.Session;
import com.example.util.HibernateUtil;

public class CounterDAO {

    private Counter createCounter(int value) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Counter counter = new Counter();
        counter.setValue(value);
        session.save(counter);
        session.getTransaction().commit();
        return counter;
    }

    public Counter getCounter(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Counter counter = session.get(Counter.class, id);
        session.getTransaction().commit();
        if (counter == null) {
            counter = createCounter(0);
        }
        return counter;
    }

    public Counter increase(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Counter counter = session.get(Counter.class, id);
        if (counter == null) {
            session.getTransaction().commit();
            return createCounter(1);
        }
        counter.setValue(counter.getValue() + 1);
        session.getTransaction().commit();
        return counter;
    }

    public Counter decrease(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Counter counter = session.get(Counter.class, id);
        if (counter == null) {
            session.getTransaction().commit();
            return createCounter(-1);
        }
        counter.setValue(counter.getValue() - 1);
        session.getTransaction().commit();
        return counter;
    }
}

package com.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.model.Person;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            // Select Person
            // Person person = session.get(Person.class, 1);
            // System.out.println(person.getName() + ' ' + person.getAge());

            // Insert Person
            Person person1 = new Person("Tom", 22);
            Person person2 = new Person("Bob", 45);
            Person person3 = new Person("Michael", 54);

            session.save(person1);
            session.save(person2);
            session.save(person3);

            session.getTransaction().commit();
        } finally {
            sessionFactory.close();
        }

    }
}

package com.hibernate;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.model.Item;
import com.hibernate.model.Passport;
import com.hibernate.model.Person;

/**
 * Hello world!
 *
 */
public class App {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Person.class)
                .addAnnotatedClass(Item.class).addAnnotatedClass(Passport.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            // Select Person
            // Person person = session.get(Person.class, 1);
            // System.out.println(person.getName() + ' ' + person.getAge());

            // Insert Person
            // Person person1 = new Person("Tom", 22);
            // Person person2 = new Person("Bob", 45);
            // Person person3 = new Person("Michael", 54);

            // session.save(person1);
            // session.save(person2);
            // session.save(person3);

            // Update Person
            // Person person = session.get(Person.class, 1);
            // person.setName("Changed");

            // Delete Person
            // Person person1 = session.get(Person.class, 2);
            // session.delete(person1);

            // Get ID of Person
            // Person person = new Person("Some name", 11);
            // session.save(person);

            // HQL query
            // List<Person> people = session.createQuery("FROM Person WHERE age >
            // 25").getResultList();
            // for (Person p : people) {
            // System.out.println(p);
            // }

            // session.createQuery("UPDATE Person set name = 'Test name' where age <
            // 15").executeUpdate();

            // session.createQuery("DELETE from Person WHERE age < 15").executeUpdate();

            /////////////////////
            ///// One-to-many/////
            /////////////////////

            // // Get items of Person
            // Person person = session.get(Person.class, 1);

            // for (Item i : person.getItemList())
            // System.out.println(i);

            // // Get person of Item
            // Item item = session.get(Item.class, 1);
            // System.out.println(item.getOwner());

            // // Insert items
            // Person person = session.get(Person.class, 3);
            // System.out.println(person);

            // Item item = new Item("Xbox", person);
            // person.getItemList().add(item);

            // session.save(item);
            // System.out.println(person.getItemList());

            // // Delete items from Person
            // Person person = session.get(Person.class, 1);

            // for (Item i : person.getItemList())
            // session.remove(i);

            /////////////////////////
            ///// Каскадирование/////
            /////////////////////////

            // Person person = new Person("Cascade name2", 34);
            // Item item = new Item("Cascade Item2");
            // Item item1 = new Item("Cascade Item3");
            // Item item2 = new Item("Cascade Item4");

            // person.addItem(Arrays.asList(item, item1, item2));

            // session.save(person);

            /////////////////////////
            /////// One-to-one //////
            /////////////////////////

            Person person = new Person("oneToOnePerson", 43);
            Passport passport = new Passport(3333333, person);
            person.setPassport(passport);

            session.save(person);

            session.getTransaction().commit();

            // Get ID of Person
            // System.out.println(person.getId());

        } finally {
            sessionFactory.close();
        }

    }
}

package hibernate_test.data_hibernate_criteria_6;


import hibernate.data_hibernate_criteria_6.entity.Address;
import hibernate.data_hibernate_criteria_6.entity.Company;
import hibernate.data_hibernate_criteria_6.entity.Passport;
import hibernate.data_hibernate_criteria_6.entity.Person;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Closeable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Iterator;

public class QueryTest {
    private SessionFactory sessionFactory;

    @Before
    public void setUp() throws Exception {
        Passport p = new Passport();
        p.setSeries("AS");
        p.setNo("123456");
        p.setIssueDate(LocalDate.now());
        p.setValidity(Period.ofYears(20));

        Address a = new Address();
        a.setCity("Kickapoo");
        a.setStreet("Main street");
        a.setBuilding("1");

        Person person = new Person();
        person.setFirstName("Test");
        person.setLastName("Testoff");
        person.setDob(LocalDate.now());
        person.setPrimaryAddress(a);
        person.setPassport(p);

        Company c = new Company();
        c.setName("Acme Ltd");

        p.setOwner(person);
        person.setWorkingPlaces(Collections.singletonList(c));

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate_resources/data-hibernate-criteria_6/hibernate.cfg.xml")
                .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();

        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw e;
        }

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(person);
        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testGreeter() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.createCriteria(Person.class)
                .setMaxResults(10)
                .list()
                .forEach(System.out::println);

        session.createCriteria(Person.class)
                .add(Restrictions.eq("lastName", "Testoff"))
                .list()
                .forEach(System.out::println);

        session.createCriteria(Person.class)
                .add(Restrictions.or(
                        Restrictions.like("lastName", "Te%"),
                        Restrictions.eq("firstName", "John")
                ))
                .list()
                .forEach(System.out::println);

        session.createCriteria(Passport.class)
                .createCriteria("owner")
                .add(Restrictions.eq("lastName", "Testoff"))
                .list()
                .forEach(System.out::println);

        session.createCriteria(Person.class)
                .createCriteria("workingPlaces")
                .add(Restrictions.eq("name", "Acme Ltd"))
                .list()
                .forEach(System.out::println);


        Passport p = new Passport();
        p.setSeries("as");
        session.createCriteria(Passport.class)
                .add(Example.create(p).ignoreCase())
                .list()
                .forEach(System.out::println);

        session.getTransaction().commit();
        session.close();

        DetachedCriteria dc = DetachedCriteria.forClass(Person.class)
                .createCriteria("passport")
                .add(Restrictions.eq("series", "AS"));
        session = sessionFactory.openSession();
        session.beginTransaction();
        dc.getExecutableCriteria(session)
                .list()
                .forEach(System.out::println);
        session.getTransaction().commit();
        session.close();
    }
}

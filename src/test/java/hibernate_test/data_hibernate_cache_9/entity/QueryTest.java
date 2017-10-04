package hibernate_test.data_hibernate_cache_9.entity;

import hibernate.data_hibernate_cache_9.entity.Address;
import hibernate.data_hibernate_cache_9.entity.Company;
import hibernate.data_hibernate_cache_9.entity.Passport;
import hibernate.data_hibernate_cache_9.entity.Person;
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
                .configure("hibernate_resources/data-hibernate-cache_9/hibernate.cfg.xml")
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

        // Database will be queried
        System.out.println(session.get(Person.class, 3L));

        // Cached object will be returned
        System.out.println(session.get(Person.class, 3L));

        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        // Database is not queried, only reference is returned
        Person p1 = session.load(Person.class, 3L);

        // Query triggered, object if filled with data
        System.out.println(p1);

        // Cached fully populated object will be returned
        Person p2=session.load(Person.class, 3L);

        System.out.println(p2);

        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        // Database will be queried
        System.out.println(session.get(Person.class, 3L));

        // Database will be queried
        System.out.println(session.createCriteria(Passport.class)
                .add(Restrictions.eq("series", "AS"))
                .setCacheable(true)
                .uniqueResult());

        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        // Database will not be queries, 2nd level cache will provide the data
        System.out.println(session.get(Person.class, 3L));

        // Database will not be queries, query cache will provide the data
        System.out.println(session.createCriteria(Passport.class)
                .add(Restrictions.eq("series", "AS"))
                .setCacheable(true)
                .uniqueResult());

        session.getTransaction().commit();
        session.close();
    }
}

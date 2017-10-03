package hibernate_test.data_hibernate_hql_4.entity;

import hibernate.data_hibernate_hql_4.entity.Address;
import hibernate.data_hibernate_hql_4.entity.Company;
import hibernate.data_hibernate_hql_4.entity.Passport;
import hibernate.data_hibernate_hql_4.entity.Person;
import org.hibernate.Hibernate;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Iterator;

public class QueryTest {
    private SessionFactory sessionFactory;

    @Before
    public void setUp() throws Exception{
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
                .configure("hibernate_resources/data-hibernate-hql_4/hibernate.cfg.xml")
                .build();
        try{
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        }catch (Exception e){
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
    public void testGreeter(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("from Person")
                .list()
                .forEach(System.out::println);
        System.out.println(
                session.createQuery("select passport from Person ")
                        .setTimeout(100)
                        .uniqueResult());
                        //.list());

        ScrollableResults passports = session.createQuery("from Passport as p where p.owner.lastName='Testoff'")
                .scroll();
        while (passports.next()) {
            System.out.println(passports.get()[0]);
        }

        Iterator passportIterator = session.createQuery("from Passport as p where p.owner.lastName like :name")
                .setString("name", "Te%")
                .iterate();
        while (passportIterator.hasNext()) {
            System.out.println(passportIterator.next());
        }
        Hibernate.close(passportIterator);

        session.createQuery("Select p from Person as p, IN(p.workingPlaces) as wp where wp.name = ?")
                .setParameter(0, "Acme Ltd")
                .list()
                .forEach(System.out::println);
        session.getNamedQuery("findCompaniesWithWorkerPassport")
                .setParameter("series", "AS")
                .list()
                .forEach(System.out::println);
        session.getTransaction().commit();
        session.close();
    }
}
package hibernate_test.data_hibernate_interceptors_12.entity;

import hibernate.data_hibernate_interceptors_12.entity.Address;
import hibernate.data_hibernate_interceptors_12.entity.Company;
import hibernate.data_hibernate_interceptors_12.entity.Passport;
import hibernate.data_hibernate_interceptors_12.entity.Person;
import hibernate.data_hibernate_interceptors_12.interceptors.PassportValidationInterceptor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;

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
                .configure("hibernate_resources/data-hibernate-interceptors_12/hibernate.cfg.xml")
                .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .getSessionFactoryBuilder()
                    .applyInterceptor(new PassportValidationInterceptor())
                    .build();

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

    @Test(expected = IllegalArgumentException.class)
    public void testPassportValid() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        //Try to save invalid passport
        sessionFactory
                .withOptions()
                .interceptor(new PassportValidationInterceptor())
                .openSession();
        Passport p = (Passport) session.createCriteria(Passport.class).add(Restrictions.eq("series", "AS")).uniqueResult();
        p.setSeries("INVALID");
        session.save(p);

        session.getTransaction().commit();
        session.close();
    }
}

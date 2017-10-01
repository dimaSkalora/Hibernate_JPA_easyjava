package hibernate.data_hibernate_mapping.annotations;


import hibernate.data_hibernate_mapping.annotations.entity.Address;
import hibernate.data_hibernate_mapping.annotations.entity.Company;
import hibernate.data_hibernate_mapping.annotations.entity.Passport;
import hibernate.data_hibernate_mapping.annotations.entity.Person;
import org.hibernate.Session;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;


public class MainTest {

    public static void main(String[] args) {
        MainTest mainTest = new MainTest();
        try {
            mainTest.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainTest.testGreeter();
    }
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

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(person);
        session.getTransaction().commit();
        session.close();
    }

    public void testGreeter() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.createQuery("from Person ")
                .list()
                .forEach(System.out::println);
        session.createQuery("from Passport ")
                .list()
                .forEach(System.out::println);
        session.createQuery("from Address ")
                .list()
                .forEach(System.out::println);
        session.createQuery("from Company ")
                .list()
                .forEach(System.out::println);
        session.getTransaction().commit();
        session.close();
    }
}

package hibernate.data_hibernate_mapping.xml;

import hibernate.data_hibernate_mapping.xml.entity.Address;
import hibernate.data_hibernate_mapping.xml.entity.Company;
import hibernate.data_hibernate_mapping.xml.entity.Passport;
import hibernate.data_hibernate_mapping.xml.entity.Person;
import org.hibernate.Session;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Date;


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
        p.setIssueDate(new Date());

        Address a = new Address();
        a.setCity("Kickapoo");
        a.setStreet("Main street");
        a.setBuilding("1");

        Person person = new Person();
        person.setFirstName("Test");
        person.setLastName("Testoff");
        person.setDob(new Date());
        person.setPrimaryAddress(a);
        person.setPassport(p);

        Company c = new Company();
        c.setName("Acme Ltd");

        p.setOwner(person);
        person.setWorkingPlaces(Collections.singleton(c));

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

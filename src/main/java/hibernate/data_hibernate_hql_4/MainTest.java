package hibernate.data_hibernate_hql_4;

import hibernate.data_hibernate_hql_4.entity.Address;
import hibernate.data_hibernate_hql_4.entity.Company;
import hibernate.data_hibernate_hql_4.entity.Passport;
import hibernate.data_hibernate_hql_4.entity.Person;
import org.hibernate.Hibernate;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.Iterator;

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

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.merge(person);
        session.getTransaction().commit();
        session.close();
    }

    public void testGreeter(){
        Session session = HibernateUtil.getSessionFactory().openSession();
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

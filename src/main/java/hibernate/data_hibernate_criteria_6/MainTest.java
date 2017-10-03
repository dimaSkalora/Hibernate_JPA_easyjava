package hibernate.data_hibernate_criteria_6;

import hibernate.data_hibernate_criteria_6.entity.Address;
import hibernate.data_hibernate_criteria_6.entity.Company;
import hibernate.data_hibernate_criteria_6.entity.Passport;
import hibernate.data_hibernate_criteria_6.entity.Person;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

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
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        dc.getExecutableCriteria(session)
                .list()
                .forEach(System.out::println);
        session.getTransaction().commit();
        session.close();
    }
}

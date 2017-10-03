package hibernate.data_hibernate_sql_5;

import hibernate.data_hibernate_sql_5.dto.CompanyNameDTO;
import hibernate.data_hibernate_sql_5.entity.Address;
import hibernate.data_hibernate_sql_5.entity.Company;
import hibernate.data_hibernate_sql_5.entity.Passport;
import hibernate.data_hibernate_sql_5.entity.Person;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;

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

    public void testGreeter() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        System.out.println("No persons: "+
                session.createSQLQuery("select count(id) as c from Person")
                        .addScalar("c", IntegerType.INSTANCE)
                        .uniqueResult());

        List<Object[]> passportIds = session.createSQLQuery("select id, passport_id from Person")
                .list();
        passportIds.forEach(p -> System.out.println("User id: "+p[0]+" Passport id: "+p[1]));

        session.createSQLQuery("select p.* from Passport as p, Person as pe where p.id=pe.passport_id and pe.lastName='Testoff'")
                .addEntity(Passport.class)
                .list()
                .forEach(System.out::println);

        session.createSQLQuery("select * from Person as p join Passport as pa on p.passport_id=pa.id and p.lastName =:name")
                .addEntity("p",Person.class)
                .addJoin("pa", "p.passport")
                .setResultTransformer( Criteria.ROOT_ENTITY )
                .setString("name", "Testoff")
                .list()
                .forEach(System.out::println);

        session.createSQLQuery("select name as \"name\"from Company")
                .setResultTransformer(Transformers.aliasToBean(CompanyNameDTO.class))
                .list()
                .forEach(System.out::println);

        session.getNamedQuery("findCompanyWithName")
                .setParameter("name", "Ac%")
                .list()
                .forEach(System.out::println);

        session.getNamedQuery("findCompanyNameOnly")
                .list()
                .forEach(System.out::println);
        session.getTransaction().commit();
        session.close();
    }
}

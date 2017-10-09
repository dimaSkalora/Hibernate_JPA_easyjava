package jpa_test.data_jpa_jpql_07.entity;

import jpa.data_jpa_jpql_07.entity.Address_Jpa;
import jpa.data_jpa_jpql_07.entity.Company_Jpa;
import jpa.data_jpa_jpql_07.entity.Passport_Jpa;
import jpa.data_jpa_jpql_07.entity.Person_Jpa;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;

public class QueryTest {
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void setUp() throws Exception {
        Passport_Jpa p = new Passport_Jpa();
        p.setSeries("AS");
        p.setNo("123456");
        p.setIssueDate(LocalDate.now());
        p.setValidity(Period.ofYears(20));

        Address_Jpa a = new Address_Jpa();
        a.setCity("Kickapoo");
        a.setStreet("Main street");
        a.setBuilding("1");

        Person_Jpa person = new Person_Jpa();
        person.setFirstName("Test");
        person.setLastName("Testoff");
        person.setDob(LocalDate.now());
        person.setPrimaryAddress_jpa(a);
        person.setPassport_jpa(p);

        Company_Jpa c = new Company_Jpa();
        c.setName("Acme Ltd");

        p.setOwner(person);
        person.setWorkingPlaces_jpa(Collections.singletonList(c));

        entityManagerFactory = Persistence.createEntityManagerFactory("data-jpa-jpql_07");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(person);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testGreeter() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("from Person_Jpa ")
                .getResultList()
                .forEach(System.out::println);
        em.createQuery("select passport_jpa from Person_Jpa ", Passport_Jpa.class)
                .getResultList()
                .forEach(System.out::println);
        /*em.createQuery("select passport from Person ", Person.class)
                .getResultList()
                .forEach(System.out::println);*/
        em.createQuery("from Passport_Jpa as p where p.owner.lastName='Testoff'")
                .getResultList()
                .forEach(System.out::println);
        em.createQuery("from Passport_Jpa as p where p.owner.lastName like :name")
                .setParameter("name", "Te%")
                .getResultList()
                .forEach(System.out::println);
        em.createQuery("Select p from Person_Jpa as p, IN(p.workingPlaces_jpa) as wp where wp.name = ?1")
                .setParameter(1, "Acme Ltd")
                .getResultList()
                .forEach(System.out::println);
        em.createNamedQuery("findCompaniesWithWorkerPassport_jpa")
                .setParameter("series", "AS")
                .getResultList()
                .forEach(System.out::println);
        em.getTransaction().commit();
        em.close();
    }
}

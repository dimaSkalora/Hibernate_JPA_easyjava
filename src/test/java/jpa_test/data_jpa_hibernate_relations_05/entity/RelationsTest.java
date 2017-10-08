package jpa_test.data_jpa_hibernate_relations_05.entity;

import jpa.data_jpa_hibernate_relations_05.entity.Address_Jpa;
import jpa.data_jpa_hibernate_relations_05.entity.Company_Jpa;
import jpa.data_jpa_hibernate_relations_05.entity.Passport_Jpa;
import jpa.data_jpa_hibernate_relations_05.entity.Person_Jpa;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;

public class RelationsTest {
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

        entityManagerFactory = Persistence.createEntityManagerFactory("data-jpa-hibernate-relations_05");
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
        em.createQuery("from Person_Jpa ", Person_Jpa.class)
                .getResultList()
                .forEach(System.out::println);
        em.createQuery("from Passport_Jpa ", Passport_Jpa.class)
                .getResultList()
                .forEach(System.out::println);
        em.createQuery("from Address_Jpa ", Address_Jpa.class)
                .getResultList()
                .forEach(System.out::println);
        em.createQuery("from Company_Jpa ", Company_Jpa.class)
                .getResultList()
                .forEach(System.out::println);
        em.getTransaction().commit();
        em.close();
    }
}

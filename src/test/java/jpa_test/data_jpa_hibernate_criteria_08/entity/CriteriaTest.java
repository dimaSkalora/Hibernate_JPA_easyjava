package jpa_test.data_jpa_hibernate_criteria_08.entity;

import jpa.data_jpa_hibernate_criteria_08.entity.Address_Jpa;
import jpa.data_jpa_hibernate_criteria_08.entity.Company_Jpa;
import jpa.data_jpa_hibernate_criteria_08.entity.Passport_Jpa;
import jpa.data_jpa_hibernate_criteria_08.entity.Person_Jpa;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.Collections;

public class CriteriaTest {
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

        entityManagerFactory = Persistence.createEntityManagerFactory("data-jpa-hibernate-criteria_08");
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
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Person_Jpa> personCriteria = cb.createQuery(jpa.data_jpa_hibernate_criteria_08.entity.Person_Jpa.class);
        Root<Person_Jpa> personRoot = personCriteria.from(jpa.data_jpa_hibernate_criteria_08.entity.Person_Jpa.class);
        personCriteria.select(personRoot);
        em.createQuery(personCriteria)
                .getResultList()
                .forEach(System.out::println);

        CriteriaQuery<Passport_Jpa> passportCriteria = cb.createQuery(jpa.data_jpa_hibernate_criteria_08.entity.Passport_Jpa.class);
        Root<Person_Jpa> personPassportRoot = passportCriteria.from(jpa.data_jpa_hibernate_criteria_08.entity.Person_Jpa.class);
        passportCriteria.select(personPassportRoot.get("passport_jpa"));
        em.createQuery(passportCriteria)
                .getResultList()
                .forEach(System.out::println);

        CriteriaQuery<Passport_Jpa> passportOwnerCriteria = cb.createQuery(jpa.data_jpa_hibernate_criteria_08.entity.Passport_Jpa.class);
        Root<Passport_Jpa> ownerPassportRoot = passportOwnerCriteria.from(jpa.data_jpa_hibernate_criteria_08.entity.Passport_Jpa.class);
        passportOwnerCriteria.select(ownerPassportRoot);
        passportOwnerCriteria.where(cb.equal(ownerPassportRoot.get("owner").get("lastName"), "Testoff"));
        em.createQuery(passportOwnerCriteria)
                .getResultList()
                .forEach(System.out::println);

        CriteriaQuery<Passport_Jpa> passportLikeCriteria = cb.createQuery(jpa.data_jpa_hibernate_criteria_08.entity.Passport_Jpa.class);
        Root<Passport_Jpa> likePassportRoot = passportLikeCriteria.from(jpa.data_jpa_hibernate_criteria_08.entity.Passport_Jpa.class);
        passportLikeCriteria.select(likePassportRoot);
        passportLikeCriteria.where(cb.like(likePassportRoot.get("owner").get("lastName"), "Te%"));
        em.createQuery(passportLikeCriteria)
                .getResultList()
                .forEach(System.out::println);

        CriteriaQuery<Person_Jpa> personWorkCriteria = cb.createQuery(jpa.data_jpa_hibernate_criteria_08.entity.Person_Jpa.class);
        Root<Person_Jpa> personWorkRoot = personWorkCriteria.from(jpa.data_jpa_hibernate_criteria_08.entity.Person_Jpa.class);
        Join<Person_Jpa, Company_Jpa> company = personWorkRoot.join("workingPlaces_jpa");
        personWorkCriteria.select(personWorkRoot);
        personWorkCriteria.where(cb.equal(company.get("name"), "Acme Ltd"));
        em.createQuery(personWorkCriteria)
                .getResultList()
                .forEach(System.out::println);

        CriteriaQuery<Company_Jpa> companyPassportCriteria = cb.createQuery(jpa.data_jpa_hibernate_criteria_08.entity.Company_Jpa.class);
        Root<Company_Jpa> companyPassportRoot = companyPassportCriteria.from(jpa.data_jpa_hibernate_criteria_08.entity.Company_Jpa.class);
        Join<Company_Jpa, Person_Jpa> person = companyPassportRoot.join("workers");
        companyPassportCriteria.select(companyPassportRoot);
        companyPassportCriteria.where(cb.equal(person.get("passport_jpa").get("series"), "AS"));
        em.createQuery(companyPassportCriteria)
                .setFirstResult(0)
                .setMaxResults(10)
                .getResultList()
                .forEach(System.out::println);

        em.getTransaction().commit();
        em.close();
    }
}

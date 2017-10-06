package jpa_test.data_jpa_hibernate_hellojpahibernate_01.entity;

import jpa.data_jpa_hibernate_hellojpahibernate_01.entity.GreeterJpa;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GreeterTest {
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void setUp() throws Exception {
        GreeterJpa greetJpa = new GreeterJpa();
        greetJpa.setGreeting("Hello");
        greetJpa.setTarget("JPA");

        GreeterJpa greetHibernate = new GreeterJpa();
        greetHibernate.setGreeting("Hello");
        greetHibernate.setTarget("Hibernate");

        entityManagerFactory = Persistence.createEntityManagerFactory("data-jpa-hibernate-hellojpahibernate_01");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(greetJpa);
        em.persist(greetHibernate);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testGreeter() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("from GreeterJpa", GreeterJpa.class)
                .getResultList()
                .forEach(g -> System.out.println(String.format("%s, %s!", g.getGreeting(), g.getTarget())));
        em.getTransaction().commit();
        em.close();
    }
}

package jpa_test.data_jpa_hibernate_inheritance_04.entity;

import jpa.data_jpa_hibernate_inheritance_04.entity.mapped_superclass.OperationJpa;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class OperationTest {
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void setUp() throws Exception {
        OperationJpa op = new OperationJpa();

        op.setAccountId(100500);
        op.setAmount(BigDecimal.TEN);

        entityManagerFactory = Persistence.createEntityManagerFactory("data-jpa-hibernate-inheritance_04");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(op);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testGreeter() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("from jpa.data_jpa_hibernate_inheritance_04.entity.mapped_superclass.OperationJpa ", OperationJpa.class)
                .getResultList()
                .forEach(System.out::println);
        em.getTransaction().commit();
        em.close();
    }
}

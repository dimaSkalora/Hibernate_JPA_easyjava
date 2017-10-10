package jpa_test.data_jpa_hibernate_callbacks_10.entity;

import jpa.data_jpa_hibernate_callbacks_10.entity.OperationJpa;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class OperationTest {
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void testManager() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("data-jpa-hibernate-callbacks_10");
    }

    @Test
    public void testGreeter() {
        //New op
        OperationJpa op = new OperationJpa();

        op.setId(1L);
        op.setAccountId(100500);
        op.setAmount(BigDecimal.TEN);
        op.setTimestamp(ZonedDateTime.now());
        op.setDescription("Test operation");
        op.setOpCode(9000);

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(op); // op is MANAGED now
        em.flush();

        op.setDescription("New operation name.");
        em.getTransaction().commit();
        em.close(); // op is DETACHED now

        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        op = em.find(jpa.data_jpa_hibernate_callbacks_10.entity.OperationJpa.class, 1L);
        em.remove(op); // op is REMOVED now
        em.getTransaction().commit();
        em.close(); // op is DETACHED now
    }
}

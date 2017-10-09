package jpa_test.data_jpa_hibernate_entitymanager_06.entity;

import jpa.data_jpa_hibernate_entitymanager_06.entity.OperationJpa;
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
        entityManagerFactory = Persistence.createEntityManagerFactory("data-jpa-hibernate-entitymanager_06");

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

        em.remove(op); // op is REMOVED now
        em.persist(op); // op is MANAGED now
        op.setDescription("New operation name.");
        em.getTransaction().commit();
        em.close(); // op is DETACHED now

        //Merge back
        em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        op = em.merge(op); // op is MANAGED now
        em.refresh(op); // OP is still MANAGED and reloaded from the DB
        em.getTransaction().commit();
        em.clear(); // op is DETACHED now
    }

    @Test
    public void testGreeter() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        System.out.println(em.find(OperationJpa.class, 1L).toString());
        em.getTransaction().commit();
        em.close();
    }
}

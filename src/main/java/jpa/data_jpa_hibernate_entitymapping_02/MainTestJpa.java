package jpa.data_jpa_hibernate_entitymapping_02;

import jpa.data_jpa_hibernate_entitymapping_02.entity.OperationJpa;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class MainTestJpa {
    public static void main(String[] args) {
        MainTestJpa mainTestJpa = new MainTestJpa();
        try {
            mainTestJpa.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainTestJpa.testGreeter();
    }

    public void setUp() throws Exception {
        OperationJpa op = new OperationJpa();

        op.setId(1L);
        op.setAccountId(100500);
        op.setAmount(BigDecimal.TEN);
        op.setTimestamp(ZonedDateTime.now());
        op.setDescription("Test operation");
        op.setOpCode(9000);

        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(op);
        em.getTransaction().commit();
        em.close();
    }

    public void testGreeter() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.createQuery("from OperationJpa ", OperationJpa.class)
                .getResultList()
                .forEach(System.out::println);
        em.getTransaction().commit();
        em.close();
    }
}

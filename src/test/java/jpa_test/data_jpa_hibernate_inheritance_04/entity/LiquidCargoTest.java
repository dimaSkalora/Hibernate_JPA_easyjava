package jpa_test.data_jpa_hibernate_inheritance_04.entity;

import jpa.data_jpa_hibernate_inheritance_04.entity.separate_tables_for_each_class.LiquidCargo;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class LiquidCargoTest {
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void setUp() throws Exception {
        LiquidCargo l = new LiquidCargo();

        l.setId(1L);
        l.setWeight(362L);
        l.setVolume(75L);
        l.setLiquidType("Water");

        entityManagerFactory = Persistence.createEntityManagerFactory("data-jpa-hibernate-inheritance_04");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(l);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testGreeter() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("from LiquidCargo", LiquidCargo.class)
                .getResultList()
                .forEach(System.out::println);
        em.getTransaction().commit();
        em.close();
    }
}

package jpa_test.data_jpa_hibernate_inheritance_04.entity;

import jpa.data_jpa_hibernate_inheritance_04.entity.separate_tables_for_each_class.PackedCargo;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PackedCargoTest {
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void setUp() throws Exception {
        PackedCargo p = new PackedCargo();

        p.setId(1L);
        p.setWeight(362L);
        p.setWidth(9L);
        p.setHeight(12L);

        entityManagerFactory = Persistence.createEntityManagerFactory("data-jpa-hibernate-inheritance_04");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testGreeter() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("from PackedCargo", PackedCargo.class)
                .getResultList()
                .forEach(System.out::println);
        em.getTransaction().commit();
        em.close();
    }
}

package jpa_test.data_jpa_hibernate_inheritance_04.entity;

import jpa.data_jpa_hibernate_inheritance_04.entity.separate_tables_for_each_class.Cargo;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CargoTest {
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void setUp() throws Exception {
        Cargo c = new Cargo();

        c.setId(1L);
        c.setWeight(362L);

        entityManagerFactory = Persistence.createEntityManagerFactory("data-jpa-hibernate-inheritance_04");
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.merge(c);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void testGreeter() {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("from Cargo", Cargo.class)
                .getResultList()
                .forEach(System.out::println);
        em.getTransaction().commit();
        em.close();
    }
}

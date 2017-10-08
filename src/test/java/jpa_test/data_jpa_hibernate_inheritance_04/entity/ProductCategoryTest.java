package jpa_test.data_jpa_hibernate_inheritance_04.entity;

import jpa.data_jpa_hibernate_inheritance_04.entity.by_the_table_and_join_each_class.ProductCategory;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProductCategoryTest {
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void setUp() throws Exception {
        ProductCategory p = new ProductCategory();

        p.setId(1L);
        p.setDescription("Some nice products are here");
        p.setCode("NICE");

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
        em.createQuery("from ProductCategory", ProductCategory.class)
                .getResultList()
                .forEach(System.out::println);
        em.getTransaction().commit();
        em.close();
    }
}

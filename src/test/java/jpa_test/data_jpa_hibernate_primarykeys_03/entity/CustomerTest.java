package jpa_test.data_jpa_hibernate_primarykeys_03.entity;

import jpa.data_jpa_hibernate_primarykeys_03.entity.Customer;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class CustomerTest {
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void setUp() throws Exception{
        Customer c = new Customer();
        c.setPassportSeries("AA");
        c.setPassportSeries("012345");
        c.setName("Test Customer von Testoff");

        entityManagerFactory = Persistence.createEntityManagerFactory("data-jpa-hibernate-primarykeys_03");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(c);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Test
    public void testGreeter(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("FROM Customer ", Customer.class)
                .getResultList()
                .forEach(System.out::println);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

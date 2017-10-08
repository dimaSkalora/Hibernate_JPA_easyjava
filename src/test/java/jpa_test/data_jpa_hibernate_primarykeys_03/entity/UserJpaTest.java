package jpa_test.data_jpa_hibernate_primarykeys_03.entity;

import jpa.data_jpa_hibernate_primarykeys_03.entity.User;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public class UserJpaTest {
    private EntityManagerFactory entityManagerFactory;

    @Before
    public void setUp() throws Exception {
        entityManagerFactory = Persistence.createEntityManagerFactory("data-jpa-hibernate-primarykeys_03");
    }

    @Test(expected = PersistenceException.class)
    public void testNeedId() {
        User u = new User();
        u.setUsername("user_1");
        u.setEmail("test@example.com");
        u.setPassword("secret");

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        em.close();
    }
}

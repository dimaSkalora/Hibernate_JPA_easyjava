package jpa.data_jpa_hibernate_primarykeys_03;

import jpa.data_jpa_hibernate_primarykeys_03.entity.User;

import javax.persistence.EntityManager;

public class MainTestJpa_User {
    public static void main(String[] args) {
        MainTestJpa_User mainTestJpa_user = new MainTestJpa_User();
        try {
            mainTestJpa_user.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainTestJpa_user.testNeedId();
    }

    public void setUp() throws Exception {
        User u = new User();
        u.setUsername("user_2");
        u.setEmail("test1@example.com");
        u.setPassword("secret");

        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(u);
        em.getTransaction().commit();
        em.close();
    }

    public void testNeedId() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.createQuery("from User", User.class)
                .getResultList()
                .forEach(System.out::println);
        em.getTransaction().commit();
        em.close();
    }
}

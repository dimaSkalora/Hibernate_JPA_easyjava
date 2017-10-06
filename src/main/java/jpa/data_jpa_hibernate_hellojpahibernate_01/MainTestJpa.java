package jpa.data_jpa_hibernate_hellojpahibernate_01;

import jpa.data_jpa_hibernate_hellojpahibernate_01.entity.GreeterJpa;

import javax.persistence.EntityManager;

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
        GreeterJpa greetJpa = new GreeterJpa();
        greetJpa.setGreeting("Hello");
        greetJpa.setTarget("JPA");

        GreeterJpa greetHibernate = new GreeterJpa();
        greetHibernate.setGreeting("Hello");
        greetHibernate.setTarget("Hibernate");

        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.persist(greetJpa);
        em.persist(greetHibernate);
        em.getTransaction().commit();
        em.close();
    }

    public void testGreeter() {
        EntityManager em = JpaUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        em.createQuery("from GreeterJpa", GreeterJpa.class)
                .getResultList()
                .forEach(g -> System.out.println(String.format("%s, %s!", g.getGreeting(), g.getTarget())));
        em.getTransaction().commit();
        em.close();
    }
}

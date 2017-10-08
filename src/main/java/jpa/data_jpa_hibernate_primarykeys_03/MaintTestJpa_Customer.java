package jpa.data_jpa_hibernate_primarykeys_03;

import jpa.data_jpa_hibernate_primarykeys_03.entity.Customer;


import javax.persistence.EntityManager;


public class MaintTestJpa_Customer {
    public static void main(String[] args) {
        MaintTestJpa_Customer maintTestJpa_customer = new MaintTestJpa_Customer();
        try {
            maintTestJpa_customer.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        maintTestJpa_customer.testGreeter();
    }

    public void setUp() throws Exception{
        Customer c = new Customer();
        c.setPassportSeries("AA");
        c.setPassportSeries("012345");
        c.setName("Test Customer von Testoff");


        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(c);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void testGreeter(){
        EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("FROM Customer ", Customer.class)
                .getResultList()
                .forEach(System.out::println);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

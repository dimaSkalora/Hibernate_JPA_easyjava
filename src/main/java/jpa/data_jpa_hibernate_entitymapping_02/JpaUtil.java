package jpa.data_jpa_hibernate_entitymapping_02;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    private static EntityManagerFactory entityManagerFactory=null;

    static{
        entityManagerFactory = Persistence.createEntityManagerFactory("data-jpa-hibernate-entitymapping_02");
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}

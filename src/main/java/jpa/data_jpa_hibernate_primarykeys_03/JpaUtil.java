package jpa.data_jpa_hibernate_primarykeys_03;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
    private static EntityManagerFactory entityManagerFactory=null;

    static{
        entityManagerFactory = Persistence.createEntityManagerFactory("data-jpa-hibernate-primarykeys_03");
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}

package hibernate.data_hibernate_hql_4;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    static {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate_resources/data-hibernate-hql_4/hibernate.cfg.xml")
                .build();
        try{
            MetadataSources metadataSources = new MetadataSources(registry);
            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        }catch (Exception e){
            StandardServiceRegistryBuilder.destroy(registry);
            throw e;
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

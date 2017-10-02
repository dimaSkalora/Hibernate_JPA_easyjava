package hibernate.data_hibernate_session;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory = null;

    static {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate_resources/data-hibernate-session/hibernate.cfg.xml")
                .build();
        try{
            MetadataSources metadataSources = new MetadataSources(registry);
            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();

            //metadataSources.addAnnotatedClass(hibernate.data_hibernate_session.Address.class);
            //metadataSources.addAnnotatedClassName("hibernate.data_hibernate_session.Address.class");
            //metadataSources.addResource("classpath:/Address.hbm.xml");
        }catch (Exception e){
            StandardServiceRegistryBuilder.destroy(registry);
            throw e;
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}

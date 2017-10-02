package hibernate_test.data_hibernate_mapping.dynamic;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RelationsTest {
    private SessionFactory sessionFactory;

    @Before
    public void setUp() throws Exception{
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate_resources/data-hibernate-mapping/dynamic/hibernate.cfg.xml")
                .build();
        try{
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();
        }catch (Exception e){
            StandardServiceRegistryBuilder.destroy(registry);
            throw e;
        }

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Map<String,Object> passport = new HashMap<>();
        passport.put("id", 10L);
        passport.put("series", "AS");
        passport.put("no", "123456");
        passport.put("issueDate", new Date());

        session.save("Passport", passport);

        session.getTransaction().commit();
        session.close();
    }

    @Test
    public void testGreeter() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("from Passport ")
                .list()
                .forEach(System.out::println);
        session.getTransaction().commit();
        session.close();
    }
}

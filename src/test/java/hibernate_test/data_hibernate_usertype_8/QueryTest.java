package hibernate_test.data_hibernate_usertype_8;


import hibernate.data_hibernate_usertype_8.entity.Network;
import hibernate.data_hibernate_usertype_8.type.NetworkObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;

public class QueryTest {
    private SessionFactory sessionFactory;

    @Before
    public void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate_resources/data-hibernate-usertype_8/hibernate.cfg.xml")
                .build();
        try {
            sessionFactory = new MetadataSources(registry)
                    .buildMetadata()
                    .buildSessionFactory();

        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            throw e;
        }
    }

    @Test
    public void testGreeter() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        NetworkObject example = new NetworkObject("93.184.216.34", (short)24);
        NetworkObject example6 = new NetworkObject("2606:2800:220:1:248:1893:25c8:1946", (short)64);

        Network n4 = new Network();
        n4.setNetwork(example);

        Network n6 = new Network();
        n6.setNetwork(example6);

        session.save(n4);
        session.save(n6);

        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        session.createCriteria(Network.class)
                .list()
                .stream()
                .forEach(System.out::println);

        session.getTransaction().commit();
        session.close();
    }
}
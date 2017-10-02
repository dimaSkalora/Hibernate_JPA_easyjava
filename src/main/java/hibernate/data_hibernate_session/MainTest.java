package hibernate.data_hibernate_session;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class MainTest {
    private SessionFactory sessionFactory;

    public static void main(String[] args) {
        MainTest mainTest = new MainTest();
        mainTest.setUp();
        mainTest.testGreeter();
    }

    public void setUp(){
        sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        //Transient op
        Operation op = new Operation();
        op.setId(1L);
        op.setAccountId(100500);
        op.setAmount(BigDecimal.TEN);
        op.setTimestamp(ZonedDateTime.now());
        op.setDescription("Test operation");
        op.setOpCode(9000);

        Long opRowId = (Long)session.save(op);
        System.out.println("Save entity and assigned id: "+opRowId);

        op.setAccountId(9000);

        session.getTransaction().commit();
        session.close();
    }

    public void testGreeter(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.createQuery("from Operation ")
                .list()
                .forEach(System.out::println);
        session.getTransaction().commit();

        session.beginTransaction();
        Operation op = session.get(Operation.class, 1L);
        op.setAccountId(90000);
        session.update(op);
        session.getTransaction().commit();

        session.beginTransaction();
        session.createQuery("from Operation ")
                .list()
                .forEach(System.out::println);
        session.getTransaction().commit();

        session.beginTransaction();
        session.load(Operation.class, 2L);
        System.out.println(session.load(Operation.class, 1L));
        System.out.println(session.get(Operation.class, 1L));
        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();
        Operation opD = session.load(Operation.class, 1L);
        session.getTransaction().commit();
        session.close();

        //Throws exception
        //System.out.println(opD.getDescription());

        session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(opD);
        session.lock(opD, LockMode.NONE);
        System.out.println(opD.getDescription());
        session.getTransaction().commit();
        session.close();
    }
}

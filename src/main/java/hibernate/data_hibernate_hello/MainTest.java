package hibernate.data_hibernate_hello;

import org.hibernate.Session;

import java.util.List;

public class MainTest {

    public static void main(String[] args) {
        MainTest mainTest = new MainTest();
        try {
           // mainTest.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        mainTest.testGreeter();
    }
    public void setUp() throws Exception {
        Greeter greetJpa = new Greeter();
        greetJpa.setGreeting("Bye2");
        greetJpa.setTarget("JPA2");

        Greeter greetHibernate = new Greeter();
        greetHibernate.setGreeting("Hello2");
        greetHibernate.setTarget("Hibernate2");

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(greetHibernate);
        session.save(greetJpa);
        session.getTransaction().commit();
        session.close();
    }

    public void testGreeter() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Greeter> greetings = session.createQuery("from Greeter")
                .list();
        greetings.forEach(g -> System.out.println(String.format("%s, %s!",
                g.getGreeting(),
                g.getTarget())));
        session.getTransaction().commit();
        session.close();
    }
}

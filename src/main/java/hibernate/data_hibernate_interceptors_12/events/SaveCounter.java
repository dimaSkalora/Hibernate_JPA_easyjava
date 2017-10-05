package hibernate.data_hibernate_interceptors_12.events;

import org.hibernate.HibernateException;
import org.hibernate.event.spi.SaveOrUpdateEvent;
import org.hibernate.event.spi.SaveOrUpdateEventListener;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Counts save events.
 */
public class SaveCounter implements SaveOrUpdateEventListener {
    /**
     * Our parent implements serializable.
     */
    private static final long serialVersionUID = 42L;

    AtomicInteger counter = new AtomicInteger();
    @Override
    public void onSaveOrUpdate(SaveOrUpdateEvent e) throws HibernateException {
        System.out.println("Saving: " + e.getResultId());
        System.out.println("Totally seen " + counter.incrementAndGet() + " save events");
    }
}

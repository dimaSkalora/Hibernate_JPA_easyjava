package hibernate.data_hibernate_interceptors_12.interceptors;

import hibernate.data_hibernate_interceptors_12.entity.Passport;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;

/**
 * Check that passport series/no is valid.
 */
public class PassportValidationInterceptor extends EmptyInterceptor {
    /**
     * Our parent implements serializable.
     */
    private static final long serialVersionUID = 42L;

    @Override
    public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
        if (entity instanceof Passport) {
            Passport p = (Passport) entity;
            if (p.getSeries().length() !=2 || p.getNo().length() != 6) {
                throw new IllegalArgumentException("Incorrect passport data");
            }
        }
        return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
    }
}

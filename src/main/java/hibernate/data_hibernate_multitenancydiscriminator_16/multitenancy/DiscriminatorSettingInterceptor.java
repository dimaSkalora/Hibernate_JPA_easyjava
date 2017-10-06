package hibernate.data_hibernate_multitenancydiscriminator_16.multitenancy;

import hibernate.data_hibernate_multitenancydiscriminator_16.entity.AbstractDiscriminatorObject;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Iterator;

/**
 * Intercepts update operations and put's tenant id into the processed entity.
 */
public class DiscriminatorSettingInterceptor extends EmptyInterceptor {
    private TenantIdResolver resolver = new TenantIdResolver();

    @Override
    public void preFlush(Iterator entities) {
        entities.forEachRemaining(o -> {
            if (o instanceof AbstractDiscriminatorObject) {
                AbstractDiscriminatorObject t = (AbstractDiscriminatorObject) o;
                if (t.getTenantId() == null) {
                    String tenantId = resolver.resolveCurrentTenantIdentifier();
                    if (tenantId == null) {
                        throw new IllegalStateException("No tenant id specified");
                    }
                    t.setTenantId(tenantId);
                }
            }
        });
        super.preFlush(entities);
    }
}

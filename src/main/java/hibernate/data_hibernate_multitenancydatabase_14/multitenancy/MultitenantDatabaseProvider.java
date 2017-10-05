package hibernate.data_hibernate_multitenancydatabase_14.multitenancy;

import com.zaxxer.hikari.hibernate.HikariConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * Database selecting multitenant provider.
 *
 * Works with H2 database and HikariCP pool.
 */
public class MultitenantDatabaseProvider extends AbstractMultiTenantConnectionProvider {
    /**
     * As on the top we are Serializable, we have to put that.
     */
    private static final long serialVersionUID = 42L;
    /**
     * Provides common db parameters.
     * @return parameters map.
     */
    private Map<String,String> dbParameters() {
        Map<String,String> parameters = new HashMap<>();
        parameters.put("hibernate.hikari.dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
        parameters.put("hibernate.hikari.username", "test");
        parameters.put("hibernate.hikari.password", "test");
        parameters.put("hibernate.hikari.maximumPoolSize", "2");

        return parameters;
    }

    @Override
    protected ConnectionProvider getAnyConnectionProvider() {
        Map<String,String> parameters = dbParameters();
        parameters.put("hibernate.hikari.dataSource.url", "jdbc:postgresql://127.0.0.1:5432/test");

        HikariConnectionProvider p =new HikariConnectionProvider();
        p.configure(parameters);
        return p;
    }

    @Override
    protected ConnectionProvider selectConnectionProvider(String s) {
        Map<String,String> parameters = dbParameters();
        parameters.put("hibernate.hikari.dataSource.url", "jdbc:postgresql://127.0.0.1:5432/"+s);

        HikariConnectionProvider p =new HikariConnectionProvider();
        p.configure(parameters);
        return p;
    }
}

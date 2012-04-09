package gov.nih.nci.integration.dao;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * @author chandrasekaravr
 */
@Configuration
public class JpaConfig {
	/**
     * The datasource url.
     */
    @Value("${ihub.messages.db.url}")
    private String url;

    /**
     * The datasource username.
     */
    @Value("${ihub.messages.db.username}")
    private String username;

    /**
     * The datasource password.
     */
    @Value("${ihub.messages.db.password}")
    private String password;

    /**
     * The datasource driver class name.
     */
    @Value("${ihub.messages.db.driver}")
    private String driverClassName;



    // Hibernate/JPA properties
    /**
     * The database platform.
     */
    @Value("${ihub.messages.db.hibernate.dialect}")
    private String databasePlatform;
    
    /**
     * Flag to print all hibernate sql
     */
    @Value("${ihub.messages.db.hibernate.showsql}")
    private boolean showSql;

    /**
     * The persistence unit name.
     */
    protected static final String PERSISTENCE_UNIT_NAME = "ihub-messages";


     /**
     * Will return the Data source.
     *
     * @return the data source
     */
    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        final BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(url);
        dataSource.setPassword(password);
        dataSource.setUsername(username);
        dataSource.setDriverClassName(driverClassName);

        return dataSource;
    }

    /**
     * Hiberante Jpa vendor adapter.
     *
     * @return the hibernate jpa vendor adapter
     */
    @Bean
    public HibernateJpaVendorAdapter jpaVendorAdapter() {
        final HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        System.out.println("databasePlatform = " + databasePlatform);
        jpaVendorAdapter.setDatabasePlatform(databasePlatform);
        jpaVendorAdapter.setShowSql(showSql);
        
        return jpaVendorAdapter;
    }

    /**
     * Entity manager factory.
     *
     * @return the entity manager factory
     */
    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        
        entityManagerFactoryBean.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        
        entityManagerFactoryBean.setPersistenceXmlLocation("classpath*:META-INF/ihub-messages-persistence.xml");

        // must set the properties
        entityManagerFactoryBean.afterPropertiesSet();
        return entityManagerFactoryBean.getObject();
    }
    
    /**
     * Returns JPA tx manager.
     *
     * @return the jpa transaction manager
     */
    @Bean
    public JpaTransactionManager transactionManager() {
        final JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory());
        jpaTransactionManager.setDataSource(dataSource());
        return jpaTransactionManager;
    }

}


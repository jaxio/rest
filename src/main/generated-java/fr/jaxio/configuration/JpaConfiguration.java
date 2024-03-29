/*
 * (c) Copyright 2005-2013 JAXIO, http://www.jaxio.com
 * Source code generated by Celerio, a Jaxio product
 * Want to use Celerio within your company? email us at info@jaxio.com
 * Follow us on twitter: @springfuse
 * Template pack-backend-jpa:src/main/java/project/configuration/JpaConfiguration.p.vm.java
 */
package fr.jaxio.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.ejb.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class JpaConfiguration {

    /**
     * Enable exception translation for beans annotated with @Repository
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    /**
     * @see read http://www.springframework.org/docs/reference/transaction.html
     */
    @Bean
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Value("classpath:hibernate.properties")
    private Properties jpaProperties;

    /**
     * Build the entity manager with Hibernate as a provider.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        // We set the persistenceXmlLocation to a different name to make it work on JBoss.
        emf.setPersistenceXmlLocation("classpath:META-INF/spring-persistence.xml");
        emf.setPersistenceUnitName("restPU");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setJpaProperties(jpaProperties);
        return emf;
    }

    @Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.getSessionFactory();
    }
}

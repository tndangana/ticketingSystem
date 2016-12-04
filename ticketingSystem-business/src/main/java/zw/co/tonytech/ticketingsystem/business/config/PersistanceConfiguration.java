/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zw.co.tonytech.ticketingsystem.business.config;

import java.util.Properties;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.ejb.HibernatePersistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;





/**
 *
 * @author tndangana
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(value = {"zw.co.tonytech.ticketingsystem.business.repository"})
@ComponentScan(basePackages = {"zw.co.tonytech.ticketingsystem.business.service.impl"})
@PropertySource("classpath:zw/co/tonytech/ticketingsystem/business/config/jdbc_dev.properties")
public class PersistanceConfiguration {
    
    
    @Resource
    private Environment environment;

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        
        //This should solve the notorious broken pipe exception
        dataSource.setValidationQuery("SELECT 1");
        dataSource.setTestOnBorrow(true);
        dataSource.setRemoveAbandoned(true);
        dataSource.setRemoveAbandonedTimeout(60);
        dataSource.setLogAbandoned(true);
        
        return dataSource;
    }
      @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPackagesToScan(new String[]{"zw.co.tonytech.ticketingsystem.business.domain"});
        entityManagerFactory.setPersistenceProviderClass(HibernatePersistence.class);
        Properties jpaProperties = new Properties();
        jpaProperties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        jpaProperties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        jpaProperties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        entityManagerFactory.setJpaProperties(jpaProperties);
        return entityManagerFactory;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
    
  
    @Configuration
    @Profile("development")
    @PropertySource("classpath:zw/co/tonytech/ticketingsystem/business/config/jdbc_dev.properties")
    static class Development {
    }

    @Configuration
    @Profile("test")
    @PropertySource("classpath:zw/co/tonytech/ticketingsystem/business/config/jdbc_test.properties")
    static class Test {
    }

    @Configuration
    @Profile("production")
    @PropertySource("classpath:zw/co/tonytech/ticketingsystem/business/config/jdbc_prod.properties")
    static class Production {
    }
}

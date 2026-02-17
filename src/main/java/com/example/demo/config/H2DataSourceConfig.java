package com.example.demo.config;
 
import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
 
@Configuration
@EnableJpaRepositories(
    basePackages = "com.example.demo.repos.h2",
    entityManagerFactoryRef = "h2EntityManagerFactory",
    transactionManagerRef   = "h2TransactionManager"
)
//this tells all repos in h2... to use h2 entitiymanager and transaction manager
 
public class H2DataSourceConfig {
 
	//bean to create and connect to h2 db from application properties
	//connect at prefix spring.h2.datasource
    @Primary
    @Bean(name = "h2DataSource")
    @ConfigurationProperties(prefix = "spring.h2.datasource")
    public DataSource h2DataSource() {
        return DataSourceBuilder.create().build();
    }
 
    @Primary//imp coz 2 datasources...h2 is the default
    @Bean(name = "h2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory(
            @Qualifier("h2DataSource") DataSource dataSource) {
 
        LocalContainerEntityManagerFactoryBean em =
                new LocalContainerEntityManagerFactoryBean();
 
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.demo.entities");
        em.setPersistenceUnitName("h2");
 
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(adapter);
 
        HashMap<String, Object> props = new HashMap<>();
        props.put("hibernate.hbm2ddl.auto", "update");//spring.jpa.hibernate.ddl-auto=update
        props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        //generate sql that h2 understands
        em.setJpaPropertyMap(props);
 
        return em;//em is the entity manager factory
    }
 
    @Primary
    @Bean(name = "h2TransactionManager") //control commit rollback so that failure of 1 db wont affect other
    public PlatformTransactionManager h2TransactionManager(
            @Qualifier("h2EntityManagerFactory")
            LocalContainerEntityManagerFactoryBean factory) {
        return new JpaTransactionManager(factory.getObject());
    }
    //StudentH2Repository will get datasource,entitymanager,transactionmanager
}
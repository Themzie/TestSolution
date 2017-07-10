package com.econetwireless.testsolution.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;

/**
 * Created by thembelani on 7/8/17.
 */
@Configuration
@PropertySource("classpath:jdbc.properties")
@Import(JpaVendorConfig.class)
@EnableJpaRepositories("com.econetwireless.testsolution.repo")
public class ApplicationInfastructure {

    @Bean
    public DataSource dataSource(Environment environment) {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter vendorAdapter,
                                                                       Environment environment, ResourceLoader resourceLoader) throws IOException {
        final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setPackagesToScan("com.econetwireless.testsolution.model");
        emf.setPersistenceUnitName("testBench");
        emf.setJpaVendorAdapter(vendorAdapter);
        final String jpaPropertiesConfigPath = environment.getProperty("jpa.vendor.properties.config");
        if(StringUtils.hasText(jpaPropertiesConfigPath)){
            final Resource jpaPropertiesConfig = resourceLoader.getResource(jpaPropertiesConfigPath);
            emf.setJpaProperties(PropertiesLoaderUtils.loadProperties(jpaPropertiesConfig));
        }
        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


}

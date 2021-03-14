package com.transaction.config.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Objects;

/*@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "subEntityManagerFactory",
        transactionManagerRef = "subTransactionManager",
        basePackages = {"com.transaction.domain.board"}
)*/
public class SubSchemaDataSourceConfig {
/*
    private final Environment env;

    @Autowired
    public SubSchemaDataSourceConfig(Environment env) {
        this.env = env;
    }

    @Bean(name = "subDataSource")
    //@ConfigurationProperties(prefix = "sub")
    public DataSource chatDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.datasource.driver-class-name")));
        dataSource.setUrl(env.getProperty("sub.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        return dataSource;
    }

    @Bean(name = "subEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean chatEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                            @Qualifier("subDataSource") DataSource chatDataSource) {
        return builder
                .dataSource(chatDataSource)
                .packages("com.transaction.entities")
                .persistenceUnit("sub")
                .build();
    }

    @Bean(name = "subTransactionManager")
    public PlatformTransactionManager chatTransactionManager(
            @Qualifier("subEntityManagerFactory") EntityManagerFactory subEntityManagerFactory) {
        return new JpaTransactionManager(subEntityManagerFactory);
    }*/
}

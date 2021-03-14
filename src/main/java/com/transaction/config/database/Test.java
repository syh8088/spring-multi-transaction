package com.transaction.config.database;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
/*

@Configuration
@EnableConfigurationProperties(Hikari1Properties.class)
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "mainEntityManagerFactory",
        transactionManagerRef = "mainTransactionManager",
        basePackages = {"com.transaction.domain.member"}
)
public class Test {

*/
/*    private final Environment env;

    @Autowired
    public MainHikariSchemaDataSourceConfig(Environment env) {
        this.env = env;
    }*//*


    //@Primary
    //@ConfigurationProperties(prefix = "spring.hikari1")
    @Bean(name = "mainDataSource")
    //public HikariDataSource mainDataSource() {
    public HikariDataSource mainDataSource(Hikari1Properties properties) {
  */
/*      HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(Objects.requireNonNull(env.getProperty("spring.hikari1.datasource.driver-class-name")));
        hikariDataSource.setJdbcUrl(env.getProperty("spring.hikari1.datasource.url"));
        hikariDataSource.setUsername(env.getProperty("spring.hikari1.datasource.username"));
        hikariDataSource.setPassword(env.getProperty("spring.hikari1.datasource.password"));
        return hikariDataSource;*//*

        return DataSourceCreator.createHikariDataSource(properties);
    }

    //@Primary
    @Bean(name = "mainEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean mainEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                           @Qualifier("mainDataSource") HikariDataSource mainDataSource) {
        return builder
                .dataSource(mainDataSource)
                .packages("com.transaction.entities")
                .persistenceUnit("main")
                .build();
    }

    @Primary
    @Bean(name = "mainTransactionManager")
    public PlatformTransactionManager mainTransactionManager(
            @Qualifier("mainEntityManagerFactory") EntityManagerFactory mainEntityManagerFactory) {
        return new JpaTransactionManager(mainEntityManagerFactory);
    }
}
*/

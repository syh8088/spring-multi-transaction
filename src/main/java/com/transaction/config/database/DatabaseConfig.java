package com.transaction.config.database;

import com.google.common.collect.ImmutableMap;
import com.zaxxer.hikari.HikariConfig;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import javax.sql.DataSource;
import java.io.IOException;

public abstract class DatabaseConfig extends HikariConfig {
    public static final String BASE_MAPPER_PACKAGE_PREFIX = "com.transaction.domain";
    public static final String BASE_ENTITY_PACKAGE_PREFIX = "com.transaction.domain";

    protected void setConfigureEntityManagerFactory(LocalContainerEntityManagerFactoryBean factory) {
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setJpaPropertyMap(ImmutableMap.of(
                "hibernate.hbm2ddl.auto", "none",
                "hibernate.dialect", "org.hibernate.dialect.MariaDB53Dialect",
                "hibernate.show_sql", "true",
                "hibernate.format_sql", "true",
                "open-in-view", "false"
        ));
        factory.afterPropertiesSet();
    }

    protected void setConfigureSqlSessionFactory(SqlSessionFactoryBean sessionFactoryBean, DataSource dataSource) throws IOException {
        sessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:/mapper/**/*.xml"));
    }
}
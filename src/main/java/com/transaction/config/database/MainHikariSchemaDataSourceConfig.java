package com.transaction.config.database;

import com.transaction.domain.member.MemberMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource." + "main")
@EnableJpaRepositories(
        entityManagerFactoryRef = "mainEntityManagerFactory",
        transactionManagerRef = "mainTransactionManager",
        basePackages = {DatabaseConfig.BASE_ENTITY_PACKAGE_PREFIX + ".member"}
)
@MapperScan(
        basePackages = {DatabaseConfig.BASE_MAPPER_PACKAGE_PREFIX + ".member"}
)
public class MainHikariSchemaDataSourceConfig extends DatabaseConfig {

    final String name = "main";

    @Bean(name = name + "DataSource")
    @Primary
    public DataSource dataSource() {
        return new LazyConnectionDataSourceProxy(new HikariDataSource(this));
    }


    /* -----------------mybatis 셋팅------------------------------------- */

/*    @Bean(name = name + "SessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier(name + "DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        setConfigureSqlSessionFactory(sessionFactoryBean, dataSource);
        return sessionFactoryBean.getObject();
    }*/

    @Bean(name = name + "SessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier(name + "DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        return sessionFactoryBean.getObject();
    }

    @Bean(name = name + "SqlSessionTemplate")
    @Primary
    public SqlSessionTemplate firstSqlSessionTemplate(@Qualifier(name + "SessionFactory") SqlSessionFactory mainSessionFactory) {
        return new SqlSessionTemplate(mainSessionFactory);
    }

/*
    @Bean(name = name + "MapperFactoryBean")
    public MapperFactoryBean<MemberMapper> memberMapper(@Qualifier(name + "SessionFactory") SqlSessionFactory mainSessionFactory) {

        MapperFactoryBean<MemberMapper> factoryBean = new MapperFactoryBean<>(MemberMapper.class);
        factoryBean.setSqlSessionFactory(mainSessionFactory);
        return factoryBean;
    }

    @Bean(name = name + "TxManager")
    @Primary
    public PlatformTransactionManager mainTxManager(@Qualifier(name + "DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
*/


    /* -----------------JPA 셋팅------------------------------------- */
//    @Bean(name = name + "EntityManagerFactory")
//    @Primary
//    public EntityManagerFactory entityManagerFactory(@Qualifier(name + "DataSource") DataSource dataSource) {
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setDataSource(dataSource);
//        factory.setPackagesToScan("com.transaction.entities");
//        factory.setPersistenceUnitName(name);
//        setConfigureEntityManagerFactory(factory);
//
//        return factory.getObject();
//    }

// 컨테이너가 관리하는 EntityManagerFactory를 생성.
    @Bean(name = name + "EntityManagerFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier(name + "DataSource") DataSource dataSource) {


        return builder
                .dataSource(dataSource)
                .packages("com.transaction.entities")
                .persistenceUnit(name)
                .build();
    }


    @Bean(name = name + "TransactionManager")
    //@Primary
    public PlatformTransactionManager transactionManager(@Qualifier(name + "EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);

        return jpaTransactionManager;
    }
}

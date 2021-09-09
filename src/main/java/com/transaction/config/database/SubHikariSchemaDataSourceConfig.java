package com.transaction.config.database;

import com.transaction.domain.member.MemberMapper;
import com.transaction.domain.role.RoleMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
//@EnableConfigurationProperties(Hikari2Properties.class)
@ConfigurationProperties(prefix = "spring.datasource." + "sub")
@EnableJpaRepositories(
        entityManagerFactoryRef = "subEntityManagerFactory",
        transactionManagerRef = "subTransactionManager",
        basePackages = {DatabaseConfig.BASE_ENTITY_PACKAGE_PREFIX + ".role", DatabaseConfig.BASE_ENTITY_PACKAGE_PREFIX + ".board"}
)
@MapperScan(
        basePackages = {DatabaseConfig.BASE_MAPPER_PACKAGE_PREFIX + ".role", DatabaseConfig.BASE_MAPPER_PACKAGE_PREFIX + ".board"}
)
public class SubHikariSchemaDataSourceConfig extends DatabaseConfig {

    final String name = "sub";

    @Bean(name = name + "DataSource")
    public DataSource dataSource() {
        return new LazyConnectionDataSourceProxy(new HikariDataSource(this));
    }

    /* -----------------mybatis 셋팅------------------------------------- */

    @Bean(name = name + "SessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier(name + "DataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        setConfigureSqlSessionFactory(sessionFactoryBean, dataSource);

        return sessionFactoryBean.getObject();
    }

//    @Bean(name = name + "SessionFactory")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier(name + "DataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
//        sessionFactoryBean.setDataSource(dataSource);
//
//        return sessionFactoryBean.getObject();
//    }

    @Bean(name = name + "SqlSessionTemplate")
    public SqlSessionTemplate firstSqlSessionTemplate(@Qualifier(name + "SessionFactory") SqlSessionFactory subSessionFactory) {
        return new SqlSessionTemplate(subSessionFactory);
    }

/*    @Bean(name = name + "MapperFactoryBean")
    public MapperFactoryBean<RoleMapper> roleMapper(@Qualifier(name + "SessionFactory") SqlSessionFactory subSessionFactory) {

        MapperFactoryBean<RoleMapper> factoryBean = new MapperFactoryBean<>(RoleMapper.class);
        factoryBean.setSqlSessionFactory(subSessionFactory);
        return factoryBean;
    }

    @Bean(name = name + "TxManager")
    // @Primary
    public PlatformTransactionManager subTxManager(@Qualifier(name + "DataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }*/

    /* -----------------JPA 셋팅------------------------------------- */
//    @Bean(name = name + "EntityManagerFactory")
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
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder, @Qualifier(name + "DataSource") DataSource dataSource) {

        return builder
                .dataSource(dataSource)
                .packages("com.transaction.entities")
                .persistenceUnit(name)
                .build();
    }

    @Bean(name = name + "TransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier(name + "EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);

        return jpaTransactionManager;
    }
}
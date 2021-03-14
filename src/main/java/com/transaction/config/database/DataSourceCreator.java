package com.transaction.config.database;

import com.zaxxer.hikari.HikariDataSource;
import lombok.experimental.UtilityClass;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@UtilityClass
public class DataSourceCreator {
    public HikariDataSource createHikariDataSource(MainHikariSchemaDataSourceConfig mainHikariSchemaDataSourceConfig) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:log4jdbc:mariadb://dfs-dev.c9wn7u8i3zyy.ap-northeast-2.rds.amazonaws.com:5859/DB_TEST?useUnicode=true&allowMultiQueries=true");
        dataSource.setUsername(mainHikariSchemaDataSourceConfig.getUsername());
        dataSource.setPassword(mainHikariSchemaDataSourceConfig.getPassword());
        dataSource.setDriverClassName(mainHikariSchemaDataSourceConfig.getDriverClassName());

        return dataSource;
    }
    public HikariDataSource createHikariDataSource(SubHikariSchemaDataSourceConfig mainHikariSchemaDataSourceConfig) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:log4jdbc:mariadb://dfs-dev.c9wn7u8i3zyy.ap-northeast-2.rds.amazonaws.com:5859/DB_TEST2?useUnicode=true&allowMultiQueries=true");
        dataSource.setUsername(mainHikariSchemaDataSourceConfig.getUsername());
        dataSource.setPassword(mainHikariSchemaDataSourceConfig.getPassword());
        dataSource.setDriverClassName(mainHikariSchemaDataSourceConfig.getDriverClassName());

        return dataSource;
    }
  /*  public static DataSource createHikariDataSource(MainHikariSchemaDataSourceConfig mainHikariSchemaDataSourceConfig) {
        mainHikariSchemaDataSourceConfig
    }*/
}

package com.transaction.config.database;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

//@ConfigurationProperties(prefix = "spring.datasource.sub")
public class Hikari2Properties extends DataSourceProperties {
}

package com.transaction.config.database;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;

//@ConfigurationProperties(prefix = "spring.datasource.main")
public class Hikari1Properties extends DataSourceProperties {
}

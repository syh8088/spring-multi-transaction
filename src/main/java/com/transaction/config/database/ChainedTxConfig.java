package com.transaction.config.database;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ChainedTxConfig {

    @Bean
    @Primary
    public PlatformTransactionManager transactionManager(PlatformTransactionManager mainTransactionManager, PlatformTransactionManager subTransactionManager) {
        return new ChainedTransactionManager(mainTransactionManager, subTransactionManager);
    }
}

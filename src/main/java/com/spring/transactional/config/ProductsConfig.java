package com.spring.transactional.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@Component
// @EnableTransactionManagement // For SpringBoot it's not required
public class ProductsConfig {

    @Bean
    public DataSource dataSource(){
        return new DriverManagerDataSource(
                "jdbc:mysql://localhost:3306/Products",
                "root",
                "password"
        );
    }
    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }

    // For SpringBoot it's not required.
//    @Bean
//    public TransactionManager transactionManager(DataSource dataSource){
//        return new DataSourceTransactionManager(dataSource);
//    }


}

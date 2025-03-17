package com.spring.transactional.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Configuration
@Component
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




}

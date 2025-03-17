package com.spring.transactional.repository;

import com.spring.transactional.entity.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ProductRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveProduct(Products products){

        // First, check if the product with the given id exists
        String checkQuery = "SELECT COUNT(*) FROM PRODUCTS WHERE id = ?";
        int count = jdbcTemplate.queryForObject(checkQuery, Integer.class, products.getId());

        if (count == 0) {
            // If no record exists, proceed with the insert
            String sql = "INSERT INTO PRODUCTS (id, name) VALUES (?, ?)";
            Object[] args = {products.getId(), products.getName()};
            jdbcTemplate.update(sql, args);
            System.out.println("Product Saved .....");
        } else {
            System.out.println("Product with id " + products.getId() + " already exists.");
        }
    }
}

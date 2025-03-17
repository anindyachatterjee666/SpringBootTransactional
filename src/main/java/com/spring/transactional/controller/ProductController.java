package com.spring.transactional.controller;

import com.spring.transactional.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    ProductService productService;


    @PostMapping("/saveProduct")
    public void saveProuct(){
        productService.saveInfo();
    }
}

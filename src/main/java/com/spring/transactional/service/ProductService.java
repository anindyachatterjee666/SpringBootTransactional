package com.spring.transactional.service;

import com.spring.transactional.entity.Products;
import com.spring.transactional.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;
    Products product = new Products();

    @Transactional
    public void saveInfo(){
        for(int i=1; i<=10; i++){
            product.setId(i);
            product.setName("Product Name: " + i);

            productRepo.saveProduct(product);

            //explicitly throwing exception
            if(product.getId() == 7){
                throw new RuntimeException("Error occured for id: " + product.getId());
            }
        }
    }
}

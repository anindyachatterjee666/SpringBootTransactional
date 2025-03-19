package com.spring.transactional.service;

import com.spring.transactional.entity.Products;
import com.spring.transactional.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;
    Products product = new Products();

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveInfo() throws Exception {
        for (int i = 1; i <= 10; i++) {
            product.setId(i);
            product.setName("Product Name: " + i);

            productRepo.saveProduct(product);

            //explicitly throwing exception
            if (product.getId() == 7) {
                throw new RuntimeException("Error occured for id: " + product.getId());
            }
        }
    } // Transaction will be committed
}


/*

AOP - Aspect Oriented Programming -> we generally write non-functional code in the aop
Aspect {
    1. start the Transaction
    2. call your method
    3. commit Transaction
    4. close the connection
}

in the code -> Spring AOP is calling the saveInfo() through a Proxy class.

Note ->
1. If we are handling the exception(e.g., using try-catch ) then the Transaction won't be rolled back. In order to make the Transaction rolled back the exception needs to be propagated out of saveInfo(). If we are inside the method and handling the exception then Transaction won't be rolled back.

    code Example ->
    @Transactional
    public void saveInfo() {
        try {
            for (int i = 1; i <= 10; i++) {
                product.setId(i);
                product.setName("Product Name: " + i);

                productRepo.saveProduct(product);

                //explicitly throwing exception
                if (product.getId() == 7) {
                    throw new RuntimeException("Error occured for id: " + product.getId());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    } // Transaction will be committed


2. If we are throwing RunTimeException & not handling it inside the method (saveInfo()) then Transaction will be rolled back by default.

    Code Example ->
    @Transactional
    public void saveInfo()  {
        for (int i = 1; i <= 10; i++) {
            product.setId(i);
            product.setName("Product Name: " + i);

            productRepo.saveProduct(product);

            //explicitly throwing exception
            if (product.getId() == 7) {
                throw new RuntimeException("Error occured for id: " + product.getId());
            }
        }
    } // Transaction will be committed


3. Instead of RunTimeException if we throw CheckedException Transaction won't be rolled back.

    Code Example ->
    @Transactional
    public void saveInfo() throws Exception {
        for (int i = 1; i <= 10; i++) {
            product.setId(i);
            product.setName("Product Name: " + i);

            productRepo.saveProduct(product);

            //explicitly throwing exception
            if (product.getId() == 7) {
                throw new Exception("Error occured for id: " + product.getId());
            }
        }
    } // Transaction will be committed


4. For any checked Exception if we want to rollback then we should use @Transactional(rollbackFor = Exception.class)

    Code Example ->
    @Transactional(rollbackFor = Exception.class)
    public void saveInfo() throws Exception {
        for (int i = 1; i <= 10; i++) {
            product.setId(i);
            product.setName("Product Name: " + i);

            productRepo.saveProduct(product);

            //explicitly throwing exception
            if (product.getId() == 7) {
                throw new Exception("Error occured for id: " + product.getId());
            }
        }
    } // Transaction will be committed

5. If we want there should not be any rollBack for the RuntimeException so we should use @Transactional(noRollbackFor = RuntimeException.class)

    CodeExample ->
    @Transactional(noRollbackFor = RuntimeException.class)
    public void saveInfo() throws Exception {
        for (int i = 1; i <= 10; i++) {
            product.setId(i);
            product.setName("Product Name: " + i);

            productRepo.saveProduct(product);

            //explicitly throwing exception
            if (product.getId() == 7) {
                throw new RuntimeException("Error occured for id: " + product.getId());
            }
        }
    } // Transaction will be committed


6. @Transactional(propagation = Propagation.REQUIRED) -> It always starts a new Transaction in-case a Transaction           doesn't exist. If it exists it uses the same one.
     If no transaction
 */
package com.scaler.productservice;

import com.scaler.productservice.Projections.ProductWithIdAndPriceProjection;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    ProductRepository productRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void testProductrepository() {
//        List<Product> productsList = productRepository.findAllByCategory_Title("Test"); //testing findAllByCategory_Title method from ProductRepository class
//        System.out.println(productsList);

        List<ProductWithIdAndPriceProjection> productsList = productRepository.getProductIdAndPriceByGivenCategoryName("Test");

        for (ProductWithIdAndPriceProjection productWithIdAndPriceProjection : productsList) {
            System.out.println(productWithIdAndPriceProjection.getPrice() + " " + productWithIdAndPriceProjection.getId());
        }
        System.out.println();
    }

}



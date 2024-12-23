package com.scaler.productservice;
import com.scaler.productservice.models.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args)
    {
        Product product = new Product();
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}

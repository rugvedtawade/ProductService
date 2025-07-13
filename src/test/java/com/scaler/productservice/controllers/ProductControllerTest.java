package com.scaler.productservice.controllers;

import com.scaler.productservice.Exceptions.ProductNotFoundException;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    productController productController;

    @Mock
    ProductService productService;

    @Test
    public void Test_GetProductDetailsWithPositiveId_ReturnsProductSuccessfully() throws ProductNotFoundException {
        //Arrange
        Long id  = 2L;
        Product product = new Product();
        product.setId(id);
        product.setTitle("test product");

        when(productService.getSingleProduct(id)).thenReturn(product);

        //Act
        Product response = productController.getSingleProduct(id);

        //Assert
        assertNotNull(response);
        assertEquals(product.getTitle(),response.getTitle());
        assertEquals(id,response.getId());
    }


}

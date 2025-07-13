package com.scaler.productservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(productController.class)
@Import(ProductControllerMvcTest.MockConfig.class)
public class ProductControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Qualifier("selfProductService")
    private ProductService productService;

    @BeforeEach
    void setup() {
        // Reset the mock before each test (optional)
        Mockito.reset(productService);
    }

    @Test
    public void Test_GetAllProducts_RunSuccessfully() throws Exception {
        // Arrange
        Long id = 2L;
        Product product = new Product();
        product.setId(id);
        product.setTitle("Iphone");
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        when(productService.getAllProducts()).thenReturn(productList);

        String expectedResponse = objectMapper.writeValueAsString(productList);

        // Act + Assert
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }

    @TestConfiguration
    static class MockConfig {
        @Bean
        @Qualifier("selfProductService")
        public ProductService selfProductService() {
            return Mockito.mock(ProductService.class);
        }
    }
}

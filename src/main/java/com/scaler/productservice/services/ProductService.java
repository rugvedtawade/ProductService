package com.scaler.productservice.services;
import java.util.List;
import com.scaler.productservice.dtos.CreateProductRequestDTO;
import com.scaler.productservice.models.Product;

public interface ProductService
{
    List<Product> getAllProducts();
    Product getSingleProduct(long id);
//  Product createProduct(CreateProductRequestDTO createProductRequestDTO); // method who takes passed DTO object by controller
    Product createProduct(String title,     // method taking params from Controller
                      String description,
                      String image,
                      String category,
                      double price);
}

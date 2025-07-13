package com.scaler.productservice.services;
import java.util.List;

import com.scaler.productservice.Exceptions.ProductNotFoundException;
import com.scaler.productservice.Exceptions.UnauthorizedUserException;
import com.scaler.productservice.Exceptions.UserNotFoundException;
import com.scaler.productservice.dtos.CreateProductRequestDTO;
import com.scaler.productservice.models.Product;
import org.springframework.data.domain.Page;

public interface ProductService
{
    List<Product> getAllProducts();
    Product getSingleProduct(long id) throws ProductNotFoundException;
//  Product createProduct(CreateProductRequestDTO createProductRequestDTO); // method who takes passed DTO object by controller
    Product createProduct(String title,     // method taking params from Controller
                      String description,
                      String image,
                      String category,
                      double price);

    Page<Product> getPaginatedProducts(int pageNo, int pageSize);

    Product getDetailsBasedOnUserScope(Long productId,Long userId) throws ProductNotFoundException, UserNotFoundException, UnauthorizedUserException;
}

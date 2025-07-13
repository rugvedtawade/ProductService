package com.scaler.productservice.controllers;
import java.util.List;

import com.scaler.productservice.Exceptions.ProductNotFoundException;
import com.scaler.productservice.Exceptions.UnauthorizedUserException;
import com.scaler.productservice.Exceptions.UserNotFoundException;
import com.scaler.productservice.dtos.CreateProductRequestDTO;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

@RestController
public class productController
{
    private ProductService productService;  //not using new word , rather using constructor for DI

    public productController(@Qualifier("selfProductService") ProductService productService) // no need to write Fake store wala part , IOC will understand the current service
    {
        this.productService = productService;       //creation of service object to call it
    }


    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getSingleProduct(@PathVariable("id") long id) throws ProductNotFoundException
    //either handle the exception or throw it further
    {
        return productService.getSingleProduct(id);
    }

//    @PostMapping("/products")
//    public Product createProduct(@RequestBody CreateProd 1uctRequestDTO createProductdto)   // in case of FakeStore DB fetching
//    {
//        return productService.createProduct(createProductdto);
//    }

//    @PostMapping("/products")
//    public Product createProduct(@RequestBody CreateProductRequestDTO createProductRequestDTO)    // directly sending DTO object to Service
//    {
//        return productService.createProduct(createProductRequestDTO); // passing reference
//    }


    @GetMapping("/products/paginated")
        //accept filter params which you're going to provide to the service
    Page<Product> getPaginatedProducts(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize){
        //should i return page of prod or list of prod to the frontend??
        //please explore how to convert Page<T> to List<T> - HW
        return productService.getPaginatedProducts(pageNo, pageSize);
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDTO createProductRequestDTO){
        return productService.createProduct(createProductRequestDTO.getTitle(), // passing params for validation convenience
                createProductRequestDTO.getDescription(),
                createProductRequestDTO.getImage(),
                createProductRequestDTO.getCategory(),
                createProductRequestDTO.getPrice());
    }


    @GetMapping("/products/{productId}/{userId}")
    public Product getProductDetailsBasedOnUserScope(@PathVariable Long productId,@PathVariable Long userId)
            throws ProductNotFoundException, UserNotFoundException, UnauthorizedUserException {
        return productService.getDetailsBasedOnUserScope(productId,userId);
    }
    //http://localhost:5000/products/1/1 - return Product (as only Admin user with userid-1 is authorized to see Test product)
    //http://localhost:5000/products/1/2 - gives UnauthorizedUserException (Client users are not allowed to see test product)
    //http://localhost:5000/products/1/100 - gives UserNotFoundException
    //http://localhost:5000/products/100/1 - ProductNotFoundException
}


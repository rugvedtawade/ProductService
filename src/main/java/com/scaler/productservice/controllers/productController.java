package com.scaler.productservice.controllers;
import java.util.List;
import com.scaler.productservice.dtos.CreateProductRequestDTO;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
public class productController
{
    private ProductService productService;  //not using new word , rather using constructor for DI

    public productController(@Qualifier("fakeStoreProductService") ProductService productService) // no need to write Fake store wala part , IOC will understand the current service
    {
        this.productService = productService;       //creation of service object to call it
    }


    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public List<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getSingleProduct(@PathVariable("id") long id)
    {
        return productService.getSingleProduct(id);
    }

//    @PostMapping("/products")
//    public Product createProduct(@RequestBody CreateProductRequestDTO createProductdto)   // in case of FakeStore DB fetching
//    {
//        return productService.createProduct(createProductdto);
//    }

//    @PostMapping("/products")
//    public Product createProduct(@RequestBody CreateProductRequestDTO createProductRequestDTO)    // directly sending DTO object to Service
//    {
//        return productService.createProduct(createProductRequestDTO); // passing reference
//    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDTO createProductRequestDTO){
        return productService.createProduct(createProductRequestDTO.getTitle(), // passing params for validation convenience
                createProductRequestDTO.getDescription(),
                createProductRequestDTO.getImage(),
                createProductRequestDTO.getCategory(),
                createProductRequestDTO.getPrice());
    }

    //HW - Create a ResponseDTO and send that object instead of Product directly

}


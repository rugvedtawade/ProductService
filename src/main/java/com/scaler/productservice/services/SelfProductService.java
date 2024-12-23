package com.scaler.productservice.services;

import com.scaler.productservice.repository.CategoryRepository;
import com.scaler.productservice.repository.ProductRepository;
import com.scaler.productservice.dtos.CreateProductRequestDTO;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.models.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("selfProductService")
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    public SelfProductService(
            ProductRepository productRepository,
            CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct(long id) {
        return null;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
        //get all products from product table
    }

//    @Override
//    public Product createProduct(CreateProductRequestDTO createProductRequestDTO) // in case of DTO object sent by controller
//    {
//        return null;
//    }

    @Override
    public Product createProduct(String title,
                                 String description,
                                 String image,
                                 String category,//aparrel
                                 double price) {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageUrl(image);

        // product.setCategory(category); we can not directly do that , as product has category variable which is not string but is Object
        // so we will create object of category and then assign to Product. BUT BUT BUT - you need to 1st check if same obj is present in DB table, then no need to create obj
        //You need to get the corresponding category object from the
        //category table -> you need to check with a category with the
        //name in the param exist or not?

        Category categoryFromDB = categoryRepository.findByTitle(category);

        if (categoryFromDB == null) {
            Category newCategory = new Category();
            newCategory.setTitle(category);
            product.setCategory(newCategory);
            //categoryRepository.save(newCategory); //category insert before product creation - choice
        } else {
            product.setCategory(categoryFromDB);
        }

        //insert the prod in prod table?


        Product createdProduct = productRepository.save(product);
        //fk contrainsts

        return createdProduct;
    }
}
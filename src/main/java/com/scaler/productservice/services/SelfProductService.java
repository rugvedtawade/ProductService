package com.scaler.productservice.services;

import com.scaler.productservice.Exceptions.ProductNotFoundException;
import com.scaler.productservice.Exceptions.UnauthorizedUserException;
import com.scaler.productservice.Exceptions.UserNotFoundException;
import com.scaler.productservice.dtos.UserDto;
import com.scaler.productservice.repository.CategoryRepository;
import com.scaler.productservice.repository.ProductRepository;
import com.scaler.productservice.dtos.CreateProductRequestDTO;
import com.scaler.productservice.models.Product;
import com.scaler.productservice.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    private RestTemplate restTemplate;

    public SelfProductService(
            ProductRepository productRepository,
            CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getSingleProduct (long id) throws ProductNotFoundException {  //throws -  Declares exceptions that a method can throw, informing the caller to handle or propagate them.
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            //either you can throw an exception further (to parent) or you handle it
            throw new ProductNotFoundException("Product with given id does not exist");
        }
        return optionalProduct.get();
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
    public Page<Product> getPaginatedProducts(int pageNo, int pageSize) { //controller class will give pageNo and pageSize
        //Pageable was an interface
        //PageRequest class
        //Can I pass an object of PageRequest class in the place of pageable param inside findAll
        //Animal <--- Dog (Animal animal = new Dog())
        //Pageable <--- PageRequest
        return productRepository.findAll(PageRequest.of(    // "of" is a method inside PageRequest class which returns an object of PageRequest class itself with passed params
                pageNo,
                pageSize,
                Sort.by("title").descending().and(Sort.by("price").ascending())));
    }

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

    @Override
    public Product getDetailsBasedOnUserScope(Long productId, Long userId)
            throws ProductNotFoundException,UserNotFoundException,UnauthorizedUserException {

        Optional<Product> optionalProduct = productRepository.findById(productId);

        if(optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("No product found with ID: " + productId);
        }

        Product product = optionalProduct.get();

        //check for product scope - Test or Other
        if(product.getCategory().getTitle().equals("Test")){
            //Call to UserService to get User Detail
            //if user is not null and user is 'Admin' we will return product , else we will throw exception.
            UserDto userDto = restTemplate.getForObject("http://userauthservice/users/{userId}",UserDto.class,userId);

            if(userDto == null) {
                throw new UserNotFoundException("No user details found for user ID: " + userId);
            }

            if(userDto.getUserRole().equals("Admin")){
                return product;
            }else {
                throw new UnauthorizedUserException("User is not authorized to access this product.");
            }
        }

        return product;
    }

}
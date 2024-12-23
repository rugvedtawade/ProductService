package com.scaler.productservice.dtos;

import com.scaler.productservice.models.Category;
import com.scaler.productservice.models.Product;

public class FakeStoreProductDto {
    private String title;
    private String description;
    private double price;
    private String category;
    private String image;

    public Product toProduct() {
        Product product = new Product();
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);

        Category category1 = new Category();    // created object - int id, String Title
        category1.setTitle(category);   //set string title
        product.setCategory(category1); // set Object category

        product.setImageUrl(image);

        return product;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }

    public String getCategory() {
        return this.category;
    }

    public String getImage() {
        return this.image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

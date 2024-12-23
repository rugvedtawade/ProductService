package com.scaler.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Product extends BaseModel {
    private String title;
    private String description;
    private double price;
    @ManyToOne(cascade = {CascadeType.PERSIST}) // CascadeType "Persist" - to tell that don't delete category id when product is deleted
    private Category category;
    private String imageUrl;
    //P:O
    //@OneToOne
    //private Order order;

    public Product(long id, String title, String description, double price, Category category, String imageurl) {

        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public Product() {
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

    public Category getCategory() {
        return this.category;
    }

    public String getImageUrl() {
        return this.imageUrl;
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

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }




}
/*
Product Category
Relation = Cardinality

Product Category
1       1
M        1
P : C
M : 1

are you defining a fk constraint?
Category_id = act like a foregin key in the product table
fk constraints
when you delete a product, what do you want to do with that category?

1 1

In your product table, will you have a category id column?

Order
@Entity

@
List<Product>
 */
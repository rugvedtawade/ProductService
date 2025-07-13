package com.scaler.productservice.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.Date;
import java.util.List;


@Entity
public class Category extends BaseModel{
    private String title;

    //duplicate relation(already mentioned in product  - mappedBy = "category")
    @OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE})   // Cascade type is Foreign key constraint
    @JsonIgnore
    private List<Product> products; //electronics

    public Category(String title, List<Product> products) {
        this.title = title;
        this.products = products;
    }

    public Category() {
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}


/*
Some common attributes

id,
createdAt,
lastModified
 */
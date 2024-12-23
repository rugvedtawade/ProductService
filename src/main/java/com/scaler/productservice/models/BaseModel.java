package com.scaler.productservice.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;

@MappedSuperclass   // tells that it is parent of other model classes
public class BaseModel {
    @Id // to tell that "id" field is Primary Key
    @GeneratedValue(strategy = GenerationType.AUTO) // to autogenarate id
    private long id;

    private Date createdAt;
    private  Date lastModifiedAt;
    private boolean isDeleted;

    public BaseModel(long id, Date createdAt, Date lastModifiedAt, boolean isDeleted) {
        this.id = id;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
        this.isDeleted = isDeleted;
    }

    public BaseModel()
    {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(Date lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
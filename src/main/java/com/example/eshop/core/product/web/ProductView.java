package com.example.eshop.core.product.web;

import com.example.eshop.core.category.web.CategoryView;
import com.example.eshop.core.company.web.CompanyView;

import java.security.Timestamp;

public class ProductView {

    private Long id;
    private String name;
    private Double price;
    private String pictureUrl;
    //private Timestamp creationDate;
    private String description;
    private CategoryView category;
    private CompanyView company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
/*
    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
*/
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CategoryView getCategory() {
        return category;
    }

    public void setCategory(CategoryView category) {
        this.category = category;
    }

    public CompanyView getCompany() {
        return company;
    }

    public void setCompany(CompanyView company) {
        this.company = company;
    }
}

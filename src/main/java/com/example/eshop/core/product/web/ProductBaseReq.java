package com.example.eshop.core.product.web;

import com.example.eshop.base.BaseRequest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.security.Timestamp;

public class ProductBaseReq extends BaseRequest {
    @NotEmpty
    private String name;
    @NotEmpty
    private Double price;
    private String pictureUrl;
  //  private Timestamp creationDate;
    private String description;
    @NotNull
    private Long categoryId;
    @NotNull
    private Long companyId;

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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }
}

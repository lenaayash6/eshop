package com.example.eshop.core.category.web;

import com.example.eshop.base.BaseRequest;
import javax.validation.constraints.NotEmpty;

public class CategoryBaseReq extends BaseRequest {
    @NotEmpty
    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

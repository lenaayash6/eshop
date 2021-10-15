package com.example.eshop.core.company.web;

import com.example.eshop.base.BaseRequest;
import javax.validation.constraints.NotEmpty;

public class CompanyBaseReq extends BaseRequest {
    @NotEmpty
    private String name;
    private String email;
    private String address;
    private String logoUrl;
    @NotEmpty
    private String about;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}

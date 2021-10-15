package com.example.eshop.core.company.converter;

import com.example.eshop.core.company.Company;
import com.example.eshop.core.company.web.CompanyView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CompanyToCompanyViewConverter implements Converter<Company, CompanyView> {

    @Override
    public CompanyView convert(@NonNull Company company) {
        CompanyView view = new CompanyView();
        view.setId(company.getId());
        view.setName(company.getName());
        view.setAddress(company.getAddress());
        view.setAbout(company.getAbout());
        view.setLogoUrl(company.getLogoUrl());
        view.setEmail(company.getEmail());

        return view;
    }
}
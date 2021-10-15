package com.example.eshop.core.product.converter;

import com.example.eshop.core.category.Category;
import com.example.eshop.core.category.converter.CategoryToCategoryViewConverter;
import com.example.eshop.core.company.Company;
import com.example.eshop.core.company.converter.CompanyToCompanyViewConverter;
import com.example.eshop.core.product.Product;
import com.example.eshop.core.product.web.ProductView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class ProductToProductViewConverter implements Converter<Product, ProductView> {

    private final CategoryToCategoryViewConverter categoryToCategoryViewConverter;
    private final CompanyToCompanyViewConverter companyToCompanyViewConverter;

    public ProductToProductViewConverter(CategoryToCategoryViewConverter categoryToCategoryViewConverter,
                                         CompanyToCompanyViewConverter companyToCompanyViewConverter) {
        this.categoryToCategoryViewConverter = categoryToCategoryViewConverter;
        this.companyToCompanyViewConverter = companyToCompanyViewConverter;
    }

    @Override
    public ProductView convert(@NonNull Product product) {
        ProductView view = new ProductView();
        view.setId(product.getId());
        view.setName(product.getName());
        view.setPrice(product.getPrice());
        view.setDescription(product.getDescription());
       // view.setCreationDate(product.getCreationDate());
        view.setPictureUrl(product.getPictureUrl());
        Category category = product.getCategory();
        view.setCategory(categoryToCategoryViewConverter.convert(category));
        Company company = product.getCompany();
        view.setCompany(companyToCompanyViewConverter.convert(company));
        return view;
    }
}

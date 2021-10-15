package com.example.eshop.core.category.converter;

import com.example.eshop.core.category.Category;
import com.example.eshop.core.category.web.CategoryView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class CategoryToCategoryViewConverter implements Converter<Category, CategoryView> {

    @Override
    public CategoryView convert(@NonNull Category category) {

        CategoryView view = new CategoryView();
        view.setId(category.getId());
        view.setName(category.getName());
        view.setDescription(category.getDescription());
        return view;

    }
}

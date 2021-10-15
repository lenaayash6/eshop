package com.example.eshop.core.category;

import com.example.eshop.core.category.converter.CategoryToCategoryViewConverter;
import com.example.eshop.core.category.web.CategoryBaseReq;
import com.example.eshop.core.category.web.CategoryView;
import com.example.eshop.error.EntityNotFoundException;
import com.example.eshop.util.MessageUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;
    private final CategoryToCategoryViewConverter categoryToCategoryViewConverter;
    private final MessageUtil messageUtil;


    public CategoryService(CategoryRepo categoryRepo,
                           CategoryToCategoryViewConverter categoryToCategoryViewConverter,
                           MessageUtil messageUtil) {
        this.categoryRepo = categoryRepo;
        this.categoryToCategoryViewConverter = categoryToCategoryViewConverter;
        this.messageUtil = messageUtil;
    }

    public Category findCategoryOrThrow(Long id) {
        return categoryRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("category.NotFound", id)));
    }

    public CategoryView getCategory(Long id) {
        Category coach = findCategoryOrThrow(id);
        return categoryToCategoryViewConverter.convert(coach);
    }

    public CategoryView create(CategoryBaseReq req) {
        Category category = new Category();
        this.prepare(category, req);
        Category categorySave = categoryRepo.save(category);
        return categoryToCategoryViewConverter.convert(categorySave);
    }

    public Page<CategoryView> findAllCategories(Pageable pageable) {
        Page<Category> categories = categoryRepo.findAll(pageable);
        List<CategoryView> categoryViews = new ArrayList<>();
        categories.forEach(coach -> {
            CategoryView categoryView = categoryToCategoryViewConverter.convert(coach);
            categoryViews.add(categoryView);
        });
        return new PageImpl<>(categoryViews, pageable, categories.getTotalElements());
    }

    @Transactional
    public void delete(Long id) {
        try {
            categoryRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("category.NotFound", id));
        }
    }

    public CategoryView update(Category category, CategoryBaseReq req) {
        Category newCategory = this.prepare(category, req);
        Category categoryForSave = categoryRepo.save(newCategory);
        return categoryToCategoryViewConverter.convert(categoryForSave);
    }

    private Category prepare(Category category, CategoryBaseReq req) {
        category.setName(req.getName());
        category.setDescription(req.getDescription());
        return category;
    }
}

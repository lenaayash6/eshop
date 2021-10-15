package com.example.eshop.core.product;

import com.example.eshop.core.category.CategoryRepo;
import com.example.eshop.core.company.CompanyRepo;
import com.example.eshop.core.product.converter.ProductToProductViewConverter;
import com.example.eshop.core.product.web.ProductBaseReq;
import com.example.eshop.core.product.web.ProductView;
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
public class ProductService {

    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final CompanyRepo companyRepo;
    private final ProductToProductViewConverter productToProductViewConverter;
    private final MessageUtil messageUtil;

    public ProductService(ProductRepo productRepo,
                          CategoryRepo categoryRepo, CompanyRepo companyRepo, ProductToProductViewConverter productToProductViewConverter,
                          MessageUtil messageUtil) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.companyRepo = companyRepo;
        this.productToProductViewConverter = productToProductViewConverter;
        this.messageUtil = messageUtil;
    }

    public Product findProductOrThrow(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("product.NotFound", id)));
    }

    public ProductView getProduct(Long id) {
        Product product = findProductOrThrow(id);
        return productToProductViewConverter.convert(product);
    }

    public ProductView create(ProductBaseReq req) {
        Product product = new Product();
        this.prepare(product, req);
        Product productSave = productRepo.save(product);
        return productToProductViewConverter.convert(productSave);
    }

    public Page<ProductView> findAllProducts(Pageable pageable) {
        Page<Product> products = productRepo.findAll(pageable);
        List<ProductView> productViews = new ArrayList<>();
        products.forEach(product -> {
            ProductView productView = productToProductViewConverter.convert(product);
            productViews.add(productView);
        });
        return new PageImpl<>(productViews, pageable, products.getTotalElements());
    }

    @Transactional
    public void delete(Long id) {
        try {
            productRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("product.NotFound", id));
        }
    }

    public ProductView update(Product product, ProductBaseReq req) {
        Product newProduct = this.prepare(product, req);
        Product productForSave = productRepo.save(newProduct);
        return productToProductViewConverter.convert(productForSave);
    }

    private Product prepare(Product product, ProductBaseReq req) {
        product.setName(req.getName());
        product.setDescription(req.getDescription());
       // product.setCreationDate(req.getCreationDate());
        product.setPrice(req.getPrice());
        product.setPictureUrl(req.getPictureUrl());
        product.setCategory(categoryRepo.getOne(req.getCategoryId()));

        product.setCompany(companyRepo.getOne(req.getCategoryId()));

        return product;
    }
}

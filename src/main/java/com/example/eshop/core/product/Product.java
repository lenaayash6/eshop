package com.example.eshop.core.product;

import com.example.eshop.core.category.Category;
import com.example.eshop.core.company.Company;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.security.Timestamp;

@Entity
@Table(name = "fc_product")
public class Product {
    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "fc_product_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "fc_product_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )

    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "picture_url")
    private String pictureUrl;
/*
    @Column(name = "creation_date")
    private Timestamp creationDate;
*/
    @Column(name = "description")
    private String description;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;


    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}

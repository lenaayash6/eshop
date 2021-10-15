package com.example.eshop.core.company;

import com.example.eshop.core.product.Product;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "fc_company")
public class Company {
    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "fc_company_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "fc_company_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fc_company_id_seq")

   // @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;


    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "logo_url")
    private String logoUrl;
    @Column(name = "about")
    private String about;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
    private Collection<Product> products;


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

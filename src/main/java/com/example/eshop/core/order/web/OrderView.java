package com.example.eshop.core.order.web;


import com.example.eshop.core.customer.web.CustomerView;

import java.security.Timestamp;

public class OrderView {

    private long id;
    private Double amount;
    private Timestamp creationDate;
    private int orderStatus;
    private CustomerView customer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public CustomerView getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerView customer) {
        this.customer = customer;
    }
}

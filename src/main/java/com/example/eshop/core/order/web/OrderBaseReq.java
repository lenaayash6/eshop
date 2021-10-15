package com.example.eshop.core.order.web;

import com.example.eshop.base.BaseRequest;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.security.Timestamp;

public class OrderBaseReq extends BaseRequest {
    @NotEmpty
    private Double amount;

   // private Timestamp creationDate;
    @NotEmpty
    private int orderStatus;
    @NotNull
    private Long customerId;

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

   /* public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }
*/
    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}

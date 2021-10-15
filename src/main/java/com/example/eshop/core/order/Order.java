package com.example.eshop.core.order;

import com.example.eshop.core.customer.Customer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.security.Timestamp;

@Entity
@Table(name = "fc_order")
public class Order {
    @Id
    @Column(name = "id")
   @GenericGenerator(
            name = "fc_order_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "fc_order_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fc_order_id_seq")

    //@GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(name = "amount")
    private Double amount;
/*
    @Column(name = "creation_date")
    private Timestamp creationDate;
*/
    @Column(name = "order_status")
    private int orderStatus;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

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
/*
    public Timestamp getCreationDate() {
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

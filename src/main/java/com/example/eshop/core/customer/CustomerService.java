package com.example.eshop.core.customer;


import com.example.eshop.core.customer.converter.CustomerToCustomerViewConverter;
import com.example.eshop.core.customer.web.CustomerBaseReq;
import com.example.eshop.core.customer.web.CustomerView;
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
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerToCustomerViewConverter customerToCustomerViewConverter;
    private final MessageUtil messageUtil;


    public CustomerService(CustomerRepo customerRepo,
                           CustomerToCustomerViewConverter customerToCustomerViewConverter,
                           MessageUtil messageUtil) {
        this.customerRepo = customerRepo;
        this.customerToCustomerViewConverter = customerToCustomerViewConverter;
        this.messageUtil = messageUtil;
    }

    public Customer findCustomerOrThrow(Long id) {
        return customerRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("customer.NotFound", id)));
    }

    public CustomerView getCustomer(Long id) {
        Customer customer = findCustomerOrThrow(id);
        return customerToCustomerViewConverter.convert(customer);
    }

    public CustomerView create(CustomerBaseReq req) {
        Customer customer = new Customer();
        this.prepare(customer, req);
        Customer customerSave = customerRepo.save(customer);
        return customerToCustomerViewConverter.convert(customerSave);
    }

    public Page<CustomerView> findAllCustomers(Pageable pageable) {
        Page<Customer> customers = customerRepo.findAll(pageable);
        List<CustomerView> customerViews = new ArrayList<>();
        customers.forEach(customer -> {
            CustomerView customerView = customerToCustomerViewConverter.convert(customer);
            customerViews.add(customerView);
        });
        return new PageImpl<>(customerViews, pageable, customers.getTotalElements());
    }

    @Transactional
    public void delete(Long id) {
        try {
            customerRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("customer.NotFound", id));
        }
    }

    public CustomerView update(Customer customer, CustomerBaseReq req) {
        Customer newCustomer = this.prepare(customer, req);
        Customer customerForSave = customerRepo.save(newCustomer);
        return customerToCustomerViewConverter.convert(customerForSave);
    }

    private Customer prepare(Customer customer, CustomerBaseReq req) {
        customer.setFirstName(req.getFirstName());
        customer.setLastName(req.getLastName());
        customer.setEmail(req.getEmail());
        customer.setMobile(req.getMobile());
        customer.setCity(req.getCity());
        customer.setAddress(req.getAddress());

        return customer;
    }
}

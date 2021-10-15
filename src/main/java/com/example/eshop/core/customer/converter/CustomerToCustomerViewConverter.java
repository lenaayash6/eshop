package com.example.eshop.core.customer.converter;

import com.example.eshop.core.customer.Customer;
import com.example.eshop.core.customer.web.CustomerView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerToCustomerViewConverter implements Converter<Customer, CustomerView> {


    @Override
    public CustomerView convert(Customer customer) {

        CustomerView view = new CustomerView();
        view.setId(customer.getId());
        view.setFirstName(customer.getFirstName());
        view.setLastName(customer.getLastName());
        view.setMobile(customer.getMobile());
        view.setEmail(customer.getEmail());
        view.setCity(customer.getCity());
        view.setAddress(customer.getAddress());

        return view;
    }
}

package com.example.eshop.core.order.converter;

import com.example.eshop.core.customer.Customer;
import com.example.eshop.core.customer.converter.CustomerToCustomerViewConverter;
import com.example.eshop.core.order.Order;
import com.example.eshop.core.order.web.OrderView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderToOrderViewConverter implements Converter<Order, OrderView> {
    private final CustomerToCustomerViewConverter customerToCustomerViewConverter;


    public OrderToOrderViewConverter(CustomerToCustomerViewConverter customerToCustomerViewConverter) {
        this.customerToCustomerViewConverter = customerToCustomerViewConverter;
    }

    @Override
    public OrderView convert(Order order) {
        OrderView view = new OrderView();
        view.setId(order.getId());
        //view.setCreationDate(order.getCreationDate());
        view.setAmount(order.getAmount());
        view.setOrderStatus(order.getOrderStatus());
        Customer customer = new Customer();
        view.setCustomer(customerToCustomerViewConverter.convert(customer));
        return view;
    }
}

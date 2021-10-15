package com.example.eshop.core.order;

import com.example.eshop.core.customer.CustomerRepo;
import com.example.eshop.core.order.converter.OrderToOrderViewConverter;
import com.example.eshop.core.order.web.OrderBaseReq;
import com.example.eshop.core.order.web.OrderView;
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
public class OrderService {

    private final OrderRepo orderRepo;
    private final CustomerRepo customerRepo;
    private final OrderToOrderViewConverter orderToOrderViewConverter;
    private final MessageUtil messageUtil;


    public OrderService(OrderRepo orderRepo,
                        CustomerRepo customerRepo, OrderToOrderViewConverter orderToOrderViewConverter,
                        MessageUtil messageUtil) {
        this.orderRepo = orderRepo;
        this.customerRepo = customerRepo;
        this.orderToOrderViewConverter = orderToOrderViewConverter;
        this.messageUtil = messageUtil;
    }

    public Order findOrderOrThrow(Long id) {
        return orderRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("order.NotFound", id)));
    }

    public OrderView getOrder(Long id) {
        Order order = findOrderOrThrow(id);
        return orderToOrderViewConverter.convert(order);
    }

    public OrderView create(OrderBaseReq req) {
        Order order = new Order();
        this.prepare(order, req);
        Order orderSave = orderRepo.save(order);
        return orderToOrderViewConverter.convert(orderSave);
    }

    public Page<OrderView> findAllOrders(Pageable pageable) {
        Page<Order> orders = orderRepo.findAll(pageable);
        List<OrderView> orderViews = new ArrayList<>();
        orders.forEach(order -> {
            OrderView orderView = orderToOrderViewConverter.convert(order);
            orderViews.add(orderView);
        });
        return new PageImpl<>(orderViews, pageable, orders.getTotalElements());
    }

    @Transactional
    public void delete(Long id) {
        try {
            orderRepo.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(messageUtil.getMessage("order.NotFound", id));
        }
    }

    public OrderView update(Order order, OrderBaseReq req) {
        Order newOrder = this.prepare(order, req);
        Order orderForSave = orderRepo.save(newOrder);
        return orderToOrderViewConverter.convert(orderForSave);
    }

    private Order prepare(Order order, OrderBaseReq req) {
        order.setAmount(req.getAmount());
     //   order.setCreationDate(req.getCreationDate());
        order.setOrderStatus(req.getOrderStatus());
        order.setCustomer(customerRepo.getOne(req.getCustomerId()));
        return order;
    }


}

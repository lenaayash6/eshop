package com.example.eshop.core.order.web;

import com.example.eshop.core.order.Order;
import com.example.eshop.core.order.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public OrderView getOrder(@PathVariable Long id) {
        return service.getOrder(id);
    }

    @GetMapping
    @ResponseBody
    public Page<OrderView> getAllOrders(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return service.findAllOrders(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public OrderView create(@RequestBody @Valid OrderBaseReq req) {
        return service.create(req);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public OrderView updateOrder(@PathVariable(name = "id") Long id,
                                 @RequestBody @Valid OrderBaseReq req) {
        Order order = service.findOrderOrThrow(id);
        return service.update(order, req);
    }

}

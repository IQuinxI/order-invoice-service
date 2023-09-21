package ma.dev.orderinvoiceservice.controller;


import lombok.RequiredArgsConstructor;
import ma.dev.orderinvoiceservice.dto.OrderRequest;
import ma.dev.orderinvoiceservice.model.Order;
import ma.dev.orderinvoiceservice.model.OrderLineItem;
import ma.dev.orderinvoiceservice.service.OrderLineItemService;
import ma.dev.orderinvoiceservice.service.OrderService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "order is created";
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        
        return orderService.getOrders();
    }

    @PostMapping("/orders/{id}/item")
    public Order addItemToOrder(@PathVariable("id") Long id, @RequestBody OrderLineItem orderItem) {
        return orderService.addOrderItem(id, orderItem);
    }
    
    
}

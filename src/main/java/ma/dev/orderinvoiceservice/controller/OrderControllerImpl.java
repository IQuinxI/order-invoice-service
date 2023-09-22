package ma.dev.orderinvoiceservice.controller;

import lombok.RequiredArgsConstructor;
import ma.dev.orderinvoiceservice.model.Order;
import ma.dev.orderinvoiceservice.model.OrderLineItem;
import ma.dev.orderinvoiceservice.service.OrderLineItemServiceImpl;
import ma.dev.orderinvoiceservice.service.OrderServiceImpl;


import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderControllerImpl implements OrderController {

    private final OrderServiceImpl orderService;
    private final OrderLineItemServiceImpl orderLineItemServiceImpl;

    @GetMapping
    @Override
    public CollectionModel<EntityModel<Order>> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/{id}")
    @Override
    public EntityModel<Order> getOrder(@PathVariable("id") Long id) {
        return orderService.getOrder(id);
    }

    @PostMapping
    @Override
    public ResponseEntity<?> addOrder(@RequestBody Order order) {
        return orderService.addOrder(order.getOrderLineItemsList(),
            order.getInvoiceId(),order.getClientId());
    }

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id) {
        return orderService.deleteOrder(id);
    }

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<?> replaceOrder(@PathVariable("id") Long id, @RequestBody Order newOrder) {
        return orderService.replaceOrder(id, newOrder.getOrderLineItemsList(), newOrder.getInvoiceId(), newOrder.getClientId());
    }

    @PutMapping("items/{id}")
    @Override
    public ResponseEntity<?> addOrderItem(@PathVariable("id") Long id, @RequestBody OrderLineItem orderItem) {
       return orderService.addOrderItem(id, orderItem);
    }

    @GetMapping("/items")
    public CollectionModel<EntityModel<OrderLineItem>> getItems() {
        return orderLineItemServiceImpl.getAllItems();
    }

    @GetMapping("/eager/{id}")
    public CollectionModel<EntityModel<Order>> getOrdersEager(@PathVariable("id") Long id) {
        return orderService.getOrderEager(id);
    }

    @GetMapping("/items/{id}")
    @Override
    public CollectionModel<EntityModel<OrderLineItem>> getOrderItemsByOrderId(@PathVariable("id") Long id) {
       return orderService.getOrderItemsByOrderId(id);
    }

}

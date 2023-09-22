package ma.dev.orderinvoiceservice.controller;

import lombok.RequiredArgsConstructor;
import ma.dev.orderinvoiceservice.dto.OrderRequest;
import ma.dev.orderinvoiceservice.model.Order;
import ma.dev.orderinvoiceservice.model.OrderLineItem;
import ma.dev.orderinvoiceservice.repository.OrderLineItemRepository;
import ma.dev.orderinvoiceservice.repository.OrderRepository;
import ma.dev.orderinvoiceservice.service.OrderLineItemServiceImpl;
import ma.dev.orderinvoiceservice.service.OrderServiceImpl;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderControllerImpl implements OrderController {

    private final OrderServiceImpl orderService;
    private final OrderRepository orderRepository;
    private final OrderLineItemServiceImpl orderLineItemServiceImpl;
    private final OrderLineItemRepository orderItems;

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

    @Override
    public ResponseEntity<?> addOrder(Order order) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addOrder'");
    }

    @Override
    public ResponseEntity<?> deleteOrder(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteOrder'");
    }

    @Override
    public ResponseEntity<?> replaceOrder(Long id, Order newOrder) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'replaceOrder'");
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
    // @GetMapping("/orders")
    // public List<Order> getOrders() {

    // return orderService.getOrders();
    // }

    // @PostMapping("/orders/{id}/item")
    // public Order addItemToOrder(@PathVariable("id") Long id, @RequestBody
    // OrderLineItem orderItem) {
    // return orderService.addOrderItem(id, orderItem);
    // }

}

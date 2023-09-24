package ma.dev.orderinvoiceservice.controller;

import lombok.RequiredArgsConstructor;
import ma.dev.orderinvoiceservice.model.Order;
import ma.dev.orderinvoiceservice.model.OrderLineItem;
import ma.dev.orderinvoiceservice.repository.OrderLineItemRepository;
import ma.dev.orderinvoiceservice.service.OrderLineItemServiceImpl;
import ma.dev.orderinvoiceservice.service.OrderServiceImpl;

import java.util.List;

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
    private final OrderLineItemRepository orderLineItemRepository;

    // get all orders
    @GetMapping
    @Override
    public CollectionModel<EntityModel<Order>> getOrders() {
        return orderService.getOrders();
    }

    // get an order by id
    @GetMapping("/{id}")
    @Override
    public EntityModel<Order> getOrder(@PathVariable("id") Long id) {
        return orderService.getOrder(id);
    }

    // add an order 
    @PostMapping
    @Override
    public ResponseEntity<?> addOrder(@RequestBody Order order) {
        return orderService.addOrder(order.getOrderLineItemsList(),
            order.getInvoiceId(),order.getClientId());
    }

    // delete an order
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<?> deleteOrder(@PathVariable("id") Long id) {
        return orderService.deleteOrder(id);
    }

    // replace an order
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<?> replaceOrder(@PathVariable("id") Long id, @RequestBody Order newOrder) {
        return orderService.replaceOrder(id, newOrder.getOrderLineItemsList(), newOrder.getInvoiceId(), newOrder.getClientId());
    }

    // add an order item to an already existing order
    @PutMapping("items/{id}")
    @Override
    public ResponseEntity<?> addOrderItem(@PathVariable("id") Long id, @RequestBody OrderLineItem orderItem) {
       return orderService.addOrderItem(id, orderItem);
    }

    // get all items 
    @GetMapping("/items")
    @Override
    public CollectionModel<EntityModel<OrderLineItem>> getItems() {
        return orderLineItemServiceImpl.getAllItems();
    }

    // get all items of an order
    @GetMapping("/items/{id}")
    @Override
    public CollectionModel<EntityModel<OrderLineItem>> getOrderItemsByOrderId(@PathVariable("id") Long id) {
       return orderService.getOrderItemsByOrderId(id);
    }

}

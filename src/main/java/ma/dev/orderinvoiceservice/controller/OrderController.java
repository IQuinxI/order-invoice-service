package ma.dev.orderinvoiceservice.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import ma.dev.orderinvoiceservice.model.Order;
import ma.dev.orderinvoiceservice.model.OrderLineItem;

/**
 * OrderController
 */
public interface OrderController {

    public CollectionModel<EntityModel<Order>> getOrders();

    public EntityModel<Order> getOrder(Long id);

    public ResponseEntity<?> addOrder(Order order);

    public ResponseEntity<?> deleteOrder(Long id);

    public ResponseEntity<?> replaceOrder(Long id, Order newOrder);

    public ResponseEntity<?> addOrderItem(Long id, OrderLineItem orderItem);

    public CollectionModel<EntityModel<OrderLineItem>> getOrderItemsByOrderId(Long id);

    public CollectionModel<EntityModel<OrderLineItem>> getItems();


}
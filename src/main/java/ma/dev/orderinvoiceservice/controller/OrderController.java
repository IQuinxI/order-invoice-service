package ma.dev.orderinvoiceservice.controller;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import ma.dev.orderinvoiceservice.model.Invoice;
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
}
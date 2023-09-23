package ma.dev.orderinvoiceservice.service;

import java.util.List;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;

import ma.dev.orderinvoiceservice.model.Invoice;
import ma.dev.orderinvoiceservice.model.Order;
import ma.dev.orderinvoiceservice.model.OrderLineItem;

/**
 * OrderService
 */
public interface OrderService {
    public CollectionModel<EntityModel<Order>> getOrders();

    public EntityModel<Order> getOrder(Long id);

    public ResponseEntity<?> addOrder(List<OrderLineItem> orderLineItems, Invoice invoice, Long clientId);

    public ResponseEntity<?> deleteOrder(Long id);

    public ResponseEntity<?> replaceOrder(Long id, List<OrderLineItem> orderLineItems, Invoice invoice, Long clientId);

    public ResponseEntity<?> addOrderItem(Long id, OrderLineItem orderItem);

    public CollectionModel<EntityModel<OrderLineItem>> getOrderItemsByOrderId(Long id);


}
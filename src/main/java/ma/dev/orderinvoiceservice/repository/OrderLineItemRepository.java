package ma.dev.orderinvoiceservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.dev.orderinvoiceservice.model.Order;
import ma.dev.orderinvoiceservice.model.OrderLineItem;

public interface OrderLineItemRepository extends JpaRepository<OrderLineItem, Long> {
    // List<OrderLineItem> findByOrderId(Long id);
    // public List<OrderLineItem> findBy
    List<OrderLineItem> findByOrder(Order order);
}

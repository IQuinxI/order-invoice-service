package ma.dev.orderinvoiceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.dev.orderinvoiceservice.model.OrderLineItem;

public interface OrderLineItemRepository extends JpaRepository<OrderLineItem, Long> {
    
}

package ma.dev.orderinvoiceservice.repository;

import ma.dev.orderinvoiceservice.model.Order;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface OrderRepository extends JpaRepository<Order,Long> {
   @EntityGraph(value = "Order.orderLineItemsList") 
   List<Order> findByClientId(Long id);
   
}

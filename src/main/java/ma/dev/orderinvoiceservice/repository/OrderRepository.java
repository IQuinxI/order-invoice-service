package ma.dev.orderinvoiceservice.repository;

import ma.dev.orderinvoiceservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}

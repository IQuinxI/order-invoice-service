package ma.dev.orderinvoiceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;
import ma.dev.orderinvoiceservice.model.InvoiceItems;

@Transactional
public interface InvoiceItemsRepository extends JpaRepository<InvoiceItems, Long>{
    
}

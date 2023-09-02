package ma.dev.orderinvoiceservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ma.dev.orderinvoiceservice.model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>{
    
}

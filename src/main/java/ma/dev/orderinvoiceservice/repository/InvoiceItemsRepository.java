package ma.dev.orderinvoiceservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;
import ma.dev.orderinvoiceservice.model.InvoiceItems;

@Transactional
public interface InvoiceItemsRepository extends JpaRepository<InvoiceItems, Long>{
    List<InvoiceItems> findByInvoiceId(Long invoiceId);
}

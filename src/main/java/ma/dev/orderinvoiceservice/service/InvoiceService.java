package ma.dev.orderinvoiceservice.service;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import ma.dev.orderinvoiceservice.model.Invoice;

public interface InvoiceService {
    public CollectionModel<EntityModel<Invoice>> getInvoices();

    public ResponseEntity<?> addInvoice(LocalDateTime dueDateTime);

    public ResponseEntity<?> deleteInvoice(Long id);

    public ResponseEntity<?> replaceInvoice(Long id, Invoice invoice);

    public EntityModel<Invoice> getInvoice(Long id);
}

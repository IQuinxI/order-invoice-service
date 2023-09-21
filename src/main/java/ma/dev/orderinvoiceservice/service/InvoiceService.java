package ma.dev.orderinvoiceservice.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ma.dev.orderinvoiceservice.model.Invoice;
import ma.dev.orderinvoiceservice.repository.InvoiceRepository;

@Transactional
@RequiredArgsConstructor
@Service
public class InvoiceService {
    private final InvoiceRepository invoiceRepository;

    public Invoice addInvoice(LocalDateTime dueDateTime) {

        Invoice invoice =  Invoice.builder()
                .creationDate(LocalDateTime.now())
                .dueDate(dueDateTime)
                .build();

        return invoiceRepository.save(invoice);
    }
}

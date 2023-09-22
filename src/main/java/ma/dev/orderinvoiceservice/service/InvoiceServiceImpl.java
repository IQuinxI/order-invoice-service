package ma.dev.orderinvoiceservice.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import ma.dev.orderinvoiceservice.assemblers.InvoiceAssembler;
import ma.dev.orderinvoiceservice.controller.InvoiceController;
import ma.dev.orderinvoiceservice.exceptions.InvoiceNotFoundException;
import ma.dev.orderinvoiceservice.model.Invoice;
import ma.dev.orderinvoiceservice.repository.InvoiceRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Transactional
@RequiredArgsConstructor
@Service
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceAssembler assembler;

    @Override
    public CollectionModel<EntityModel<Invoice>> getInvoices() {
        List<EntityModel<Invoice>> invoices = invoiceRepository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        
        return CollectionModel.of(invoices, 
                linkTo(methodOn(InvoiceController.class).getInvoices()).withSelfRel()
                );
    }

    @Override
    public ResponseEntity<?> deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<?> replaceInvoice(Long id, Invoice invoice) {
        Invoice updatedInvoice = invoiceRepository.findById(id)
                .map(inv -> {
                    inv.setDueDate(invoice.getDueDate());
                    inv.setTotalAmount(invoice.getTotalAmount());

                    return invoiceRepository.save(inv);
                })
                .orElseGet(() -> {
                    invoice.setInvoiceId(id);
                    return invoiceRepository.save(invoice);
                });
        
        EntityModel<Invoice> entityModel = assembler.toModel(updatedInvoice);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @Override
    public EntityModel<Invoice> getInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id)
            .orElseThrow(() -> new InvoiceNotFoundException(id)); 
        return assembler.toModel(invoice);
    }

    @Override
    public ResponseEntity<?> addInvoice(LocalDateTime dueDateTime) {
        Invoice invoice =  Invoice.builder()
                .creationDate(LocalDateTime.now())
                .dueDate(dueDateTime)
                .build();
        
        EntityModel<Invoice> entityModel = assembler.toModel(invoiceRepository.save(invoice));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
}

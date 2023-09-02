package ma.dev.orderinvoiceservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ma.dev.orderinvoiceservice.controller.clients.CustomerServiceClient;
import ma.dev.orderinvoiceservice.model.Invoice;
import ma.dev.orderinvoiceservice.repository.InvoiceItemsRepository;
import ma.dev.orderinvoiceservice.repository.InvoiceRepository;

@RestController
public class InvoiceController {
    @Autowired
    private InvoiceRepository invoiceRespository;

    // @Autowired
    // private InvoiceItemsRepository invoiceItemsRepository;

    @Autowired
    private CustomerServiceClient customerServiceClient;

    @GetMapping("/invoices/{id}")
    Invoice getInvoice(@PathVariable(name = "id") Long id) {
        Invoice invoice = invoiceRespository.findById(id).get();
        Long clientId = invoice.getClientId();

        invoice.setClient(customerServiceClient.findClientById(clientId));
        
        return invoice;
    }
}

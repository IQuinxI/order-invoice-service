package ma.dev.orderinvoiceservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ma.dev.orderinvoiceservice.controller.clients.CustomerServiceClient;
import ma.dev.orderinvoiceservice.controller.clients.ProductServiceClient;
import ma.dev.orderinvoiceservice.model.Invoice;
import ma.dev.orderinvoiceservice.repository.InvoiceItemsRepository;
import ma.dev.orderinvoiceservice.repository.InvoiceRepository;
import ma.dev.orderinvoiceservice.service.InvoiceService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/invoices")
public class InvoiceController {

    // @Autowired
    // private InvoiceRepository invoiceRespository;

    // @Autowired
    // private ProductServiceClient productServiceClient;

    // @Autowired
    // private InvoiceItemsRepository invoiceItemsRepository;

    // @Autowired
    // private CustomerServiceClient customerServiceClient;

    private final InvoiceService invoiceService;
    @GetMapping("/{id}")
    public EntityModel<Invoice> getInvoice(@PathVariable(name = "id") Long id) {
        // Invoice invoice = invoiceRespository.findById(id).get();
        
        // // get Client from clients-service
        // // and load it into the response
        // Long clientId = invoice.getClientId();
        // invoice.setClient(customerServiceClient.findClientById(clientId));
        
        // // Load all invoiceItems into invoice
        // invoice.setInvoiceItems(invoiceItemsRepository.findByInvoiceId(id));


        // // get invoiceItems from products-service
        // // and loads them into the response
        // invoice.getInvoiceItems().forEach(invoiceItem -> {
        //     Long productId = invoiceItem.getProductId();
        //     invoiceItem.setProduct(productServiceClient.findProductById(productId));
        // });
        
        return invoiceService.getInvoice(id);
    }

    @GetMapping
    public CollectionModel<EntityModel<Invoice>> getInvoices() {
        return invoiceService.getInvoices();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInvoices(@PathVariable("id") Long id) {
        return invoiceService.deleteInvoice(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> replaceInvoice(@PathVariable("id") Long id,  
            @RequestBody Invoice newInvoice) {
        return invoiceService.replaceInvoice(id, newInvoice);
    }

   @PostMapping()
   public ResponseEntity<?> addInvoice(@RequestBody Invoice invoice) {
        return invoiceService.addInvoice(invoice.getDueDate());
   }


}

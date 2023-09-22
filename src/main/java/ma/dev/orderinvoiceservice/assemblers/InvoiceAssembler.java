package ma.dev.orderinvoiceservice.assemblers;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import ma.dev.orderinvoiceservice.controller.InvoiceController;
import ma.dev.orderinvoiceservice.model.Invoice;

@Component
public class InvoiceAssembler implements RepresentationModelAssembler<Invoice, EntityModel<Invoice>>{

    @Override
    public EntityModel<Invoice> toModel(Invoice invoice) {
        return EntityModel.of(invoice, 
            linkTo(methodOn(InvoiceController.class).getInvoice(invoice.getInvoiceId())).withSelfRel(),
            linkTo(methodOn(InvoiceController.class).getInvoices()).withRel("Invoices")
            );
    }
    
}

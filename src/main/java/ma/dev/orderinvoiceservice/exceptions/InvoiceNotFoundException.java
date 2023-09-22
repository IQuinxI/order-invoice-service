package ma.dev.orderinvoiceservice.exceptions;

/**
 * InvoiceNotFoundException
 */
public class InvoiceNotFoundException extends RuntimeException{
    public InvoiceNotFoundException(Long id) {
        super("Could not find the invoice with the id " + id);
    }
    
}
package ma.dev.orderinvoiceservice.exceptions;

/**
 * OrderNotFoundException
 */
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("Could not find the order with the id " + id);
    }

}
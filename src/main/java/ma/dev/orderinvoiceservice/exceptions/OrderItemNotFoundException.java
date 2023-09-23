package ma.dev.orderinvoiceservice.exceptions;

/**
 * OrderItemNotFoundException
 */
public class OrderItemNotFoundException extends RuntimeException{
    public OrderItemNotFoundException(Long id) {
        super("can not find the order item with the id: " + id);
    }
    
}
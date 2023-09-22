package ma.dev.orderinvoiceservice.exceptions;

/**
 * RequestNotValidException
 */
public class RequestNotValidException extends RuntimeException{
    
    public RequestNotValidException() {
        super("This request is not valid");
    }
}
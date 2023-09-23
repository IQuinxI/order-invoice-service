package ma.dev.orderinvoiceservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * OrderItemNotFoundAdvice
 */
@ControllerAdvice
public class OrderItemNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(OrderItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String OrderItemNotFoundHandler(OrderItemNotFoundException ex) {
        return ex.getMessage();
    }
    
}
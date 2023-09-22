package ma.dev.orderinvoiceservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * InvoiceNotFoundAdvice
 */
@ControllerAdvice
public class InvoiceNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(InvoiceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String InvoiceNotFoundHandler(InvoiceNotFoundException ex) {
        return ex.getMessage();
    }
    
    
}
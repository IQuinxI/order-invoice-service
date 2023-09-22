package ma.dev.orderinvoiceservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * RequestNotValidAdvice
 */
@ControllerAdvice
public class RequestNotValidAdvice {
    @ResponseBody
    @ExceptionHandler(RequestNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String RequestNotValidHandler(RequestNotValidException ex) {
        return ex.getMessage();
    }
    
}
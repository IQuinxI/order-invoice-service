package ma.dev.orderinvoiceservice.exceptions;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.CharSet;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;

import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import jakarta.ws.rs.BadRequestException;

/**
 * FeignClientErrorDecoder
 */
public class FeignClientErrorDecoder implements ErrorDecoder {
    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        // ExceptionMessage message = null;

        // try (InputStream bodyIs = response.body()
        //         .asInputStream()) {
        //     ObjectMapper mapper = new ObjectMapper();
        //     message = mapper.readValue(bodyIs, ExceptionMessage.class);
        // } catch (IOException e) {
        //     return new Exception(e.getMessage());
        // }

        // System.out.println(message == null? "null" : message);

        switch (response.status()) {
            case 400:
                System.out.println("Status code " + response.status() + ", methodKey = " + methodKey);
            case 404: {
                
                System.out.println("Error took place when using Feign client to send HTTP Request. Status code "
                        + response.status() + ", methodKey = " + methodKey);
                return new ResponseStatusException(HttpStatus.valueOf(response.status()),
                        "Could not find the Client or Product");
            }
            default:
                return new Exception(response.reason());
        }
    }
}

package ma.dev.orderinvoiceservice.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * FeignClientErrorDecoder
 */
public class FeignClientErrorDecoder implements ErrorDecoder {
    private ErrorDecoder errorDecoder = new Default();
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
            case 503: {
                return new ResponseStatusException(HttpStatus.valueOf(response.status()),
                        "The Product or Client instance are not online");
            }
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }
}

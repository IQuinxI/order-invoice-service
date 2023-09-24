package ma.dev.orderinvoiceservice.controller.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// import ma.dev.orderinvoiceservice.conf.JwtTokenInterceptor;
import ma.dev.orderinvoiceservice.model.Client;

// Should be used when security is enabled 
// @FeignClient(name = "client-service", configuration = JwtTokenInterceptor.class)
@FeignClient(name = "client-service")
public interface CustomerServiceClient {
    @GetMapping("/clients/{id}")
    Client findClientById(@PathVariable("id") Long id);
}

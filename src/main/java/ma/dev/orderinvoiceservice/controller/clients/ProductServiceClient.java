package ma.dev.orderinvoiceservice.controller.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ma.dev.orderinvoiceservice.model.Product;

@FeignClient(name = "product-service")
public interface ProductServiceClient {
    @GetMapping("/products/{id}")
    Product findProductById(@PathVariable("id") Long id);
}

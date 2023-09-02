package ma.dev.orderinvoiceservice.controller;


import lombok.RequiredArgsConstructor;
import ma.dev.orderinvoiceservice.dto.OrderRequest;
import ma.dev.orderinvoiceservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "order is created";
    }
}

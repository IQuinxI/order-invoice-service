// package ma.dev.orderinvoiceservice.utils;

// import java.util.Date;

// import org.springframework.boot.CommandLineRunner;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import ma.dev.orderinvoiceservice.model.Invoice;
// import ma.dev.orderinvoiceservice.repository.InvoiceRepository;
// // This class will load test data when launching the program
// @Configuration
// public class LoadDatabase {

//     @Bean
//     CommandLineRunner load(InvoiceRepository invoiceRespository) {
//         return args -> {
//             invoiceRespository.save(new Invoice(null, 1l, new Date(), 100, null, null));
//             invoiceRespository.save(new Invoice(null, 2l, new Date(), 250, null, null));

//             invoiceRespository.findAll().forEach(System.out::println);
//         };
//     }
// }
package ma.dev.orderinvoiceservice.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ma.dev.orderinvoiceservice.model.Invoice;
import ma.dev.orderinvoiceservice.model.OrderLineItem;
import ma.dev.orderinvoiceservice.repository.OrderRepository;
import ma.dev.orderinvoiceservice.service.InvoiceService;
import ma.dev.orderinvoiceservice.service.OrderLineItemService;
import ma.dev.orderinvoiceservice.service.OrderService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadDatabase {
    @Bean
    CommandLineRunner load(InvoiceService invoiceService, OrderService orderService, 
        OrderLineItemService orderLineItemService, OrderRepository orderRepository) {
        return args -> {

            System.out.println(invoiceService.addInvoice(LocalDateTime.now().plusDays(10))); 

            System.out.println(orderService.addOrder(new ArrayList<>(), 1L,null));

            // System.out.println(orderService.findOrder(1L));
            // System.out.println(orderLineItemService.addOrderItem(orderRepository.findById(1L).get(), 1L,
            //      120,BigDecimal.valueOf(120.50)));
            // orderLineItemService.addOrderItem(null, null, 0, null)

            // order.getOrderLineItemsList().add(orderLineItem);

            // orderLineItemRepository.save(orderLineItem);
            // // orderRepository.save(order);
            // orderLineItemRepository.findAll().forEach(System.out::println);
            // orderRepository.findAll().forEach(System.out::println);
        };
    }
}

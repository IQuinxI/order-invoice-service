
package ma.dev.orderinvoiceservice.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ma.dev.orderinvoiceservice.repository.OrderRepository;
import ma.dev.orderinvoiceservice.service.InvoiceServiceImpl;
import ma.dev.orderinvoiceservice.service.OrderLineItemServiceImpl;
import ma.dev.orderinvoiceservice.service.OrderServiceImpl;

import java.time.LocalDateTime;


@Configuration
public class LoadDatabase {
    @Bean
    CommandLineRunner load(InvoiceServiceImpl invoiceService, OrderServiceImpl orderService, 
        OrderLineItemServiceImpl orderLineItemService, OrderRepository orderRepository) {
        return args -> {

            System.out.println(invoiceService.addInvoice(LocalDateTime.now().plusDays(10))); 

            // System.out.println(orderService.addOrder(new ArrayList<>(), null, 1L));

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

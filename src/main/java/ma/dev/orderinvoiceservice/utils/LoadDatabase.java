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
import ma.dev.orderinvoiceservice.model.InvoiceItems;
import ma.dev.orderinvoiceservice.repository.InvoiceItemsRepository;
import ma.dev.orderinvoiceservice.repository.InvoiceRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Configuration
public class LoadDatabase {
    @Bean
    CommandLineRunner load(InvoiceRepository invoiceRepository, InvoiceItemsRepository invoiceItemsRepository) {
        return args -> {
            Invoice invoice = new Invoice(null, new Date(), 100, 1l, new ArrayList<>(), null);
            InvoiceItems invoiceItems = new InvoiceItems(null, 1l, 1l,
                    200l, 3d, null, null);

            invoice.setInvoiceItems(new ArrayList<InvoiceItems>(List.of(invoiceItems)));

            invoiceRepository.save(invoice);

            invoiceItemsRepository.save(invoiceItems);

        };
    }
}

package ma.dev.orderinvoiceservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// import ma.dev.orderinvoiceservice.security.RsaKeyConfig;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
// @EnableConfigurationProperties(RsaKeyConfig.class)
public class OrderInvoiceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderInvoiceServiceApplication.class, args);
	}

}

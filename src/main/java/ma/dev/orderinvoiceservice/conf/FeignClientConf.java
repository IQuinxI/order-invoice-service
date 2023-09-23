package ma.dev.orderinvoiceservice.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.ErrorDecoder;
import ma.dev.orderinvoiceservice.exceptions.FeignClientErrorDecoder;

/**
 * FeignClientConf
 */
@Configuration
public class FeignClientConf {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignClientErrorDecoder();
    }
}
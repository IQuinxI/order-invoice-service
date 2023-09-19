package ma.dev.orderinvoiceservice.conf;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class JwtTokenInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        String jwtToken = "";

        requestTemplate.header("Authorization", "Bearer " + jwtToken);
    }
    
}

package ma.dev.orderinvoiceservice.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class JwtTokenInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated()) {
            Jwt jwtToken =(Jwt) authentication.getPrincipal();
            String tokenValue = jwtToken.getTokenValue();

            requestTemplate.header("Authorization", "Bearer " + tokenValue);
        }

    }

}

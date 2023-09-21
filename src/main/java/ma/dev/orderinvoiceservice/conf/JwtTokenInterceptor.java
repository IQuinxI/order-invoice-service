package ma.dev.orderinvoiceservice.conf;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class JwtTokenInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // retrieves the passed jwt token passed through the request
        if (authentication != null && authentication.isAuthenticated()) {
            Jwt jwtToken =(Jwt) authentication.getPrincipal();
            String tokenValue = jwtToken.getTokenValue();
            // passes the jwt to the feignClient when making the request
            requestTemplate.header("Authorization", "Bearer " + tokenValue);
        }

    }

}

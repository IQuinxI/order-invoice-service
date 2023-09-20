package ma.dev.orderinvoiceservice.security;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * RsaKeyConfig
 */
@ConfigurationProperties(prefix = "rsa")
public record RsaKeyConfig(RSAPublicKey publicKey, RSAPrivateKey privateKey) {

    
}
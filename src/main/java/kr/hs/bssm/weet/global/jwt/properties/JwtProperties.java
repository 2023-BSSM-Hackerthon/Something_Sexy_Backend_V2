package kr.hs.bssm.weet.global.jwt.properties;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

import javax.crypto.SecretKey;

@ConfigurationProperties(prefix = "jwt")
public record JwtProperties(
        SecretKey secret,
        String prefix,
        Long accessTokenExp,
        Long refreshTokenExp
) {

    @ConstructorBinding
    public JwtProperties(String secret, String prefix, Long accessTokenExp, Long refreshTokenExp) {
        this(
                Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret)),
                prefix,
                accessTokenExp,
                refreshTokenExp
        );
    }
}

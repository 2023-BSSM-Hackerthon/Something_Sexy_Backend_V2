package kr.hs.bssm.weet.global.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kr.hs.bssm.weet.domain.user.Authority;
import kr.hs.bssm.weet.global.jwt.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

import static kr.hs.bssm.weet.global.jwt.util.JwtConstant.*;

@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;

    public String accessToken(String email, Authority authority) {
        return createToken(email, authority, jwtProperties.accessTokenExp(), ACCESS_TOKEN.getValue());
    }

    public String refreshToken(String email, Authority authority) {
        return createToken(email, authority, jwtProperties.refreshTokenExp(), REFRESH_TOKEN.getValue());
    }

    private String createToken(String email, Authority authority, Long exp, String type) {
        Date now = new Date();

        Claims claims = Jwts.claims();
        claims.put("email", email);
        claims.put("authority", authority);

        return Jwts.builder()
                .setHeaderParam(TYPE.getValue(), type)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + exp))
                .signWith(jwtProperties.secret(), SignatureAlgorithm.HS256)
                .compact();
    }
}

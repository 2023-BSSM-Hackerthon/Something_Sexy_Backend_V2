package kr.hs.bssm.weet.global.jwt.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import kr.hs.bssm.weet.domain.user.Authority;
import kr.hs.bssm.weet.global.jwt.properties.JwtProperties;
import kr.hs.bssm.weet.global.jwt.util.dto.TokenInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtUtil {

    private final JwtProperties jwtProperties;

    public String resolveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.split(" ")[1].trim();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtProperties.secret())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public TokenInfo extractTokenInfo(String token) {
        Claims claims = getClaims(token);

        return new TokenInfo(
                claims.get("email").toString(),
                Authority.valueOf(claims.get("authority").toString())
        );
    }

    public String getTokenType(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(jwtProperties.secret())
                .build()
                .parseClaimsJws(token)
                .getHeader()
                .get("type")
                .toString();
    }

    public boolean isExpired(String token) {
        return getClaims(token)
                .getExpiration()
                .before(new Date());
    }
}

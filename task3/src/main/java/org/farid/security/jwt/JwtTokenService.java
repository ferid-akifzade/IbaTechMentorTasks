package org.farid.security.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class JwtTokenService {
    private final String secret = "SGVsbG9Xb3JsZA==";

    public String generateToken(Long userId, boolean rememberMe) {
        final Date date = new java.util.Date();
        long expiration_remember = 6048000000L;
        long expiration_normal = 864000000L;
        final Date expiry = new Date(date.getTime() + (rememberMe ? expiration_remember : expiration_normal));
        return Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(date)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Optional<Jws<Claims>> tokenToClaims(String token) {
        try {
            return Optional.of(Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
            );
        } catch (SignatureException ex) {
            log.error("JWT: Invalid signature");
        } catch (MalformedJwtException ex) {
            log.error("JWT: Invalid token");
        } catch (ExpiredJwtException ex) {
            log.error("JWT: Expired token");
        } catch (UnsupportedJwtException ex) {
            log.error("JWT: Unsupported token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT: token is empty.");
        }
        return Optional.empty();
    }

    public Long getIdFromClaims(Jws<Claims> claimsJws) {
        return Long.parseLong(claimsJws.getBody().getSubject());
    }

}

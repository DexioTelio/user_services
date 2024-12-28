package com.ecommerce.demo.jwt;

import com.ecommerce.demo.model.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtTokenServices {
    @Value("${JWT_SECRET_KEY}")
    private final String SECRET_KEY;
    @Value("${jwt.expiration-time}")
    private long EXPIRATION_TIME;

    public JwtTokenServices(@Value("${JWT_SECRET_KEY}") String secretKey) {
        if (secretKey == null || secretKey.isEmpty()) {
            throw new IllegalArgumentException("The JWT secret key is not configured.");
        }
        this.SECRET_KEY = secretKey;
    }

    public String generateToken(Authentication authentication) {
        Map<String, Object> claims = new HashMap<>();

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            claims.put("roles", userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList());
            claims.put("username", userDetails.getUsername());
        }

        if (principal instanceof CustomUserDetails customUserDetails) {
            claims.put("email", customUserDetails.getEmail());
        }

        Date now = new Date(System.currentTimeMillis());
        Date expiration = new Date(System.currentTimeMillis() + EXPIRATION_TIME);


        return Jwts.builder()
                .claims(claims)
                .subject(authentication.getName())
                .issuer("http://eco.local")
                .issuedAt(now)
                .expiration(expiration)
                .signWith(getSignInKey())
                .audience()
                .and().compact();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private SecretKey getSignInKey() {
        byte[] bytes = Base64.getDecoder()
                .decode(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return new SecretKeySpec(bytes, "HmacSHA256");
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token).
                getPayload();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsTResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsTResolver.apply(claims);
    }
}

package com.library.common.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import javax.crypto.SecretKey;
import lombok.extern.slf4j.Slf4j;



@Slf4j
public class JwtTokenProvider {
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer";
    private static int REFRESH_TOKEN_EXPIRE_TIME_COOKIE;

    private final SecretKey key;

    public JwtTokenProvider(String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractIdFromToken(String Token) {
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(Token)
                .getPayload();

        return claims.getSubject();
    }

//    public TokenInfo generateToken(String subject, String claim, long ACCESS_TOKEN_EXPIRE_TIME, long REFRESH_TOKEN_EXPIRE_TIME) {
//        long now = (new Date()).getTime();
//        String accessToken = Jwts.builder()
//                .subject(subject)
//                .claim("claim", claim)
//                .expiration(new Date(now + ACCESS_TOKEN_EXPIRE_TIME))
//                .issuedAt(new Date(now))
//                .signWith(key)
//                .compact();
//
//        String refreshToken = Jwts.builder()
//                .subject(subject)
//                .expiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
//                .signWith(key)
//                .compact();
//
//        return TokenInfo.builder()
//                .grantType(BEARER_TYPE)
//                .accessToken(accessToken)
//                .refreshToken(refreshToken)
//                .refreshTokenExpirationTime(REFRESH_TOKEN_EXPIRE_TIME)
//                .build();
//    }

    public TokenInfo generateToken(String id, String role, long ACCESS_TOKEN_EXPIRE_TIME, long REFRESH_TOKEN_EXPIRE_TIME) {
        long now = (new Date()).getTime();
        String accessToken = Jwts.builder()
                .subject(id)
                .claim(AUTHORITIES_KEY, role)
                .expiration(new Date(now + ACCESS_TOKEN_EXPIRE_TIME))
                .issuedAt(new Date(now))
                .signWith(key)
                .compact();

        String refreshToken = Jwts.builder()
                .subject(id)
                .expiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key)
                .compact();

//        log.info("REFRESH_TOKEN_EXPIRE_TIME = {}", REFRESH_TOKEN_EXPIRE_TIME);
        return TokenInfo.builder()
                .grantType(BEARER_TYPE)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .refreshTokenExpirationTime(REFRESH_TOKEN_EXPIRE_TIME)
                .build();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            throw new MalformedJwtException("Invalid JWT Token", e);
        }
//        } catch (ExpiredJwtException e) {
//            throw new ExpiredJwtException("Expired JWT Token", e);
//            log.info(, e);
//        } catch (UnsupportedJwtException e) {
//            log.info("Unsupported JWT Token", e);
//        } catch (IllegalArgumentException e) {
//            log.info("JWT claims string is empty.", e);
//        }
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(accessToken)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public Long getExpiration(String accessToken) {
        Date expiration = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(accessToken)
                .getPayload()
                .getExpiration();
        long now = new Date().getTime();
        return (expiration.getTime() - now);
    }

//    @Value("${jwt.refresh-token-expiration-time-cookie}")
//    public void setRefreshTokenExpireTimeCookie(int expireTime) {
//        REFRESH_TOKEN_EXPIRE_TIME_COOKIE = expireTime;
//    }

    public static int getRefreshTokenExpireTimeCookie() {
        return REFRESH_TOKEN_EXPIRE_TIME_COOKIE;
    }
}


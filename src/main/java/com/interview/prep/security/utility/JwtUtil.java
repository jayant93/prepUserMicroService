package com.interview.prep.security.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "testjwt";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; //hour

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    //generate Token
    public String generateToken(String username){
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    //token validation
    public boolean validateToken(String token,String username){
        String extractedUserName = extractUserName(token);
        return extractedUserName.equals(username) && !isTokenExpired(token);
    }

    //extract username
    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    //extract expiration
    private Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    //extract claims
    private <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    //extract all claims
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }

    //is token expired
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
}

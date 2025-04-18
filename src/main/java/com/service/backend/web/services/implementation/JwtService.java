package com.service.backend.web.services.implementation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${secret_key}")
    private String secretKey ;
    /*private byte[] secretKey ;
    public JwtService() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        this.secretKey  = keyGen.generateKey().getEncoded();
    }*/

    public String generateToken(String username) throws NoSuchAlgorithmException {
        Map<String,Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + 1000*60*60))
                .issuedAt(new Date())
                .and()
                .signWith(getKey())
                .compact() ;

    }

    public SecretKey getKey() throws NoSuchAlgorithmException {
       // return Keys.hmacShaKeyFor(secretKey);
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public String decodeToken(String token)  {
        return "";
    }

    public String extractUsername(String token) throws NoSuchAlgorithmException {

        return getClaims(token).getSubject();
    }

    public boolean isValid(String token) {
         boolean isExpired = getClaims(token).getExpiration().before(new Date());
        return !isExpired;
    }

    private Claims getClaims(String token){
        try {
            return Jwts.parser().
                    setSigningKey(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}

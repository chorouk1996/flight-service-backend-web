package com.service.backend.web.services.implementation;

import com.service.backend.web.exceptions.FunctionalException;
import com.service.backend.web.exceptions.FunctionalExceptionDto;
import com.service.backend.web.models.enumerators.TypeTokenEnum;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class JwtService {

    @Value("${secret_key}")
    private String secretKey;
    /*private byte[] secretKey ;
    public JwtService() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
        this.secretKey  = keyGen.generateKey().getEncoded();
    }*/

    public String generateToken(String username) {
        return tokenGeneration(username,new HashMap<>(),new Date(System.currentTimeMillis() + 1000 * 60 * 60));
    }

    public String generateResetToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("Type", TypeTokenEnum.RESET_TOKEN);
        return tokenGeneration(username,claims,new Date(System.currentTimeMillis() + 1000 * 60 * 15));
    }


    private String tokenGeneration(String username ,Map<String, Object> claims ,Date date) {
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .expiration(date)
                .issuedAt(new Date())
                .and()
                .signWith(getKey())
                .compact();

    }
    public String generateRefreshToken(String username){
        Map<String, Object> claims = new HashMap<>();
        claims.put("type", "refresh");
        claims.put("jti", UUID.randomUUID().toString());
        return tokenGeneration(username,claims,new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 30));
    }

    public String resetToken(String token){
        return  generateToken(extractUsername(token));
    }
    public SecretKey getKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }


    public String extractUsername(String token) {

        return getClaims(token).getSubject();
    }

    public boolean isValid(String token) {
        boolean isExpired = getClaims(token).getExpiration().before(new Date());
        return !isExpired;
    }

    private Claims getClaims(String token) {
        try {
            return  Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        }
        catch(ExpiredJwtException e){
            FunctionalExceptionDto ex = new FunctionalExceptionDto();
            ex.setMessage("Your Token Expired, request a new one");
            ex.setStatus(HttpStatus.UNAUTHORIZED);
            ex.setTimestamp(LocalDateTime.now());
            ex.setError("TOKEN_EXPIRED");
            throw new FunctionalException(ex);
        }
    }
}

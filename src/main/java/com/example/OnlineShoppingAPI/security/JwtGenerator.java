package com.example.OnlineShoppingAPI.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {
    @Value("${spring.credentialNotFound.error}")
    String credentialNotFound;
    public String generateToken(Authentication authentication){
        String username= authentication.getName();
        Date currentDate=new Date();
        Date expiredDate=new Date(currentDate.getTime()+SecurityConstants.JWT_EXPIRATION);

        String token= Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiredDate)
                .signWith(SignatureAlgorithm.HS384,SecurityConstants.JWT_SECRET)
                .compact();
        return token;
    }
    public String getUserNameFromJWT(String token){
        Claims claims=Jwts.parser()
                .setSigningKey(SecurityConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser().setSigningKey(SecurityConstants.JWT_SECRET).parseClaimsJws(token);
            return true;
        }catch (Exception exception){
            throw new AuthenticationCredentialsNotFoundException(credentialNotFound);
        }
    }
}

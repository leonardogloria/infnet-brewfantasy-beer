package br.com.brewfantasy.beer.service;

import br.com.brewfantasy.beer.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    private final static String mySecret = "MySuperSecret12#";
    public String generateToken(Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Date now = new Date();
        Date ext = new Date(now.getTime() + 100_000L);
        return Jwts.builder().setIssuer("brewFantasy")
                .setSubject(user.getId().toString())
                .setIssuedAt(new Date())
                .setExpiration(ext)
                .signWith(SignatureAlgorithm.HS256, mySecret)
                .compact();
    }
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(mySecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public Long getTokenId(String token) {
        Claims body = Jwts.parser().setSigningKey(mySecret).parseClaimsJws(token).getBody();
        return Long.valueOf(body.getSubject());
    }
}

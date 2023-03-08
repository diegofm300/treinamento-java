package com.treinamentojava.treinamentojava.usuario.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "7134743777217A25432A462D4A614E645267556A586E3272357538782F413F44";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /*
     * Serve para extrair apenas o que for solicitado do token
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername()) //parte exclusiva, geralmente aqui fica uma informação que determina de qual entidade é o token, como username ou id
                .setIssuedAt(new Date(System.currentTimeMillis())) // data em que ele foi gerado
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24)) // definida dt de explicação em 24 horas
                .signWith(getSigninKey(), SignatureAlgorithm.HS256) // algoritmo usado
                .compact();
    }

    /**
     * Método que irá validar o token, verificando se o usuário está correto e se o token não está expirado
     * @param token
     * @param userDetails
     * @return
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date()); //verifica se a data de expiração do token é
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /*
     * Serve para extrair tudo que está declarado no token
     */
    public Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /*
     * Obtém a chave de assinatura
     */
    private Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

        return Keys.hmacShaKeyFor(keyBytes);
    }
}

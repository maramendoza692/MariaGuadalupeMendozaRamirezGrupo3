package com.mmendozar.shopAll.service;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    // Inyección del valor de la clave secreta para la firma de tokens JWT.
    @Value("${jwt.secret}")
    private String secret;

    
    // -------- Generar token -------------
    @SuppressWarnings("deprecation")
	public String generateToken(String username) {
        // Tiempo de expiración del token en milisegundos (1 hora en este caso).
        long expirationTimeInMillis = 3600000;

        // Convierte la clave secreta en bytes usando UTF-8.
        byte[] secretKeyBytes = secret.getBytes(StandardCharsets.UTF_8);

        // Construye el token JWT.
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())  
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMillis))
                .signWith(SignatureAlgorithm.HS512, secretKeyBytes)
                .compact();
    }
}

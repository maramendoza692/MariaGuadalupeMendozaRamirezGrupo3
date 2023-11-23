package com.mmendozar.shopAll.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

public class JwtTokenFilter extends OncePerRequestFilter {

    private String secret;

    // Constructor que inicializa el filtro con una clave secreta.
    public JwtTokenFilter(String s) {
        this.secret = s;
    }

//------------ Filtrar las solicitudes -----------
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Verifica si el header de autorización no es nulo y si comienza con "Bearer ".
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            // Extraer el jwt del header.
            String token = tokenHeader.split(" ")[1].trim();

            try {
                byte[] secretKeyBytes = secret.getBytes(StandardCharsets.UTF_8);

                Jws<Claims> claims = Jwts.parserBuilder()
                        .setSigningKey(secretKeyBytes)
                        .build()
                        .parseClaimsJws(token);

                // Obtener el nombre de usuario (subject) del cuerpo del token.
                String username = claims.getBody().getSubject();

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            username, null, Collections.emptyList());

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            } catch (Exception e) {
                e.printStackTrace();
                SecurityContextHolder.clearContext();
            }
        }


        chain.doFilter(request, response);
    }
}

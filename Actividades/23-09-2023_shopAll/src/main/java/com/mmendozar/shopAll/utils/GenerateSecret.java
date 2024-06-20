package com.mmendozar.shopAll.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import javax.crypto.SecretKey;

public class GenerateSecret {
    public static void main(String[] args) {
        // Generar una clave secreta usando el algoritmo HMAC SHA-512
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        // Codificar la clave secreta en formato Base64
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());

        // Imprimir la clave codificada en Base64 en la consola
        System.out.println(base64Key);
    }
}


package com.mmendozar.shopAll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mmendozar.shopAll.model.Usuario;
import com.mmendozar.shopAll.model.dto.CredencialesDTO;
import com.mmendozar.shopAll.model.dto.RespuestaGenerica;
import com.mmendozar.shopAll.repository.UsuarioRepository;
import com.mmendozar.shopAll.utils.Constantes;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
    
   // ----------- Generar token --------
    public RespuestaGenerica getTokenUsuario(CredencialesDTO credencialesDto) {
    	RespuestaGenerica response = new RespuestaGenerica();
    	
    	String token = null;
        Usuario usuario = usuarioRepository.findByEmail(credencialesDto.getEmail())
                .orElseThrow(() -> new EntityNotFoundException(Constantes.MENSAJE_EMAIL_CONTRASEÑA));

        // Validar que la contraseña sea correcta
        boolean contraseñasIguales = passwordEncoder.matches(credencialesDto.getPassword(), usuario.getPassword());

        if (contraseñasIguales) {
            token = jwtService.generateToken(credencialesDto.getEmail());
            response.setMensaje(Constantes.TOKEN_CORRECTO);
            response.setExito(true);
            response.getDatos().add(token);
            
        } else {
        	response.setExito(true);
            response.setMensaje(Constantes.MENSAJE_EMAIL_CONTRASEÑA);
        }

        return response;
    }
}
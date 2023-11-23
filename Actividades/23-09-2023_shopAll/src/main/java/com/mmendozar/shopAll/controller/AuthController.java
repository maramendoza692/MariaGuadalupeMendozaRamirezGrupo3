package com.mmendozar.shopAll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmendozar.shopAll.model.dto.CredencialesDTO;
import com.mmendozar.shopAll.model.dto.RespuestaGenerica;
import com.mmendozar.shopAll.service.AuthService;
import com.mmendozar.shopAll.service.JwtService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/auth")
public class AuthController {
	
    @Value("${myapp.username}")
    private String configuredUsername;

    @Value("${myapp.password}")
    private String configuredPassword;

    @Autowired
    private final JwtService jwtService;
    
    @Autowired
    private AuthService authService;
    
    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    //--------- Manejar las solicitudes POST a "/auth/login"---------------
    @PostMapping("/login")
    public ResponseEntity<RespuestaGenerica> authenticate(@Valid @RequestBody CredencialesDTO credenciales) {
    	
    	RespuestaGenerica response = authService.getTokenUsuario(credenciales);
    	HttpStatus status = null;
    	
        if (response.isExito()) {
            status = HttpStatus.OK;
            response.setCodigo(status.value());
        }else {
        	status = HttpStatus.BAD_REQUEST;
        	response.setCodigo(status.value());
        }
        
        return new ResponseEntity<>(response, status);
    }
}

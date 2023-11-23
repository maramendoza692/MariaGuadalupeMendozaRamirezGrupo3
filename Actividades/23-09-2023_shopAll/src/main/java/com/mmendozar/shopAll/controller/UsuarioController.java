package com.mmendozar.shopAll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmendozar.shopAll.model.Response;
import com.mmendozar.shopAll.model.Usuario;
import com.mmendozar.shopAll.model.dto.UsuarioDTO;
import com.mmendozar.shopAll.service.UsuarioService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // --------- Consultar Todos ---------
    @GetMapping("/consultarTodos")
    public ResponseEntity<Response<Usuario>> consultarTodos() {
        Response<Usuario> response = usuarioService.consultarTodos();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // --------- Guardar ---------
    @PostMapping("/guardar")
    public ResponseEntity<Response<Usuario>> guardarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Response<Usuario> response = usuarioService.guardarUsuario(usuarioDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // --------- Eliminar ---------
    @DeleteMapping("/eliminar/{idUsuario}")
    public ResponseEntity<Response<Integer>> eliminarUsuario(@PathVariable Integer idUsuario) {
        Response<Integer> response = usuarioService.eliminarUsuario(idUsuario);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // --------- Actualizar ---------
    @PutMapping("/actualizar/{idUsuario}")
    public ResponseEntity<Response<Usuario>> actualizarUsuario(
    		@Valid @PathVariable Integer idUsuario,
            @RequestBody UsuarioDTO usuarioDTO) {
        Response<Usuario> response = usuarioService.actualizarUsuario(idUsuario, usuarioDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

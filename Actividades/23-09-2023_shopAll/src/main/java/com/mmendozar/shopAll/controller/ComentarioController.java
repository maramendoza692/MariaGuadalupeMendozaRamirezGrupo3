package com.mmendozar.shopAll.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mmendozar.shopAll.model.Comentario;
import com.mmendozar.shopAll.model.Response;
import com.mmendozar.shopAll.model.dto.ComentarioDTO;
import com.mmendozar.shopAll.service.ComentarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    // --------- Consultar  --------
    @GetMapping("/consultarTodos")
    public ResponseEntity<Response<Comentario>> consultarTodosComentarios() {
        Response<Comentario> response = comentarioService.consultarTodosComentarios();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // --------- Guardar ------------
    @PostMapping("/guardar")
    public ResponseEntity<Response<Comentario>> guardarComentario(@Valid @RequestBody ComentarioDTO comentario) {
        Response<Comentario> response = comentarioService.guardarComentario(comentario);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // --------- Eliminar ------------
    @DeleteMapping("/eliminar/{idComentario}")
    public ResponseEntity<Response<Integer>> eliminarComentario(@PathVariable Integer idComentario) {
        Response<Integer> response = comentarioService.eliminarComentario(idComentario);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // --------- Actualizar ------
    @PutMapping("/actualizar/{idComentario}")
    public ResponseEntity<Response<Comentario>> actualizarComentario(
    		@Valid
            @PathVariable Integer idComentario,
            @RequestBody ComentarioDTO nuevoComentario) {
        Response<Comentario> response = comentarioService.actualizarComentario(idComentario, nuevoComentario);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}


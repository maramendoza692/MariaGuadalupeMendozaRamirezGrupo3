package com.mmendozar.shopAll.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.mmendozar.shopAll.model.Categoria;
import com.mmendozar.shopAll.model.Response;
import com.mmendozar.shopAll.model.dto.CategoriaDTO;
import com.mmendozar.shopAll.service.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categorias")
@Validated
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    // --------- Consultar Todos ---------
    @GetMapping("/consultarTodas")
    public ResponseEntity<Response<Categoria>> consultarTodasCategorias() {
        Response<Categoria> response = categoriaService.consultarTodasCategorias();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // --------- Guardar ---------
    @PostMapping("/guardar")
    public ResponseEntity<Response<Categoria>> guardarCategoria(@Valid @RequestBody CategoriaDTO categoria) {
    	Response<Categoria> response = categoriaService.guardarCategoria(categoria);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // --------- Eliminar ---------

    @DeleteMapping("/eliminar/{idCategoria}")
    public ResponseEntity<Response<Integer>> eliminarCategoria(@PathVariable Integer idCategoria) {
        Response<Integer> response = categoriaService.eliminarCategoria(idCategoria);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // --------- Actualizar ---------
    @PutMapping("/actualizar/{idCategoria}")
    public ResponseEntity<Response<Categoria>> actualizarCategoria(@Valid
            @PathVariable Integer idCategoria,
            @RequestBody CategoriaDTO nuevaCategoria) {
        Response<Categoria> response = categoriaService.actualizarCategoria(idCategoria, nuevaCategoria);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}


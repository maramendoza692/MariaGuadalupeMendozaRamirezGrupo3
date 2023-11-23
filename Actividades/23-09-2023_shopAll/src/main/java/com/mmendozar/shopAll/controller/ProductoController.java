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

import com.mmendozar.shopAll.model.Producto;
import com.mmendozar.shopAll.model.Response;
import com.mmendozar.shopAll.model.dto.ProductoDTO;
import com.mmendozar.shopAll.service.ProductoService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // --------- Consultar Todos ---------
    @GetMapping("/consultarTodos")
    public ResponseEntity<Response<Producto>> consultarTodos() {
        Response<Producto> response = productoService.consultarTodos();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // --------- Guardar ---------
    @PostMapping("/guardar")
    public ResponseEntity<Response<Producto>> guardarProducto(@Valid @RequestBody ProductoDTO productoDTO) {
        Response<Producto> response = productoService.guardarProducto(productoDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // --------- Eliminar ---------
    @DeleteMapping("/eliminar/{idProducto}")
    public ResponseEntity<Response<Integer>> eliminarProducto(@PathVariable Integer idProducto) {
        Response<Integer> response = productoService.eliminarProducto(idProducto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // --------- Actualizar ---------
    @PutMapping("/actualizar/{idProducto}")
    public ResponseEntity<Response<Producto>> actualizarProducto(
    		@Valid
    		@PathVariable Integer idProducto,
            @RequestBody ProductoDTO productoDTO) {
        Response<Producto> response = productoService.actualizarProducto(idProducto, productoDTO);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

package com.mmendozar.shopAll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mmendozar.shopAll.model.Transaccion;
import com.mmendozar.shopAll.model.dto.TransaccionDTO;
import com.mmendozar.shopAll.model.Response;
import com.mmendozar.shopAll.service.TransaccionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transacciones")
public class TransaccionController {

    @Autowired
    private TransaccionService transaccionService;

    // --------- Consultar Todos ---------
    @GetMapping("/consultarTodas")
    public ResponseEntity<Response<Transaccion>> consultarTodasTransacciones() {
        Response<Transaccion> response = transaccionService.consultarTodasTransacciones();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // --------- Guardar ---------
    @PostMapping("/generarTransaccion")
    public ResponseEntity<Response<Transaccion>> generarTransaccion(@Valid @RequestBody TransaccionDTO transaccion) {
        Response<Transaccion> response = transaccionService.generarTransaccion(transaccion);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // --------- Eliminar ---------
    @DeleteMapping("/eliminar/{idTransaccion}")
    public ResponseEntity<Response<Integer>> eliminarTransaccion(@PathVariable Integer idTransaccion) {
        Response<Integer> response = transaccionService.eliminarTransaccion(idTransaccion);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // --------- Actualizar ---------
    @PutMapping("/actualizar/{idTransaccion}")
    public ResponseEntity<Response<Transaccion>> actualizarTransaccion(
    		@Valid @PathVariable Integer idTransaccion,
            @RequestBody TransaccionDTO nuevaTransaccion) {
        Response<Transaccion> response = transaccionService.actualizarTransaccion(idTransaccion, nuevaTransaccion);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

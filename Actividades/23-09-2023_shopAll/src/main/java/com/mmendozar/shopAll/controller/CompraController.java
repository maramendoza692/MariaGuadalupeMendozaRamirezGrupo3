package com.mmendozar.shopAll.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mmendozar.shopAll.model.Compra;
import com.mmendozar.shopAll.model.Response;
import com.mmendozar.shopAll.model.dto.CompraDTO;
import com.mmendozar.shopAll.service.CompraService;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/compra")
public class CompraController {
	
	@Autowired
	private CompraService compraService;
	
	// --------- Consultar Todas ---------
    @GetMapping("/consultarTodos")
    public ResponseEntity<Response<Compra>> consultarTodos() {
        Response<Compra> response = compraService.consultarTodos();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

	// ---------- Generar compra --------
	
	@PostMapping("/generarCompra")
	public ResponseEntity<Response<Compra>> generarCompra (@Valid @RequestBody CompraDTO compra){
		
		Response<Compra> response = compraService.generarCompra(compra);
		return new ResponseEntity<Response<Compra>> (response, HttpStatus.OK);
		
		
	}
	
		
		
}

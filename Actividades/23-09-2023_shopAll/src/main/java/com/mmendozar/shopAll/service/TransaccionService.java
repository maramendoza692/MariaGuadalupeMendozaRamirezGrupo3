package com.mmendozar.shopAll.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mmendozar.shopAll.model.Transaccion;
import com.mmendozar.shopAll.model.dto.TransaccionDTO;
import com.mmendozar.shopAll.model.Compra;
import com.mmendozar.shopAll.model.Response;
import com.mmendozar.shopAll.repository.TransaccionRepository;
import com.mmendozar.shopAll.repository.CompraRepository;
import com.mmendozar.shopAll.utils.Constantes;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Autowired
    private CompraRepository compraRepository;

    // --------- Consultar Todos ---------
    public Response<Transaccion> consultarTodasTransacciones() {
        Response<Transaccion> response = new Response<>();
        List<Transaccion> listaTransacciones = transaccionRepository.findAll();

        if (listaTransacciones.isEmpty()) {
            response.setMessage(Constantes.NO_HAY_RESULTADOS);
        } else {
            response.setMessage(Constantes.CONSULTA_CORRECTA);
        }

        response.setStatus("OK");
        response.setList(listaTransacciones);
        response.setCount(listaTransacciones.size());

        return response;
    }

    // --------- Guardar ---------
    public Response<Transaccion> generarTransaccion(TransaccionDTO transaccionDto) {
        Response<Transaccion> response = new Response<>();

        try {
            Transaccion transaccionNueva = new Transaccion();
            
            // Validar que la compra asociada exista
            Optional<Compra> optionalCompra = compraRepository.findById(transaccionDto.getIdCompra());

            if (!optionalCompra.isPresent()) {
                response.setStatus("ERROR");
                response.setMessage(Constantes.COMPRA_NO_EXISTENTE);
                return response;
            }

            Compra compra = optionalCompra.get();
            
            transaccionNueva.setFecha(new Date(System.currentTimeMillis()));
            transaccionNueva.setTotal(compra.getTotal());
            transaccionNueva.setIdCompra(compra);

            Transaccion transaccionGuardada = transaccionRepository.save(transaccionNueva);

            response.setStatus("OK");
            response.setMessage(Constantes.GUARDADO_CORRECTAMENTE);
            response.setData(transaccionGuardada);
        } catch (Exception e) {
            response.setStatus("ERROR");
            response.setMessage(Constantes.ERROR);
        }

        return response;
    }


    // --------- Eliminar ---------
    public Response<Integer> eliminarTransaccion(Integer idTransaccion) {
        Response<Integer> response = new Response<>();
        transaccionRepository.deleteById(idTransaccion);

        response.setStatus("OK");
        response.setMessage(Constantes.ELIMINADO_CORRECTAMENTE);
        response.setData(idTransaccion);

        return response;
    }

    // --------- Actualizar ---------
    public Response<Transaccion> actualizarTransaccion(Integer idTransaccion, TransaccionDTO transaccionDto) {
        Response<Transaccion> response = new Response<>();

        try {
            Optional<Transaccion> optionalTransaccion = transaccionRepository.findById(idTransaccion);
            Optional<Compra> optionalCompra = compraRepository.findById(transaccionDto.getIdCompra());

            if (!optionalTransaccion.isPresent()) {
                response.setStatus("ERROR");
                response.setMessage(Constantes.TRANSACCION_NO_EXISTENTE);
                return response;
            }

            if (!optionalCompra.isPresent()) {
                response.setStatus("ERROR");
                response.setMessage(Constantes.COMPRA_NO_EXISTENTE);
                return response;
            }

            Transaccion transaccionExistente = optionalTransaccion.get();
            Compra compra = optionalCompra.get();

            // Actualizar los campos de la transacción existente con la información proporcionada
            transaccionExistente.setFecha(transaccionExistente.getFecha());
            transaccionExistente.setTotal(compra.getTotal());
            transaccionExistente.setIdCompra(compra);

            Transaccion transaccionActualizada = transaccionRepository.save(transaccionExistente);

            response.setStatus("OK");
            response.setMessage(Constantes.ACTUALIZADO_CORRECTAMENTE);
            response.setData(transaccionActualizada);
        } catch (Exception e) {
            response.setStatus("ERROR");
            response.setMessage(Constantes.ERROR);
        }

        return response;
    }

}


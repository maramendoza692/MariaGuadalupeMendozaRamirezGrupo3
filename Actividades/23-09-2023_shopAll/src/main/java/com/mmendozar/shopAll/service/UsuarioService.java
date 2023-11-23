package com.mmendozar.shopAll.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mmendozar.shopAll.model.Response;
import com.mmendozar.shopAll.model.Rol;
import com.mmendozar.shopAll.model.Usuario;
import com.mmendozar.shopAll.model.dto.UsuarioDTO;
import com.mmendozar.shopAll.repository.RolRepository;
import com.mmendozar.shopAll.repository.UsuarioRepository;
import com.mmendozar.shopAll.utils.Constantes;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    // --------- Consultar Todos ----------
    public Response<Usuario> consultarTodos() {
        Response<Usuario> response = new Response<Usuario>();
        List<Usuario> lista = usuarioRepository.findAll();

        if (lista.isEmpty()) {
            response.setMessage(Constantes.NO_HAY_RESULTADOS);
        } else {
            response.setMessage(Constantes.CONSULTA_CORRECTA);
        }
        response.setStatus("OK");
        response.setList(lista);
        response.setCount(lista.size());

        return response;
    }

    // ----------- Guardar ----------
    public Response<Usuario> guardarUsuario(UsuarioDTO usuarioDTO) {
        Response<Usuario> response = new Response<Usuario>();

        Usuario usuario1 = null;
        Usuario usuario2 = null;

        Optional<Rol> optionalRol = rolRepository.findById(usuarioDTO.getIdRol());

        if (!optionalRol.isPresent()) {
            response.setStatus("ERROR");
            response.setMessage(Constantes.ROL_NO_EXISTENTE);
            response.setData(null);
            return response;
        }

        Rol rol = optionalRol.get();

        usuario1 = new Usuario();
        usuario1.setNombre(usuarioDTO.getNombre());
        usuario1.setApePaterno(usuarioDTO.getApePaterno());
        usuario1.setApeMaterno(usuarioDTO.getApeMaterno());
        usuario1.setTelefono(usuarioDTO.getTelefono());
        usuario1.setEmail(usuarioDTO.getEmail());
        usuario1.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        usuario1.setIdRol(rol);

        usuario2 = usuarioRepository.save(usuario1);
        response.setStatus("OK");
        response.setMessage(Constantes.GUARDADO_CORRECTAMENTE);
        response.setData(usuario2);

        return response;
    }

    // ------------- Eliminar ------------
    public Response<Integer> eliminarUsuario(Integer idUsuario) {
        Response<Integer> response = new Response<Integer>();
        usuarioRepository.deleteById(idUsuario);

        response.setMessage(Constantes.ELIMINADO_CORRECTAMENTE);
        response.setStatus("OK");
        response.setData(idUsuario);

        return response;
    }

    // ------------- Actualizar ----------
    public Response<Usuario> actualizarUsuario(Integer idUsuario, UsuarioDTO usuarioDTO) {
        Response<Usuario> response = new Response<Usuario>();

        Optional<Usuario> optionalUsuario = usuarioRepository.findById(idUsuario);
        Optional<Rol> optionalRol = rolRepository.findById(usuarioDTO.getIdRol());

        if (!optionalUsuario.isPresent()) {
            response.setStatus("ERROR");
            response.setMessage(Constantes.USUARIO_NO_EXISTENTE);
            response.setData(null);
            return response;
        }

        if (!optionalRol.isPresent()) {
            response.setStatus("ERROR");
            response.setMessage(Constantes.ROL_NO_EXISTENTE);
            response.setData(null);
            return response;
        }

        Usuario usuarioExistente = optionalUsuario.get();
        Rol rol = optionalRol.get();

        // Actualizar los campos del usuario existente con la información proporcionada
        usuarioExistente.setNombre(usuarioDTO.getNombre());
        usuarioExistente.setApePaterno(usuarioDTO.getApePaterno());
        usuarioExistente.setApeMaterno(usuarioDTO.getApeMaterno());
        usuarioExistente.setTelefono(usuarioDTO.getTelefono());
        usuarioExistente.setEmail(usuarioDTO.getEmail());
        usuarioExistente.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
        usuarioExistente.setIdRol(rol);

        Usuario usuarioActualizado = usuarioRepository.save(usuarioExistente);
        response.setStatus("OK");
        response.setMessage(Constantes.ACTUALIZADO_CORRECTAMENTE);
        response.setData(usuarioActualizado);

        return response;
    }
}
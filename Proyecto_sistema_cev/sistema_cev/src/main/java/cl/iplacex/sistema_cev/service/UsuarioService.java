package cl.iplacex.sistema_cev.service;

import cl.iplacex.sistema_cev.dto.UsuarioDTO;
import cl.iplacex.sistema_cev.exception.ResourceNotFoundException;
import cl.iplacex.sistema_cev.model.Usuario;
import cl.iplacex.sistema_cev.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Optional<Usuario> encontrarPorRut(String rut) {
        return usuarioRepository.findByRutUsuario(rut);
    }

    @Override
    public Usuario actualizarPerfil(String rut, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findByRutUsuario(rut)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con RUT: " + rut));

        // Mapear el DTO a la entidad para actualizar solo los campos permitidos
        if (usuarioDTO.getEmail() != null) {
            usuario.setEmail(usuarioDTO.getEmail());
        }
        if (usuarioDTO.getTelefono() != null) {
            usuario.setTelefono(usuarioDTO.getTelefono());
        }
        if (usuarioDTO.getDireccion() != null) {
            usuario.setDireccion(usuarioDTO.getDireccion());
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioDTO obtenerPerfil(String rut) {
        Usuario usuario = usuarioRepository.findByRutUsuario(rut)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con RUT: " + rut));

        // Mapear la entidad al DTO para el perfil
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setRutUsuario(usuario.getRutUsuario());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setTelefono(usuario.getTelefono());
        usuarioDTO.setDireccion(usuario.getDireccion());

        return usuarioDTO;
    }

    @Override
    public void eliminarUsuario(String rut) {
        if (!usuarioRepository.existsByRutUsuario(rut)) {
            throw new ResourceNotFoundException("Usuario no encontrado con RUT: " + rut);
        }
        usuarioRepository.deleteByRutUsuario(rut);
    }
}
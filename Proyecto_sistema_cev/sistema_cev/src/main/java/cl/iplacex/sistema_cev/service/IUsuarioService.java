package cl.iplacex.sistema_cev.service;

import cl.iplacex.sistema_cev.dto.UsuarioDTO;
import cl.iplacex.sistema_cev.model.Usuario;
import java.util.Optional;

public interface IUsuarioService {

    // Método para encontrar un usuario por su RUT
    Optional<Usuario> encontrarPorRut(String rut);

    // Método para actualizar un perfil de usuario usando un DTO
    Usuario actualizarPerfil(String rut, UsuarioDTO usuarioDTO);

    // Método para obtener un perfil de usuario por su RUT
    UsuarioDTO obtenerPerfil(String rut);

    // Método para eliminar un usuario
    void eliminarUsuario(String rut);
}
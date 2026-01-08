package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import cl.iplacex.sistema_cev.model.Rol;

public interface IRolService {
    // Crear Rol
    Rol create(Rol rol);

    // Obtener Lista de Roles
    List<Rol> readAll();

    // Obtener Rol por idRol
    Optional<Rol> findById(Long idRol);

    // Actualizar Rol
    Rol update(Rol rol);

    //Eliminar Rol por id
    void delete(Long idRol);

}

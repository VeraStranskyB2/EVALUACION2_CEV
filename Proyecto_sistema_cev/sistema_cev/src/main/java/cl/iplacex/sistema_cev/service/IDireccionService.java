package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import cl.iplacex.sistema_cev.model.Direccion;

public interface IDireccionService {

    // Crear Direccion
    Direccion create(Direccion direccion);

    // Obtener todas las direcciones
    List<Direccion> readAll();

    // Encontrar Direccion por ID
    Optional<Direccion> findById(Long idDireccion);

    // Actualizar una Direccion
    Direccion update(Direccion direccion);

    // Eliminar una Direccion por ID
    void delete(Long idDireccion);
}
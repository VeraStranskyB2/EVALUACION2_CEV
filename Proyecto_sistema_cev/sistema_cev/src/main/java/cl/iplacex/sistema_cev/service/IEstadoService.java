package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import cl.iplacex.sistema_cev.model.Estado;

public interface IEstadoService {

    // Crear Estado
    Estado create(Estado estado);

    // Obtener Estado
    List<Estado> readAll();

    // Encontrar Estado por ID
    Optional<Estado> getEstadoById(Long idEstado);

    // Actualizar Estado
    Estado update(Estado estado);

    // Eliminar Estado ID
    void delete(Long idEstado);

}

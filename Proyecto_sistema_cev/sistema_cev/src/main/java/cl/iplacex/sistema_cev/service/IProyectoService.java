package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import cl.iplacex.sistema_cev.dto.ProyectoDTO;

public interface IProyectoService {

    // Crear Proyecto
    ProyectoDTO create(ProyectoDTO proyectoDTO);

    // Obtener todos los Proyectos
    List<ProyectoDTO> readAll();

    // Encontrar Proyecto por ID
    Optional<ProyectoDTO> readById(String idProyecto);

    // Encontrar Proyecto por Rol Matriz
    Optional<ProyectoDTO> readByRolMatriz(String rolMatriz);

    // Actualizar Proyecto
    ProyectoDTO update(ProyectoDTO proyectoDTO);

    // Eliminar Proyecto por ID
    void delete(String idProyecto);

}

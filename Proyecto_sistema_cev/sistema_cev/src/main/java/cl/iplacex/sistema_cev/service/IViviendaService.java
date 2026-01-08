package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import cl.iplacex.sistema_cev.dto.ViviendaDTO;

public interface IViviendaService {

    // Crear Vivienda
    ViviendaDTO create(ViviendaDTO viviendaDTO);

    // Obtener todas las Viviendas
    List<ViviendaDTO> readAll();

    // Encontrar Vivienda por Rol
    Optional<ViviendaDTO> readByRolVivienda(String rolVivienda);

    // Actualizar Vivienda
    ViviendaDTO update(ViviendaDTO viviendaDTO);

    // Eliminar Vivienda por rol
    void delete(String rolVivienda);

}

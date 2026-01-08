package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import cl.iplacex.sistema_cev.dto.SolicitudDTO;


public interface ISolicitudService {

    // Crear Solicitud
    SolicitudDTO create(SolicitudDTO solicitudDTO);

    // Obtener todas las Solicitudes
    List<SolicitudDTO> readAll();

    // Encontrar Solicitud por ID
    Optional<SolicitudDTO> readById(String idSolicitud);

    // Encontrar Solicitud por Estado
    List<SolicitudDTO> readByEstado(Long idEstado);

    // Actualizar Solicitud
    SolicitudDTO update(SolicitudDTO solicitudDTO);

    // Eliminar Solicitud por ID
    void delete(String idSolicitud);

}

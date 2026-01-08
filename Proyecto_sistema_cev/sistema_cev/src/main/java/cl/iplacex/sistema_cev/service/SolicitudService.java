package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cl.iplacex.sistema_cev.dto.SolicitudDTO;
import cl.iplacex.sistema_cev.model.Cliente;
import cl.iplacex.sistema_cev.model.Proyecto;
import cl.iplacex.sistema_cev.model.Solicitud;
import cl.iplacex.sistema_cev.repository.ClienteRepository;

import cl.iplacex.sistema_cev.repository.ProyectoRepository;
import cl.iplacex.sistema_cev.repository.SolicitudRepository;

@Service
public class SolicitudService implements ISolicitudService {

    @Autowired
    private SolicitudRepository solicitudRepository;

    @Autowired
    private ProyectoRepository proyectoRepository; 

    @Autowired
    private ClienteRepository clienteRepository;

    
    // Crear Solicitud
    @Override
    public SolicitudDTO create(SolicitudDTO solicitudDTO) {
        // Convertir DTO a entidad
        Solicitud solicitud = convertToEntity(solicitudDTO);
        return convertToDTO(solicitudRepository.save(solicitud));
    }

    // Obtener todas las Solicitudes
    @Override
    public List<SolicitudDTO> readAll() {
        return solicitudRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Encontrar Solicitud por ID
    @Override
    public Optional<SolicitudDTO> readById(String idSolicitud) {
        return solicitudRepository.findById(idSolicitud)
                .map(this::convertToDTO);
    }

    // Encontrar Solicitud por Estado
    @Override
    public List<SolicitudDTO> readByEstado(Long idEstado) {
        return solicitudRepository.findByEstado_IdEstado(idEstado).stream()
        .map(this::convertToDTO)
        .collect(Collectors.toList());
    }

    
    // Actualizar Solicitud
    @Override
    public SolicitudDTO update(SolicitudDTO solicitudDTO) {
        // Verificar si la solicitud existe antes de actualizar
        Solicitud solicitud = solicitudRepository.findById(solicitudDTO.getIdSolicitud())
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con ID: " + solicitudDTO.getIdSolicitud()));

        // Actualiza los campos necesarios
        solicitud.setFechaIngreso(solicitudDTO.getFechaIngreso());
        solicitud.setTipo(solicitudDTO.getTipo());
        solicitud.setPermisoEdificacion(solicitudDTO.getPermisoEdificacion());
        solicitud.setRecepcionFinal(solicitudDTO.getRecepcionFinal());

        // Buscar Proyecto y Cliente usando  ID y RUT
        Proyecto proyecto = proyectoRepository.findById(solicitudDTO.getIdProyecto())
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + solicitudDTO.getIdProyecto()));
        Cliente cliente = clienteRepository.findByUsuarioRutUsuario(solicitudDTO.getUsuarioRutUsuario())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado por RUT: " + solicitudDTO.getUsuarioRutUsuario()));

        // Asignar los objetos encontrados
        solicitud.setProyecto(proyecto);
        solicitud.setCliente(cliente);

        return convertToDTO(solicitudRepository.save(solicitud));
    }


    // Eliminar Solicitud por ID
    @Override
    public void delete(String idSolicitud) {
        // Verificar si la solicitud existe antes de eliminar
        if (!solicitudRepository.existsById(idSolicitud)) {
            throw new RuntimeException("Solicitud no encontrada con ID: " + idSolicitud);
        }
        solicitudRepository.deleteById(idSolicitud);
    }
 

    // Método para convertir DTO a Entidad
    private Solicitud convertToEntity(SolicitudDTO solicitudDTO) {
        // Buscar Proyecto y Cliente usando sus IDs
        Proyecto proyecto = proyectoRepository.findById(solicitudDTO.getIdProyecto())
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado con ID: " + solicitudDTO.getIdProyecto()));
        Cliente cliente = clienteRepository.findByUsuarioRutUsuario(solicitudDTO.getUsuarioRutUsuario())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado por RUT: " + solicitudDTO.getUsuarioRutUsuario()));

        return new Solicitud(
                solicitudDTO.getIdSolicitud(),
                solicitudDTO.getFechaIngreso(),
                solicitudDTO.getTipo(),
                solicitudDTO.getPermisoEdificacion(),
                solicitudDTO.getRecepcionFinal(),
                proyecto, // Asignar el objeto Proyecto
                null, cliente   // Asignar el objeto Cliente
        );
    }

    // Método para convertir Entidad a DTO
    private SolicitudDTO convertToDTO(Solicitud solicitud) {
        return new SolicitudDTO(
                solicitud.getIdSolicitud(),
                solicitud.getFechaIngreso(),
                solicitud.getTipo(),
                solicitud.getPermisoEdificacion(),
                solicitud.getRecepcionFinal(),
                solicitud.getProyecto().getIdProyecto(), 
                solicitud.getCliente().getUsuarioRutUsuario() 
        );
    }
}



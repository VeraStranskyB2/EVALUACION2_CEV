package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.iplacex.sistema_cev.dto.ProyectoDTO;
import cl.iplacex.sistema_cev.model.Proyecto;
import cl.iplacex.sistema_cev.repository.ProyectoRepository;

@Service
public class ProyectoService implements IProyectoService {

    @Autowired
    private ProyectoRepository proyectoRepository;

    // Crear Proyecto
    @Override
    public ProyectoDTO create(ProyectoDTO proyectoDTO) {
        Proyecto proyecto = convertToEntity(proyectoDTO);
        Proyecto savedProyecto = proyectoRepository.save(proyecto);
        return convertToDTO(savedProyecto);
    }

    // Obtener todos los Proyectos
    @Override
    public List<ProyectoDTO> readAll() {
        return proyectoRepository.findAll().stream()
                                 .map(this::convertToDTO)
                                 .collect(Collectors.toList());
    }

    // Encontrar Proyecto por ID
    @Override
    public Optional<ProyectoDTO> readById(String idProyecto) {
        return proyectoRepository.findById(idProyecto)
                                 .map(this::convertToDTO);
    }

    // Encontrar Proyecto por Rol Matriz
    @Override
    public Optional<ProyectoDTO> readByRolMatriz(String rolMatriz) {
        return proyectoRepository.findByRolMatriz(rolMatriz)
                                 .map(this::convertToDTO);
    }

    // Actualizar Proyecto
    @Override
    public ProyectoDTO update(ProyectoDTO proyectoDTO) {
        if (proyectoRepository.existsById(proyectoDTO.getIdProyecto())) {
            Proyecto proyecto = convertToEntity(proyectoDTO);
            Proyecto updatedProyecto = proyectoRepository.save(proyecto);
            return convertToDTO(updatedProyecto);
        }
        throw new RuntimeException("Proyecto no encontrado para actualizar");
    }

    // Eliminar Proyecto por ID
    @Override
    public void delete(String idProyecto) {
        if (!proyectoRepository.existsById(idProyecto)) {
            throw new RuntimeException("Proyecto no encontrado para eliminar");
        }
        proyectoRepository.deleteById(idProyecto);
    }

    // Método para convertir DTO a Entidad
    private Proyecto convertToEntity(ProyectoDTO proyectoDTO) {
        Proyecto proyecto = new Proyecto();
        proyecto.setIdProyecto(proyectoDTO.getIdProyecto());
        proyecto.setRolMatriz(proyectoDTO.getRolMatriz());
        proyecto.setNombreProyecto(proyectoDTO.getNombreProyecto());
        proyecto.setDireccion(proyectoDTO.getDireccion());
        proyecto.setNumeroViviendas(proyectoDTO.getNumeroViviendas());
        
        return proyecto;
    }

    // Método para convertir Entidad a DTO
    private ProyectoDTO convertToDTO(Proyecto proyecto) {
        ProyectoDTO proyectoDTO = new ProyectoDTO();
        proyectoDTO.setIdProyecto(proyecto.getIdProyecto());
        proyectoDTO.setRolMatriz(proyecto.getRolMatriz());
        proyectoDTO.setNombreProyecto(proyecto.getNombreProyecto());
        proyectoDTO.setDireccion(proyecto.getDireccion());
        proyectoDTO.setNumeroViviendas(proyecto.getNumeroViviendas());
        proyectoDTO.setCreatedAt(proyecto.getCreatedAt());
        proyectoDTO.setUpdatedAt(proyecto.getUpdatedAt());
        
        return proyectoDTO;
    }
}
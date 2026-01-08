package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.iplacex.sistema_cev.dto.ViviendaDTO;
import cl.iplacex.sistema_cev.model.Vivienda;
import cl.iplacex.sistema_cev.repository.ViviendaRepository;

@Service
public class ViviendaService implements IViviendaService {

    @Autowired
    private ViviendaRepository viviendaRepository;

    // Crear Vivienda
    @Override
    public ViviendaDTO create(ViviendaDTO viviendaDTO) {
        Vivienda vivienda = convertToEntity(viviendaDTO);
        return convertToDTO(viviendaRepository.save(vivienda));
    }

    // Obtener todas las Viviendas
    @Override
    public List<ViviendaDTO> readAll() {
        return viviendaRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Encontrar Vivienda por Rol
    @Override
    public Optional<ViviendaDTO> readByRolVivienda(String rolVivienda) {
        return viviendaRepository.findByRolVivienda(rolVivienda)
                .map(this::convertToDTO);
        
    }

    // Actualizar Vivienda
    @Override
    public ViviendaDTO update(ViviendaDTO viviendaDTO) {
        // Verificar si la vivienda existe antes de actualizar
        if (!viviendaRepository.existsById(viviendaDTO.getRolVivienda())) {
            throw new RuntimeException("Vivienda no encontrada con rol: " + viviendaDTO.getRolVivienda());
        }
        Vivienda vivienda = convertToEntity(viviendaDTO);
        return convertToDTO(viviendaRepository.save(vivienda));
    }

    // Eliminar Vivienda por Rol
    @Override
    public void delete(String rolVivienda) {
        // Verificar si la vivienda existe antes de eliminar
        if (!viviendaRepository.existsById(rolVivienda)) {
            throw new RuntimeException("Vivienda no encontrada con rol: " + rolVivienda);
        }
        viviendaRepository.deleteByRolVivienda(rolVivienda);
    }  

    // Método para convertir ViviendaDTO a Vivienda
    private Vivienda convertToEntity(ViviendaDTO viviendaDTO) {
        return new Vivienda(
                viviendaDTO.getRolVivienda(),
                viviendaDTO.getDireccion(),
                viviendaDTO.getTipo(),
                viviendaDTO.getEstado(),
                viviendaDTO.getLote(),
                viviendaDTO.getManzana(),
                viviendaDTO.getEdificio(),
                viviendaDTO.getPiso(),
                viviendaDTO.getUnidad(),
                viviendaDTO.getOrientacion(),
                viviendaDTO.getMaterialidad(),
                null 
        );
    }

    // Método para convertir Vivienda a ViviendaDTO
    private ViviendaDTO convertToDTO(Vivienda vivienda) {
        return new ViviendaDTO(
                vivienda.getRolVivienda(),
                vivienda.getDireccion(),
                vivienda.getTipo(),
                vivienda.getEstado(),
                vivienda.getLote(),
                vivienda.getManzana(),
                vivienda.getEdificio(),
                vivienda.getPiso(),
                vivienda.getUnidad(),
                vivienda.getOrientacion(),
                vivienda.getMaterialidad()
        );
    }
    
}

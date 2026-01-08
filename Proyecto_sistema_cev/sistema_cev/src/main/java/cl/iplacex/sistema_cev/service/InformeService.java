package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.iplacex.sistema_cev.dto.InformeDTO;
import cl.iplacex.sistema_cev.model.Informe;
import cl.iplacex.sistema_cev.repository.InformeRepository;

@Service
public class InformeService implements IInformeService {

    @Autowired
    private final InformeRepository informeRepository;

    
    public InformeService(InformeRepository informeRepository) {
        this.informeRepository = informeRepository;
    }

    // Crear Informe
    @Override
    public InformeDTO create(InformeDTO informeDTO) {
        Informe informe = convertToEntity(informeDTO);
        Informe savedInforme = informeRepository.save(informe);
        return convertToDTO(savedInforme);
    }

    // Obtener todos los Informres
    @Override
    public List<InformeDTO> readAll() {
        return informeRepository.findAll().stream()
                                 .map(this::convertToDTO)
                                 .collect(Collectors.toList());
    }

    // Encontrar Informe por ID
    @Override
    public Optional<InformeDTO> findById(String idInforme) {
        return informeRepository.findById(idInforme)
                                 .map(this::convertToDTO);
    }

    // Actualizar Informe
    @Override
    public InformeDTO update(InformeDTO informeDTO) {
        if (informeRepository.existsById(informeDTO.getIdInforme())) {
            Informe informe = convertToEntity(informeDTO);
            Informe updatedInforme = informeRepository.save(informe);
            return convertToDTO(updatedInforme);
        }
        throw new RuntimeException("Proyecto no encontrado para actualizar");
    }


    // Eliminar Informe por ID
    @Override
    public void delete(String idInforme) {
        // Verificar si el informe existe antes de eliminar
        if (!informeRepository.existsById(idInforme)) {
            throw new RuntimeException("Informe no encontrado con ID: " + idInforme);
        }
        informeRepository.deleteById(idInforme);
    }

    // Método para convertir DTO a Entidad
    private Informe convertToEntity(InformeDTO informeDTO) {
        Informe informe = new Informe();
        informe.setIdInforme(informeDTO.getIdInforme());
        informe.setFechaGeneracion(informeDTO.getFechaGeneracion());
        informe.setPotenciaNominalSC(informeDTO.getPotenciaNominalSC());
        informe.setPotenciaNominalSV(informeDTO.getPotenciaNominalSV());
        informe.setPotenciaNominalACS(informeDTO.getPotenciaNominalACS());
        informe.setSuperficieTotalSSC(informeDTO.getSuperficieTotalSSC());
        informe.setSuperficieTotalSSI(informeDTO.getSuperficieTotalSSI());
        
        return informe;
    }

    // Método para convertir Entidad a DTO
    private InformeDTO convertToDTO(Informe savedInforme) {
            InformeDTO informeDTO = new InformeDTO();
            informeDTO.setFechaGeneracion(savedInforme.getFechaGeneracion());
            informeDTO.setPotenciaNominalSC(savedInforme.getPotenciaNominalSC());
            informeDTO.setPotenciaNominalSV(savedInforme.getPotenciaNominalSV());
            informeDTO.setPotenciaNominalACS(savedInforme.getPotenciaNominalACS());
            informeDTO.setSuperficieTotalSSC(savedInforme.getSuperficieTotalSSC());
            informeDTO.setSuperficieTotalSSI(savedInforme.getSuperficieTotalSSI());
                    
        return informeDTO;
    }

   
}
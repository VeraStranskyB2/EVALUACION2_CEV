package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;

import cl.iplacex.sistema_cev.dto.EvaluacionDTO;
import cl.iplacex.sistema_cev.model.Evaluacion;
import cl.iplacex.sistema_cev.model.Evaluador;
import cl.iplacex.sistema_cev.model.Proyecto;
import cl.iplacex.sistema_cev.repository.EvaluacionRepository;
import cl.iplacex.sistema_cev.repository.EvaluadorRepository;
import cl.iplacex.sistema_cev.repository.ProyectoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class EvaluacionService implements IEvaluacionService {

    
    private final EvaluacionRepository evaluacionRepository;
    private final ProyectoRepository proyectoRepository; 
    private final EvaluadorRepository evaluadorRepository;
    
    
    
    
    public EvaluacionService(EvaluacionRepository evaluacionRepository,
    ProyectoRepository proyectoRepository,
    EvaluadorRepository evaluadorRepository) {
        this.evaluacionRepository = evaluacionRepository;
        this.proyectoRepository = proyectoRepository;
        this.evaluadorRepository = evaluadorRepository;
        
        
    }

    // Crear Evaluacion
    @Override
    public EvaluacionDTO create(EvaluacionDTO evaluacionDTO) {
        // Buscar las entidades relacionadas
       
        Proyecto proyecto = proyectoRepository.findById(evaluacionDTO.getIdProyecto())
                .orElseThrow(() -> new EntityNotFoundException ());
        
        Evaluador evaluador = evaluadorRepository.findByUsuarioRutUsuario(evaluacionDTO.getUsuarioRutUsuario())
                .orElseThrow(() -> new EntityNotFoundException());

        Evaluacion evaluacion = new Evaluacion(
                evaluacionDTO.getIdEvaluacion(),
                evaluacionDTO.getFechaEvaluacion(),
                proyecto,
                evaluador
        );       
        
        return convertToDTO(evaluacionRepository.save(evaluacion));
    }

    // Obtener todas las Evaluaciones
    @Override
    public List<EvaluacionDTO> readAll() {
        return evaluacionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Encontrar Evaluacion por ID
    @Override
    public Optional<EvaluacionDTO> readById(String idEvaluacion) {
        return evaluacionRepository.findById(idEvaluacion)
                .map(this::convertToDTO);
    }
    
    // Actualizar Evaluacion
    @Override
    public EvaluacionDTO update(EvaluacionDTO evaluacionDTO) {

        Evaluacion evaluacion = evaluacionRepository.findById(evaluacionDTO.getIdEvaluacion())
                .orElseThrow(() -> new EntityNotFoundException());

        evaluacion.setFechaEvaluacion(evaluacionDTO.getFechaEvaluacion());

        // Buscar las instancias correspondientes de Proyecto y Evaluador
        Proyecto proyecto = proyectoRepository.findByIdProyecto(evaluacionDTO.getIdProyecto())
                .orElseThrow(() -> new EntityNotFoundException());
        
        Evaluador evaluador = evaluadorRepository.findByUsuarioRutUsuario(evaluacionDTO.getUsuarioRutUsuario())
                .orElseThrow(() -> new EntityNotFoundException());

        evaluacion.setProyecto(proyecto);
        evaluacion.setEvaluador(evaluador);
               
        return convertToDTO(evaluacionRepository.save(evaluacion));

    }

    // Eliminar Evaluacion por ID
    @Override
    public void delete(String idEvaluacion) {
        if (!evaluacionRepository.existsById(idEvaluacion)) {
            throw new EntityNotFoundException("Evaluación no encontrada");
        }
        evaluacionRepository.deleteById(idEvaluacion);

    }

     // Método para convertir Entidad a DTO
     private EvaluacionDTO convertToDTO(Evaluacion evaluacion) {
        return new EvaluacionDTO(
            evaluacion.getIdEvaluacion(),
            evaluacion.getFechaEvaluacion(),
            evaluacion.getProyecto().getIdProyecto(),
            evaluacion.getEvaluador().getUsuarioRutUsuario()
        
        );
    }

    

    
        

  

    
   
}
    

   
   








   
   








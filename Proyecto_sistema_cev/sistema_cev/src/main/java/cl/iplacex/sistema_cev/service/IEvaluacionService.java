package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import cl.iplacex.sistema_cev.dto.EvaluacionDTO;


public interface IEvaluacionService {

    // Crear Evaluacion
    EvaluacionDTO create(EvaluacionDTO evaluacionDTO);

    // Obtener todas las Evaluaciones
    List<EvaluacionDTO> readAll();

    // Encontrar Evaluacion ID
    Optional<EvaluacionDTO> readById(String idevaluacion);

       // Actualizar Evaluacion
    EvaluacionDTO update(EvaluacionDTO evaluacionDTO);

    // Eliminar Evaluacion ID
    void delete(String idEvaluacion);
}
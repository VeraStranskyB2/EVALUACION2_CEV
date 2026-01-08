package cl.iplacex.sistema_cev.service;

import cl.iplacex.sistema_cev.dto.EvaluadorDTO;
import cl.iplacex.sistema_cev.model.Evaluador;
import java.util.List;
import java.util.Optional;

public interface IEvaluadorService {

    // Crear un evaluador usando un DTO
    Evaluador create(EvaluadorDTO evaluadorDTO);

    // Obtener todos los evaluadores
    List<Evaluador> readAll();

    // Encontrar un evaluador por su RUT
    Optional<Evaluador> findById(String usuarioRutUsuario);

    // Actualizar un evaluador
    Evaluador update(String usuarioRutUsuario, EvaluadorDTO evaluadorDTO);

    // Eliminar un evaluador por su RUT
    void delete(String usuarioRutUsuario);
}
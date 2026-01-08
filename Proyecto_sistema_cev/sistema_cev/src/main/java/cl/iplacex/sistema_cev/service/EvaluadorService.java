package cl.iplacex.sistema_cev.service;

import cl.iplacex.sistema_cev.dto.EvaluadorDTO;
import cl.iplacex.sistema_cev.exception.ResourceNotFoundException;
import cl.iplacex.sistema_cev.model.Evaluador;
import cl.iplacex.sistema_cev.repository.EvaluadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EvaluadorService implements IEvaluadorService {

    @Autowired
    private EvaluadorRepository evaluadorRepository;

    @Override
    public Evaluador create(EvaluadorDTO evaluadorDTO) {
        if (evaluadorDTO == null) {
            throw new IllegalArgumentException("El DTO del evaluador no puede ser nulo.");
        }
        Evaluador evaluador = new Evaluador();
        // Mapear los campos del DTO a la entidad
        evaluador.setNombreEvaluador(evaluadorDTO.getNombreEvaluador());
        evaluador.setApellidos(evaluadorDTO.getApellidos());
        evaluador.setProfesion(evaluadorDTO.getProfesion());
        evaluador.setFechaAcreditacion(LocalDateTime.now());
        // El RUT se setea desde el servicio de autenticación
        
        return evaluadorRepository.save(evaluador);
    }

    @Override
    public List<Evaluador> readAll() {
        return evaluadorRepository.findAll();
    }

    @Override
    public Optional<Evaluador> findById(String usuarioRutUsuario) {
        return evaluadorRepository.findById(usuarioRutUsuario);
    }

    @Override
    public Evaluador update(String usuarioRutUsuario, EvaluadorDTO evaluadorDTO) {
        Evaluador evaluador = evaluadorRepository.findById(usuarioRutUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluador no encontrado con RUT: " + usuarioRutUsuario));

        evaluador.setNombreEvaluador(evaluadorDTO.getNombreEvaluador());
        evaluador.setApellidos(evaluadorDTO.getApellidos());
        evaluador.setProfesion(evaluadorDTO.getProfesion());

        return evaluadorRepository.save(evaluador);
    }

    @Override
    public void delete(String usuarioRutUsuario) {
        if (!evaluadorRepository.existsById(usuarioRutUsuario)) {
            throw new ResourceNotFoundException("Evaluador no encontrado con RUT: " + usuarioRutUsuario);
        }
        evaluadorRepository.deleteById(usuarioRutUsuario);
    }
}
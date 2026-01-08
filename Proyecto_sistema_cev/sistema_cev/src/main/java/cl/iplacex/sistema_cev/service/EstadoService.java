package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.iplacex.sistema_cev.exception.EstadoNotFoundException;

import cl.iplacex.sistema_cev.model.Estado;
import cl.iplacex.sistema_cev.repository.EstadoRepository;

@Service
public class EstadoService implements IEstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    // Crear un nuevo Estado
    @Override
    public Estado create(Estado estado) {
        return estadoRepository.save(estado);
    }

    // Obtener la lista de todos los Estados
    @Override
    public List<Estado> readAll() {
        return estadoRepository.findAll();
    }

    // Obtener un Estado por ID
    @Override
    public Optional<Estado> getEstadoById(Long idEstado) {
        return estadoRepository.findById(idEstado);
    }

    // Actualizar un Estado existente
    @Override
    public Estado update(Estado estado) {
        if (!estadoRepository.existsById(estado.getIdEstado())) {
            throw new EstadoNotFoundException(estado.getIdEstado());
        }
        return estadoRepository.save(estado);
    }

    // Eliminar un Estado por ID
    @Override
    public void delete(Long idEstado) {
        if (!estadoRepository.existsById(idEstado)) {
            throw new EstadoNotFoundException(idEstado.toString());
        }
        estadoRepository.deleteById(idEstado);
    }
}

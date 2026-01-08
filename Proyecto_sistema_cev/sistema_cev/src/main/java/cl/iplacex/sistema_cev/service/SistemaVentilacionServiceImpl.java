package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import cl.iplacex.sistema_cev.model.SistemaVentilacion;
import cl.iplacex.sistema_cev.repository.SistemaVentilacionRepository;

@Service
public class SistemaVentilacionServiceImpl implements ISistemaVentilacionService {

    private final SistemaVentilacionRepository repository;

    // Inyección de dependencias a través del constructor

    public SistemaVentilacionServiceImpl(SistemaVentilacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public SistemaVentilacion create(SistemaVentilacion sistemaVentilacion) {
        return repository.save(sistemaVentilacion);
    }

    @Override
    public List<SistemaVentilacion> readAll() {
        return repository.findAll();
    }

    @Override
    public Optional<SistemaVentilacion> readById(String idSistemaVentilacion) {
        return repository.findById(idSistemaVentilacion);
    }

    @Override
    public SistemaVentilacion update(SistemaVentilacion sistemaVentilacion) {
        // Verifica si el objeto existe antes de actualizar
        if (!repository.existsById(sistemaVentilacion.getIdSistemaVentilacion())) {
            throw new IllegalArgumentException("El sistema de ventilación con ID " + sistemaVentilacion.getIdSistemaVentilacion() + " no existe.");
        }
        return repository.save(sistemaVentilacion);
    }

    @Override
    public void delete(String idSistemaVentilacion) {
        // Verifica si el objeto existe antes de eliminar
        if (!repository.existsById(idSistemaVentilacion)) {
            throw new IllegalArgumentException("El sistema de ventilación con ID " + idSistemaVentilacion + " no existe.");
        }
        repository.deleteById(idSistemaVentilacion);
    }
}

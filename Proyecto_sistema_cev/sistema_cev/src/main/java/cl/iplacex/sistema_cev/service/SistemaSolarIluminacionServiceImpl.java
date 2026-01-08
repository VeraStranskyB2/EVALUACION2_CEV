package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import cl.iplacex.sistema_cev.model.SistemaSolarIluminacion;
import cl.iplacex.sistema_cev.repository.SistemaSolarIluminacionRepository;

@Service
public class SistemaSolarIluminacionServiceImpl implements ISistemaSolarIluminacionService {

    private final SistemaSolarIluminacionRepository repository;

    // Inyección de dependencias a través del constructor
    
    public SistemaSolarIluminacionServiceImpl(SistemaSolarIluminacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public SistemaSolarIluminacion create(SistemaSolarIluminacion sistemaSolarIluminacion) {
        return repository.save(sistemaSolarIluminacion);
    }

    @Override
    public List<SistemaSolarIluminacion> readAll() {
        return repository.findAll();
    }

    @Override
    public Optional<SistemaSolarIluminacion> readById(String idSistemaSolarIluminacion) {
        return repository.findById(idSistemaSolarIluminacion);
    }

    @Override
    public SistemaSolarIluminacion update(SistemaSolarIluminacion sistemaSolarIluminacion) {
        // Verifica si el objeto existe antes de actualizar
        if (!repository.existsById(sistemaSolarIluminacion.getIdSistemaSolarIluminacion())) {
            throw new IllegalArgumentException("El sistema solar de iluminación con ID " + sistemaSolarIluminacion.getIdSistemaSolarIluminacion() + " no existe.");
        }
        return repository.save(sistemaSolarIluminacion);
    }

    @Override
    public void delete(String idSistemaSolarIluminacion) {
        // Verifica si el objeto existe antes de eliminar
        if (!repository.existsById(idSistemaSolarIluminacion)) {
            throw new IllegalArgumentException("El sistema solar de iluminación con ID " + idSistemaSolarIluminacion + " no existe.");
        }
        repository.deleteById(idSistemaSolarIluminacion);
    }
}

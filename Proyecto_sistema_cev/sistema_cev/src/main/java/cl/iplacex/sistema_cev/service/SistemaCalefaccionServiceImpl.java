package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import cl.iplacex.sistema_cev.model.SistemaCalefaccion;
import cl.iplacex.sistema_cev.repository.SistemaCalefaccionRepository;

@Service
public class SistemaCalefaccionServiceImpl implements ISistemaCalefaccionService {

    private final SistemaCalefaccionRepository repository;

    // Inyección de dependencias a través del constructor
  
    public SistemaCalefaccionServiceImpl(SistemaCalefaccionRepository repository) {
        this.repository = repository;
    }

    @Override
    public SistemaCalefaccion create(SistemaCalefaccion sistemaCalefaccion) {
        return repository.save(sistemaCalefaccion);
    }

    @Override
    public List<SistemaCalefaccion> readAll() {
        return repository.findAll();
    }

    @Override
    public Optional<SistemaCalefaccion> readById(String idSistemaCalefaccion) {
        return repository.findById(idSistemaCalefaccion);
    }

    @Override
    public SistemaCalefaccion update(SistemaCalefaccion sistemaCalefaccion) {
        // Verifica si el objeto existe antes de actualizar
        if (!repository.existsById(sistemaCalefaccion.getIdSistemaCalefaccion())) {
            throw new IllegalArgumentException("El sistema de calefacción con ID " + sistemaCalefaccion.getIdSistemaCalefaccion() + " no existe.");
        }
        return repository.save(sistemaCalefaccion);
    }

    @Override
    public void delete(String idSistemaCalefaccion) {
        // Verifica si el objeto existe antes de eliminar
        if (!repository.existsById(idSistemaCalefaccion)) {
            throw new IllegalArgumentException("El sistema de calefacción con ID " + idSistemaCalefaccion + " no existe.");
        }
        repository.deleteById(idSistemaCalefaccion);
    }
}

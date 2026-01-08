package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.iplacex.sistema_cev.model.Direccion;
import cl.iplacex.sistema_cev.repository.DireccionRepository;

@Service
public class DireccionService implements IDireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    // Crear Direccion
    @Override
    public Direccion create(Direccion direccion) {
        if (direccion == null) {
            throw new IllegalArgumentException("La dirección no puede ser nula");
        }
        return direccionRepository.save(direccion);
    }

    // Obtener todas las Direcciones
    @Override
    public List<Direccion> readAll() {
        return direccionRepository.findAll();
    }

    // Encontrar Direccion por ID
    @Override
    public Optional<Direccion> findById(Long idDireccion) {
        return direccionRepository.findById(idDireccion);
    }

    // Actualizar una Direccion
    @Override
    public Direccion update(Direccion direccion) {
        if (direccion == null || direccion.getIdDireccion() == null) {
            throw new IllegalArgumentException("La dirección o su ID no pueden ser nulos");
        }
        return direccionRepository.save(direccion);
    }

    // Eliminar una Direccion por ID
    @Override
    public void delete(Long idDireccion) {
        if (!direccionRepository.existsById(idDireccion)) {
            throw new IllegalArgumentException("No existe una dirección con el ID proporcionado");
        }
        direccionRepository.deleteById(idDireccion);
    }
}
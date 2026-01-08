package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.iplacex.sistema_cev.model.Comuna;
import cl.iplacex.sistema_cev.repository.ComunaRepository;



@Service
public class ComunaService implements IComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    // Crear Comuna
    @Override
    public Comuna create(Comuna comuna) {
        if (comuna == null) {
            throw new IllegalArgumentException("La comuna no puede ser nula");
        }
        return comunaRepository.save(comuna);
    }

    // Obtener todas las Comunas
    @Override
    public List<Comuna> readAll() {
        return comunaRepository.findAll();
    }

    // Encontrar Comuna por ID
    @Override
    public Optional<Comuna> findById(Long idComuna) {
        return comunaRepository.findById(idComuna);
    }

    
    // Actualizar Comuna
    @Override
    public Comuna update(Comuna comuna) {
        if (comuna == null || comuna.getIdComuna() == null) {
            throw new IllegalArgumentException("La comuna o su ID no pueden ser nulos");
        }
        return comunaRepository.save(comuna);
    }

    // Eliminar Comuna por ID
    @Override
    public void delete(Long idComuna) {
        if (!comunaRepository.existsById(idComuna)) {
            throw new IllegalArgumentException("No existe una comuna con el ID proporcionado");
        }
        comunaRepository.deleteById(idComuna);
    }



}

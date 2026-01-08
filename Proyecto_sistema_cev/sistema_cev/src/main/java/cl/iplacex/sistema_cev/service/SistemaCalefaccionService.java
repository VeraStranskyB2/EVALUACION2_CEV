package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.iplacex.sistema_cev.model.SistemaCalefaccion;
import cl.iplacex.sistema_cev.repository.SistemaCalefaccionRepository;

@Service
public class SistemaCalefaccionService implements ISistemaCalefaccionService {

    @Autowired
    private SistemaCalefaccionRepository sistemaCalefaccionRepository;


    // Crear Sistema Calefaccion
    @Override
    public SistemaCalefaccion create(SistemaCalefaccion sistemaCalefaccion) {
        return sistemaCalefaccionRepository.save(sistemaCalefaccion);
    }

    // Obtener todos los indices de Sistema de Calefaccion
    @Override
    public List<SistemaCalefaccion> readAll() {
        return sistemaCalefaccionRepository.findAll();
    }

    // Encontrar Sistema de Calefaccion por ID
    @Override
    public Optional<SistemaCalefaccion> readById(String idSistemaCalefaccion) {
        return sistemaCalefaccionRepository.findById(idSistemaCalefaccion);
    }

    
    // Actualizar Sistema de Calefaccion
    @Override
    public SistemaCalefaccion update(SistemaCalefaccion sistemaCalefaccion) {
        return sistemaCalefaccionRepository.save(sistemaCalefaccion);
    }

    // Eliminar Sistema de Calefaccion por ID
    @Override
    public void delete(String idSistemaCalefaccion) {
        sistemaCalefaccionRepository.deleteById(idSistemaCalefaccion);
    }

}

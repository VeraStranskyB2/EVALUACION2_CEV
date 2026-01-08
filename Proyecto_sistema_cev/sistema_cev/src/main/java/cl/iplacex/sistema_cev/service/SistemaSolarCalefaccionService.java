package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import cl.iplacex.sistema_cev.model.SistemaSolarCalefaccion;
import cl.iplacex.sistema_cev.repository.SistemaSolarCalefaccionRepository;



public class SistemaSolarCalefaccionService implements ISistemaSolarCalefaccionService {


    @Autowired
    private SistemaSolarCalefaccionRepository sistemaSolarCalefaccionRepository;


    // Crear Sistema Solar Calefaccion
    @Override
    public SistemaSolarCalefaccion create(SistemaSolarCalefaccion sistemaSolarCalefaccion) {
        return sistemaSolarCalefaccionRepository.save(sistemaSolarCalefaccion);
    }

    // Obtener todos los indices de Sistema Solar de Calefaccion
    @Override
    public List<SistemaSolarCalefaccion> readAll() {
        return sistemaSolarCalefaccionRepository.findAll();
    }

    // Encontrar Sistema Solar de Calefaccion por ID
    @Override
    public Optional<SistemaSolarCalefaccion> readById(String idSistemaSolarCalefaccion) {
        return sistemaSolarCalefaccionRepository.findById(idSistemaSolarCalefaccion);
    }

    
    // Actualizar Sistema Solar de Calefaccion
    @Override
    public SistemaSolarCalefaccion update(SistemaSolarCalefaccion sistemaSolarCalefaccion) {
        return sistemaSolarCalefaccionRepository.save(sistemaSolarCalefaccion);
    }

    // Eliminar Sistema Solar de Calefaccion por ID
    @Override
    public void delete(String idSistemaSolarCalefaccion) {
        sistemaSolarCalefaccionRepository.deleteById(idSistemaSolarCalefaccion);
    }

}

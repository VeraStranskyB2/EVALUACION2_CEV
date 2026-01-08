package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import cl.iplacex.sistema_cev.model.SistemaSolarIluminacion;
import cl.iplacex.sistema_cev.repository.SistemaSolarIluminacionRepository;



public class SistemaSolarIluminacionService implements ISistemaSolarIluminacionService {

    @Autowired
    private SistemaSolarIluminacionRepository sistemaSolarIluminacionRepository;


    // Crear Sistema Solar Iluminacion
    @Override
    public SistemaSolarIluminacion create(SistemaSolarIluminacion sistemaSolarIluminacion) {
        return sistemaSolarIluminacionRepository.save(sistemaSolarIluminacion);
    }

    // Obtener todos los indices de Sistema Solar de Iluminacion
    @Override
    public List<SistemaSolarIluminacion> readAll() {
        return sistemaSolarIluminacionRepository.findAll();
    }

    // Encontrar Sistema Solar de Iluminacion por ID
    @Override
    public Optional<SistemaSolarIluminacion> readById(String idSistemaSolarIluminacion) {
        return sistemaSolarIluminacionRepository.findById(idSistemaSolarIluminacion);
    }

    
    // Actualizar Sistema Solar de Iluminacion
    @Override
    public SistemaSolarIluminacion update(SistemaSolarIluminacion sistemaSolarIluminacion) {
        return sistemaSolarIluminacionRepository.save(sistemaSolarIluminacion);
    }

    // Eliminar Sistema Solar de Iluminacion por ID
    @Override
    public void delete(String idSistemaSolarIluminacion) {
        sistemaSolarIluminacionRepository.deleteById(idSistemaSolarIluminacion);
    }


}

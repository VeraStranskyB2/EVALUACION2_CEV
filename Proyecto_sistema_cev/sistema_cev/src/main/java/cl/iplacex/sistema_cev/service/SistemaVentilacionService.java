package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import cl.iplacex.sistema_cev.model.SistemaVentilacion;
import cl.iplacex.sistema_cev.repository.SistemaVentilacionRepository;





public class SistemaVentilacionService implements ISistemaVentilacionService  {

    @Autowired
    private SistemaVentilacionRepository sistemaVentilacionRepository;


    // Crear Sistema Ventilacion
    @Override
    public SistemaVentilacion create(SistemaVentilacion sistemaVentilacion) {
        return sistemaVentilacionRepository.save(sistemaVentilacion);
    }

    // Obtener todos los indices de Sistema Ventilacion
    @Override
    public List<SistemaVentilacion> readAll() {
        return sistemaVentilacionRepository.findAll();
    }

    // Encontrar Sistema Ventilacion por ID
    @Override
    public Optional<SistemaVentilacion> readById(String idSistemaVentilacion) {
        return sistemaVentilacionRepository.findById(idSistemaVentilacion);
    }

    
    // Actualizar Sistema Ventilacion
    @Override
    public SistemaVentilacion update(SistemaVentilacion sistemaVentilacion) {
        return sistemaVentilacionRepository.save(sistemaVentilacion);
    }

    // Eliminar Sistema Ventilacion por ID
    @Override
    public void delete(String idSistemaVentilacion) {
        sistemaVentilacionRepository.deleteById(idSistemaVentilacion);
    }

}

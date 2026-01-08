package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import cl.iplacex.sistema_cev.model.SistemaVentilacion;


public interface ISistemaVentilacionService {

    // Crear Sistema de Ventilacion
    SistemaVentilacion create(SistemaVentilacion sistemaVentilacion);

    // Obtener Sistema de Ventilacion
    List<SistemaVentilacion> readAll();

    // Encontrar Sistema de Ventilacion por ID
    Optional<SistemaVentilacion> readById(String idSistemaVentilacion);

    // Actualizar Sistema de Ventilacion
    SistemaVentilacion update(SistemaVentilacion sistemaVentilacion);

    // Eliminar Sistema de Ventilacion por ID
    void delete(String idSistemaVentilacion);

}

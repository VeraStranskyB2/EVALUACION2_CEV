package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import cl.iplacex.sistema_cev.model.SistemaSolarIluminacion;


public interface ISistemaSolarIluminacionService {

    // Crear Sistema Solar Iluminacion
    SistemaSolarIluminacion create(SistemaSolarIluminacion sistemaSolarIluminacion);

    // Obtener Sistema Solar Iluminacion
    List<SistemaSolarIluminacion> readAll();

    // Encontrar Sistema Solar Iluminacion por ID
    Optional<SistemaSolarIluminacion> readById(String idSistemaSolarIluminacion);

    // Actualizar Sistema Solar Iluminacion
    SistemaSolarIluminacion update(SistemaSolarIluminacion sistemaSolarIluminacion);

    // Eliminar Sistema Solar Iluminacion por ID
    void delete(String idSistemaSolarIluminacion);
}

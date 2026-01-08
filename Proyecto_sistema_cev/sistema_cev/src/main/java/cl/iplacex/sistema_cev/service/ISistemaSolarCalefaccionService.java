package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import cl.iplacex.sistema_cev.model.SistemaSolarCalefaccion;

public interface ISistemaSolarCalefaccionService {

    // Crear Sistema Solar Calefaccion
    SistemaSolarCalefaccion create(SistemaSolarCalefaccion sistemaSolarCalefaccion);

    // Obtener Sistema Solar Calefaccion
    List<SistemaSolarCalefaccion> readAll();

    // Encontrar Sistema Solar Calefaccion por ID
    Optional<SistemaSolarCalefaccion> readById(String idSistemaSolarCalefaccion);

    // Actualizar Sistema Solar Calefaccion
    SistemaSolarCalefaccion update(SistemaSolarCalefaccion sistemaSolarCalefaccion);

    // Eliminar Sistema Solar Calefaccion por ID
    void delete(String idSistemaSolarCalefaccion);
 

}
package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import cl.iplacex.sistema_cev.model.SistemaCalefaccion;


public interface ISistemaCalefaccionService {

    // Crear Sistema Calefaccion
    SistemaCalefaccion create(SistemaCalefaccion sistemaCalefaccion);

    // Obtener Sistema Calefaccion
    List<SistemaCalefaccion> readAll();

    // Encontrar Sistema Calefaccion por ID
    Optional<SistemaCalefaccion> readById(String idSistemaCalefaccion);

    // Actualizar Sistema Calefaccion
    SistemaCalefaccion update(SistemaCalefaccion sistemaCalefaccion);

    // Eliminar Sistema Calefaccion por ID
    void delete(String idSistemaCalefaccion);


}

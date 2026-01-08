package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import cl.iplacex.sistema_cev.model.Comuna;



public interface IComunaService {

    // Crear Comuna
    Comuna create(Comuna comuna);

    // Obtener Comuna
    List<Comuna> readAll();

    // Encontrar Comuna por ID
    Optional<Comuna> findById(Long idComuna);

    // Actualizar una Comuna
    Comuna update(Comuna comuna);

    // Eliminar una Comuna por ID
    void delete(Long idComuna);

}

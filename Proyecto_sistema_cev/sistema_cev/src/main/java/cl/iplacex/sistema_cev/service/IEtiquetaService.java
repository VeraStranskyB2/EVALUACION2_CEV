package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import cl.iplacex.sistema_cev.model.Etiqueta;

public interface IEtiquetaService {

    // Obtener Etiqueta
    List<Etiqueta> findAll();

    // Encontrar Etiqueta por ID
    Optional<Etiqueta> findById(String idEtiqueta);

    // Guardar una nueva Etiqueta
    Etiqueta save(Etiqueta etiqueta);

    // Actualizar una Etiqueta
    Etiqueta update(String idEtiqueta, Etiqueta etiqueta);

    // Eliminar una Etiqueta por ID
    void delete(String idEtiqueta);

}

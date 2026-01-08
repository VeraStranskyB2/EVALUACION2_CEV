package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import cl.iplacex.sistema_cev.model.Region;



public interface IRegionService {

    // Crear una Region
    Region create(Region region);

    // Obtener todas las Regiones
    List<Region> readAll();

    // Encontrar Region por ID
    Optional<Region> readById(Long idRegion);

    // Actualizar una Region
    Region update(Region region);

    // Eliminar una Region por ID
    void delete(Long idRegion);


}

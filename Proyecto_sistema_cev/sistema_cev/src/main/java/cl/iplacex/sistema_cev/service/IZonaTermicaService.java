package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import cl.iplacex.sistema_cev.model.ZonaTermica;



public interface IZonaTermicaService {

    // Crear Zona Termica
    ZonaTermica create(ZonaTermica zonaTermica);

    // Obtener todas las Zonas Termicas
    List<ZonaTermica> readAll();

    // Encontrar Zona Termica por ID
    Optional<ZonaTermica> readById(String idZonaTermica);

    // Actualizar Zona Termica
    ZonaTermica update(ZonaTermica zonaTermica);

    // Eliminar Zona Termica por ID
    void delete(String idZonaTermica);

}

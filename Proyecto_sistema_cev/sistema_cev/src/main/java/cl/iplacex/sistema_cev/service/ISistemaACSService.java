package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import cl.iplacex.sistema_cev.model.SistemaACS;


public interface ISistemaACSService {

    // Crear Sistema ACS
    SistemaACS create(SistemaACS sistemaACS);

    // Obtener Sistema ACS
    List<SistemaACS> readAll();

    // Encontrar Sistema ACS por ID
    Optional<SistemaACS> readById(String idSistemaACS);

    // Actualizar Sistema ACS
    SistemaACS update(SistemaACS sistemaACS);

    // Eliminar Sistema ACS por ID
    void delete(String idSistemaACS);

   
}

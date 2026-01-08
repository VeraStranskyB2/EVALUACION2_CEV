package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import cl.iplacex.sistema_cev.model.Documento;

public interface IDocumentoService {

    // Crear Documento
    Documento create(Documento documento);

    // Obtener Documento
    List<Documento> readAll();

    // Encontrar Documento por ID
    Optional<Documento> readById(String idDocumento);

    // Actualizar un Documento
    Documento update(Documento documento);

    // Eliminar un documento por ID
    void delete(String idDocumento);

}

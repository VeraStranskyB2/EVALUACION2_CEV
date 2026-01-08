package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import cl.iplacex.sistema_cev.dto.InformeDTO;


public interface IInformeService {

    // Crear Informe
    InformeDTO create(InformeDTO informeDTO);

    // Crear Lista de Informes
    List<InformeDTO> readAll();

    // Encontrar Informe por ID
    Optional<InformeDTO> findById(String idInforme);

    // Actualizar Informe
    InformeDTO update(InformeDTO informeDTO);

    // Eliminar Informe por ID
    void delete(String idInforme);
}

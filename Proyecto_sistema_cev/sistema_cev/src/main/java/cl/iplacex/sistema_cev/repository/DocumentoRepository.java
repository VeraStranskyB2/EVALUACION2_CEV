package cl.iplacex.sistema_cev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.iplacex.sistema_cev.model.Documento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, String> {

    // Busca un Documento por el ID de la Solicitud
    List<Documento> findBySolicitud_IdSolicitud(String idSolicitud);
  

   
}

package cl.iplacex.sistema_cev.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.iplacex.sistema_cev.model.Solicitud;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, String> {
    
    // Busca una Solicitud por el Id del Estadio//
    List<Solicitud> findByEstado_IdEstado(Long idEstado);

    


}

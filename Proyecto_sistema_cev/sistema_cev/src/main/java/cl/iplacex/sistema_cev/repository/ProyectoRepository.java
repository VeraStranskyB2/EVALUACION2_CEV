package cl.iplacex.sistema_cev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.iplacex.sistema_cev.model.Proyecto;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, String> {

    Optional<Proyecto> findByRolMatriz(String rolMatriz);

    Optional<Proyecto> findByIdProyecto(String idProyecto);

   

}

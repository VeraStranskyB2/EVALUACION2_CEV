package cl.iplacex.sistema_cev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.iplacex.sistema_cev.model.SistemaCalefaccion;

@Repository
public interface SistemaCalefaccionRepository extends JpaRepository<SistemaCalefaccion, String> {

}

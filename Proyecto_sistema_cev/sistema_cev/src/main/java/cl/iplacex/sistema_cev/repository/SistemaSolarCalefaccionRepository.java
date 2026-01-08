package cl.iplacex.sistema_cev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.iplacex.sistema_cev.model.SistemaSolarCalefaccion;

@Repository
public interface SistemaSolarCalefaccionRepository extends JpaRepository<SistemaSolarCalefaccion, String> {

}

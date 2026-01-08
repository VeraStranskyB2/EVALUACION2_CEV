package cl.iplacex.sistema_cev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.iplacex.sistema_cev.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {

    public Optional<Rol> findByNombre(String nombre);

   

}

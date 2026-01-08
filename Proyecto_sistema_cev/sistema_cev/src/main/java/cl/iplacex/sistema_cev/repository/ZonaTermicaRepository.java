package cl.iplacex.sistema_cev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.iplacex.sistema_cev.model.ZonaTermica;

@Repository
//Esta interfaz hereda métodos CRUD de JpaRepository para la entidad ZonaTermica
public interface ZonaTermicaRepository extends JpaRepository<ZonaTermica, String> {
   
}

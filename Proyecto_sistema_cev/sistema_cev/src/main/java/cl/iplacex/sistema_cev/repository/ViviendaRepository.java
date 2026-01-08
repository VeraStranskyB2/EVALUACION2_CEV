package cl.iplacex.sistema_cev.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import cl.iplacex.sistema_cev.model.Vivienda;

@Repository
public interface ViviendaRepository extends JpaRepository<Vivienda, String> {
    // Busca una vivienda por Rol//
    Optional<Vivienda> findByRolVivienda(String rolVivienda);
    
    // Elimina una vivienda por Rol
    void deleteByRolVivienda(String rolVivienda);

    

}

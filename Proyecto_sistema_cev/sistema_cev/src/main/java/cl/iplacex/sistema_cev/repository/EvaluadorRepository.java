package cl.iplacex.sistema_cev.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.iplacex.sistema_cev.model.Evaluador;

@Repository
public interface EvaluadorRepository extends JpaRepository<Evaluador, String> {
    
    public Optional<Evaluador> findByUsuarioRutUsuario(String rutUsuario);

    void deleteByUsuarioRutUsuario(String usuarioRutUsuario);


}

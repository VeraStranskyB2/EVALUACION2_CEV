package cl.iplacex.sistema_cev.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import cl.iplacex.sistema_cev.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByRutUsuario(String rutUsuario);

    void deleteByRutUsuario(String rutUsuario);

    boolean existsByRutUsuario(String rutUsuario);

    Optional<Usuario> findByRutUsuarioOrEmail(String rutUsuario, String email);

    boolean existsByEmail(String email);

   
    

}

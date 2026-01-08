package cl.iplacex.sistema_cev.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.iplacex.sistema_cev.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {

    
    List<Cliente> readByRazonSocial(String razonSocial);

    
    void deleteByUsuarioRutUsuario(String usuarioRutUsuario);

   
    public Optional<Cliente> findByUsuarioRutUsuario(String rutUsuario);

    
    List<Cliente> findByRazonSocialContainingIgnoreCase(String razonSocial);
}

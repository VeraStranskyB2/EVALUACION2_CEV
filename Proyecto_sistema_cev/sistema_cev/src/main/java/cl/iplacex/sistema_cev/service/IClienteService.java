package cl.iplacex.sistema_cev.service;

import cl.iplacex.sistema_cev.dto.ClienteDTO;
import java.util.List;
import java.util.Optional;

/* Interfaz para el servicio de gestión de clientes. */
public interface IClienteService {

    // Crea un nuevo cliente.  
    ClienteDTO create(ClienteDTO clienteDTO);

    //Obtener una lista de todos los clientes.    
    List<ClienteDTO> readAll();

    // Busca clientes por razón social.    
    List<ClienteDTO> readByRazonSocial(String razonSocial);

    // Busca un cliente por su RUT.   
    Optional<ClienteDTO> findById(String usuarioRutUsuario);

    // Actualiza un cliente existente.   
    ClienteDTO update(String usuarioRutUsuario, ClienteDTO clienteDTO);

    // Elimina un cliente por su RUT.   
    void delete(String usuarioRutUsuario);
}

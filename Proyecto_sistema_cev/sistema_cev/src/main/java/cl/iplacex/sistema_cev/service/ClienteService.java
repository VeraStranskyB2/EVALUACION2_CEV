package cl.iplacex.sistema_cev.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.iplacex.sistema_cev.dto.ClienteDTO;
import cl.iplacex.sistema_cev.exception.ResourceNotFoundException;
import cl.iplacex.sistema_cev.model.Cliente;
import cl.iplacex.sistema_cev.repository.ClienteRepository;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Método auxiliar para convertir una entidad Cliente a un DTO.
    private ClienteDTO convertToDTO(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        ClienteDTO dto = new ClienteDTO();
        dto.setUsuarioRutUsuario(cliente.getUsuarioRutUsuario());
        dto.setRazonSocial(cliente.getRazonSocial());
        dto.setRepresentanteLegal(cliente.getRepresentanteLegal());
        dto.setGiroComercial(cliente.getGiroComercial());
        return dto;
    }

    @Override
    public ClienteDTO create(ClienteDTO clienteDTO) {
        if (clienteDTO == null || clienteDTO.getUsuarioRutUsuario() == null) {
            throw new IllegalArgumentException("El DTO del cliente y su RUT no pueden ser nulos.");
        }
        
        // Conversión de DTO a la entidad Cliente
        Cliente cliente = new Cliente();
        cliente.setUsuarioRutUsuario(clienteDTO.getUsuarioRutUsuario());
        cliente.setRazonSocial(clienteDTO.getRazonSocial());
        cliente.setRepresentanteLegal(clienteDTO.getRepresentanteLegal());
        cliente.setGiroComercial(clienteDTO.getGiroComercial());
        cliente.setFechaRegistro(LocalDateTime.now());
        
        Cliente clienteGuardado = clienteRepository.save(cliente);
        return convertToDTO(clienteGuardado);
    }

    @Override
    public List<ClienteDTO> readAll() {
        // Obtiene una lista de entidades Cliente del repositorio y luego las convierte a DTOs
        return clienteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ClienteDTO> readByRazonSocial(String razonSocial) {
        // Obtiene la lista de entidades Cliente del repositorio y las convierte a DTOs
        return clienteRepository.findByRazonSocialContainingIgnoreCase(razonSocial).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ClienteDTO> findById(String usuarioRutUsuario) {
        // Busca la entidad por su ID y, si existe, la convierte a un DTO
        return clienteRepository.findById(usuarioRutUsuario)
                .map(this::convertToDTO);
    }

    @Override
    public ClienteDTO update(String usuarioRutUsuario, ClienteDTO clienteDTO) {
        // Busca la entidad existente o lanza una excepción si no la encuentra
        Cliente cliente = clienteRepository.findById(usuarioRutUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con RUT: " + usuarioRutUsuario));

        // Actualiza los datos de la entidad con la información del DTO
        cliente.setRazonSocial(clienteDTO.getRazonSocial());
        cliente.setRepresentanteLegal(clienteDTO.getRepresentanteLegal());
        cliente.setGiroComercial(clienteDTO.getGiroComercial());

        Cliente clienteActualizado = clienteRepository.save(cliente);
        return convertToDTO(clienteActualizado);
    }

    @Override
    public void delete(String usuarioRutUsuario) {
        // Verifica que el cliente exista antes de intentar eliminarlo
        if (!clienteRepository.existsById(usuarioRutUsuario)) {
            throw new ResourceNotFoundException("Cliente no encontrado con RUT: " + usuarioRutUsuario);
        }
        clienteRepository.deleteById(usuarioRutUsuario);
    }
}
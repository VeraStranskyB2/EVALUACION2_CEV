package cl.iplacex.sistema_cev.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.iplacex.sistema_cev.dto.ClienteDTO;
import cl.iplacex.sistema_cev.service.IClienteService;

/**
 * Controlador para la gestión de clientes.
 * Proporciona endpoints para operaciones CRUD de clientes.
 */
@RestController
@RequestMapping("api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {

    private final IClienteService clienteService;

    // Inyección de dependencias por constructor, una práctica recomendada
    public ClienteController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Crear un nuevo Cliente (ahora para ADMIN y CLIENTE).
    @PreAuthorize("hasRole('ADMIN') or hasRole('CLIENTE')")
    @PostMapping
    public ResponseEntity<ClienteDTO> create(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO createdCliente = clienteService.create(clienteDTO);
        return new ResponseEntity<>(createdCliente, HttpStatus.CREATED);
    }

    // Obtener todos los Clientes (solo para ADMIN).
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> readAll() {
        List<ClienteDTO> clientes = clienteService.readAll();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    // Obtener Cliente por Razón Social (para ADMIN y EVALUADOR).
    @PreAuthorize("hasRole('ADMIN') or hasRole('EVALUADOR')")
    @GetMapping("/razon_social/{razonSocial}")
    public ResponseEntity<List<ClienteDTO>> readByRazonSocial(@PathVariable String razonSocial) {
        List<ClienteDTO> clientes = clienteService.readByRazonSocial(razonSocial);
        return ResponseEntity.ok(clientes);
    }

    // Obtener Cliente por RUT (Para ADMIN, CLIENTE y EVALUADOR).
    @PreAuthorize("hasRole('ADMIN') or hasRole('EVALUADOR') or (hasRole('CLIENTE') and #usuarioRutUsuario == authentication.principal.rutUsuario)")
    @GetMapping("/{usuarioRutUsuario}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable String usuarioRutUsuario) {
        Optional<ClienteDTO> clienteOpt = clienteService.findById(usuarioRutUsuario);
        if (clienteOpt.isPresent()) {
            return ResponseEntity.ok(clienteOpt.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Actualizar Cliente (ADMIN puede actualizar cualquiera, CLIENTE solo el suyo).
    @PreAuthorize("hasRole('ADMIN') or (hasRole('CLIENTE') and #usuarioRutUsuario == authentication.principal.rutUsuario)")
    @PutMapping("/{usuarioRutUsuario}")
    public ResponseEntity<ClienteDTO> update(@PathVariable String usuarioRutUsuario, @RequestBody ClienteDTO clienteDTO) {
        ClienteDTO updatedCliente = clienteService.update(usuarioRutUsuario, clienteDTO);
        return ResponseEntity.ok(updatedCliente);
    }

    // Eliminar Cliente por Rut (solo para ADMIN).
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{usuarioRutUsuario}")
    public ResponseEntity<Void> delete(@PathVariable String usuarioRutUsuario) {
        clienteService.delete(usuarioRutUsuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

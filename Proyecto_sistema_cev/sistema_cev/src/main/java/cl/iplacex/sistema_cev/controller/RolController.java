package cl.iplacex.sistema_cev.controller;
import cl.iplacex.sistema_cev.dto.RolDTO;
import cl.iplacex.sistema_cev.model.Rol;
import cl.iplacex.sistema_cev.service.IRolService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles") // Ruta base con un prefijo de API
@CrossOrigin(origins = "*")
public class RolController {

    private final IRolService rolService;

    // Inyección de dependencias a través del constructor (mejor práctica)
    public RolController(IRolService rolService) {
        this.rolService = rolService;
    }

    // Crear un nuevo Rol
    @PostMapping
    public ResponseEntity<Rol> create(@Valid @RequestBody RolDTO rolDTO) {
        // Mapea el DTO a la entidad antes de llamar al servicio
        Rol newRol = new Rol(rolDTO.getNombre());
        Rol createdRol = rolService.create(newRol);
        return new ResponseEntity<>(createdRol, HttpStatus.CREATED);
    }

    // Obtener todos los Roles
    @GetMapping
    public ResponseEntity<List<Rol>> readAll() {
        List<Rol> roles = rolService.readAll();
        return ResponseEntity.ok(roles); // Uso de atajo para HttpStatus.OK
    }

    // Obtener Rol por ID
    @GetMapping("/{id}")
    public ResponseEntity<Rol> readById(@PathVariable Long id) {
        Optional<Rol> rol = rolService.findById(id);
        // Usa la sintaxis simplificada para manejar el Optional
        return rol.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }

    // Actualizar Rol por ID
    @PutMapping("/{id}")
    public ResponseEntity<Rol> update(@PathVariable Long id, @Valid @RequestBody RolDTO rolDTO) {
        Rol rolToUpdate = new Rol();
        rolToUpdate.setIdRol(id);
        rolToUpdate.setNombre(rolDTO.getNombre());
        
        Rol updatedRol = rolService.update(rolToUpdate);
        return ResponseEntity.ok(updatedRol);
    }

    // Eliminar Rol por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rolService.delete(id);
        return ResponseEntity.noContent().build();
        // Retorna 204 No Content
    }
}
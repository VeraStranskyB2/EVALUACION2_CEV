package cl.iplacex.sistema_cev.controller;

import cl.iplacex.sistema_cev.model.Etiqueta;
import cl.iplacex.sistema_cev.service.IEtiquetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/etiquetas")
@CrossOrigin(origins = "*")
// Permitir solicitudes desde cualquier origen
public class EtiquetaController {

    @Autowired
    private IEtiquetaService etiquetaService;

    // Obtener todas las etiquetas
    @GetMapping
    public ResponseEntity<List<Etiqueta>> getAllEtiquetas() {
        List<Etiqueta> etiquetas = etiquetaService.findAll();
        return new ResponseEntity<>(etiquetas, HttpStatus.OK);
    }

    // Obtener etiqueta por ID
    @GetMapping("/{idEtiqueta}")
    public ResponseEntity<Etiqueta> getEtiquetaById(@PathVariable String idEtiqueta) {
        Optional<Etiqueta> etiqueta = etiquetaService.findById(idEtiqueta);
        return etiqueta.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear una etiqueta
    @PostMapping
    public ResponseEntity<Etiqueta> createEtiqueta(@RequestBody Etiqueta etiqueta) {
        Etiqueta savedEtiqueta = etiquetaService.save(etiqueta);
        return new ResponseEntity<>(savedEtiqueta, HttpStatus.CREATED);
    }

    // Actualizar una etiqueta
    @PutMapping("/{idEtiqueta}")
    public ResponseEntity<Etiqueta> update(@PathVariable String idEtiqueta, @RequestBody Etiqueta etiqueta) {
        Etiqueta updatedEtiqueta = etiquetaService.update(idEtiqueta, etiqueta);
        return new ResponseEntity<>(updatedEtiqueta, HttpStatus.OK);
    }

    // Eliminar etiqueta por ID
    @DeleteMapping("/{idEtiqueta}")
    public ResponseEntity<Void> delete(@PathVariable String idEtiqueta) {
        etiquetaService.delete(idEtiqueta);
        return ResponseEntity.noContent().build();
    }
}
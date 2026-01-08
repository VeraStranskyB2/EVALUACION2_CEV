package cl.iplacex.sistema_cev.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.iplacex.sistema_cev.model.Direccion;
import cl.iplacex.sistema_cev.service.IDireccionService;

@RestController
@RequestMapping("/direcciones")
@CrossOrigin(origins = "*")
public class DireccionController {

    @Autowired
    private IDireccionService direccionService;

    // Crear una nueva Direccion
    @PostMapping
    public ResponseEntity<Direccion> create(@RequestBody Direccion direccion) {
        Direccion createdDireccion = direccionService.create(direccion);
        return new ResponseEntity<>(createdDireccion, HttpStatus.CREATED);
    }

    // Obtener todas las Direcciones
    @GetMapping
    public ResponseEntity<List<Direccion>> readAll() {
        List<Direccion> direcciones = direccionService.readAll();
        return new ResponseEntity<>(direcciones, HttpStatus.OK);
    }

    // Obtener Direccion por ID
    @GetMapping("/{idDireccion}")
    public ResponseEntity<Direccion> findById(@PathVariable Long idDireccion) {
        Optional<Direccion> direccionOptional = direccionService.findById(idDireccion);
        return direccionOptional.map(direccion -> new ResponseEntity<>(direccion, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar una Direccion
    @PutMapping("/{idDireccion}")
    public ResponseEntity<Direccion> update(@PathVariable Long idDireccion, @RequestBody Direccion direccion) {
        direccion.setIdDireccion(idDireccion);
        Direccion updatedDireccion = direccionService.update(direccion);
        return new ResponseEntity<>(updatedDireccion, HttpStatus.OK);
    }

    // Eliminar Direccion por ID
    @DeleteMapping("/{idDireccion}")
    public ResponseEntity<Void> delete(@PathVariable Long idDireccion) {
        direccionService.delete(idDireccion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
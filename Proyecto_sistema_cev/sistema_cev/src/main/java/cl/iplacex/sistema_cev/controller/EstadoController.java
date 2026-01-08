package cl.iplacex.sistema_cev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.iplacex.sistema_cev.model.Estado;
import cl.iplacex.sistema_cev.service.IEstadoService;

@RestController
@RequestMapping("/estados")
//Permite solicitudes de cualquier origen
@CrossOrigin(origins = "*")

public class EstadoController {

    @Autowired
    private IEstadoService estadoService;

    // Crear una nuevo Estado
    @PostMapping
    public ResponseEntity<Estado> create(@RequestBody Estado estado) {
        Estado createdEstado = estadoService.create(estado);
        return new ResponseEntity<>(createdEstado, HttpStatus.CREATED);
    }

    // Obtener todos los Estados
    @GetMapping
    public ResponseEntity<List<Estado>> readAll() {
        List<Estado> estados = estadoService.readAll();
        return new ResponseEntity<>(estados, HttpStatus.OK);
    }

    // Obtener Estado por ID
    @GetMapping("/{idIEstado}")
    public ResponseEntity<Estado> readById(@PathVariable Long idEstado) {
        return estadoService.getEstadoById(idEstado)
                .map(estado -> new ResponseEntity<>(estado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar Estado
    @PutMapping("/{idEstado}")
    public ResponseEntity<Estado> update(@PathVariable Long idEstado, @RequestBody Estado estado) {
        estado.setIdEstado(idEstado);
        Estado updatedEstado = estadoService.update(estado);
        return new ResponseEntity<>(updatedEstado, HttpStatus.OK);
    }

    // Eliminar Estado por ID
    @DeleteMapping("/{idEstado}")
    public ResponseEntity<Void> delete(@PathVariable Long idEstado) {
        estadoService.delete(idEstado);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
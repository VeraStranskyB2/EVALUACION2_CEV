package cl.iplacex.sistema_cev.controller;

import java.util.List;

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




import cl.iplacex.sistema_cev.dto.ProyectoDTO;

import cl.iplacex.sistema_cev.service.IProyectoService;

@RestController
@RequestMapping("/proyectos")
@CrossOrigin(origins = "*")
public class ProyectoController {

    @Autowired
    private IProyectoService proyectoService;

    // Crear un nuevo Proyecto
    @PostMapping
    public ResponseEntity<ProyectoDTO> create(@RequestBody ProyectoDTO proyectoDTO) {
        ProyectoDTO createdProyecto = proyectoService.create(proyectoDTO);
        return new ResponseEntity<>(createdProyecto, HttpStatus.CREATED);
    }

    // Obtener todos los Proyectos
    @GetMapping
    public ResponseEntity<List<ProyectoDTO>> readAll() {
        List<ProyectoDTO> proyectos = proyectoService.readAll();
        return new ResponseEntity<>(proyectos, HttpStatus.OK);
    }

    // Obtener Proyecto por ID
    @GetMapping("/{idProyecto}")
    public ResponseEntity<ProyectoDTO> readById(@PathVariable String idProyecto) {
        return proyectoService.readById(idProyecto)
        .map(proyecto -> new ResponseEntity<>(proyecto, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar Proyecto
    @PutMapping("/{idProyecto}")
    public ResponseEntity<ProyectoDTO> update(@PathVariable String idProyecto, @RequestBody ProyectoDTO proyectoDTO) {
        proyectoDTO.setIdProyecto(idProyecto);
        ProyectoDTO updatedProyecto = proyectoService.update(proyectoDTO);
        return new ResponseEntity<>(updatedProyecto, HttpStatus.OK);
    }

    // Eliminar Proyecto por ID
    @DeleteMapping("/{idProyecto}")
    public ResponseEntity<Void> delete(@PathVariable String idProyecto) {
        proyectoService.delete(idProyecto);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
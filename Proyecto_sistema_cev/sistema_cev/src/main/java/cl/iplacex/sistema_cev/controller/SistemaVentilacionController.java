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

import cl.iplacex.sistema_cev.model.SistemaVentilacion;
import cl.iplacex.sistema_cev.service.ISistemaVentilacionService;


@RestController
@RequestMapping("/sistemasVentilacion")
@CrossOrigin(origins = "*")
public class SistemaVentilacionController {

    @Autowired
    private ISistemaVentilacionService sistemaVentilacionService;

    // Crear un nuevo Sistema de Ventilacion
    @PostMapping
    public ResponseEntity<SistemaVentilacion> create(@RequestBody SistemaVentilacion sistemaVentilacion) {
        SistemaVentilacion createdSistemaVentilacion = sistemaVentilacionService.create(sistemaVentilacion);
        return new ResponseEntity<>(createdSistemaVentilacion, HttpStatus.CREATED);
    }

    // Obtener todos los Sistemas de Ventilacion
    @GetMapping
    public ResponseEntity<List<SistemaVentilacion>> readAll() {
        List<SistemaVentilacion> sistemaVentilacion = sistemaVentilacionService.readAll();
        return new ResponseEntity<>(sistemaVentilacion, HttpStatus.OK);
    }

    // Obtener un Sistema de Ventilacion por ID
    @GetMapping("/{idSistemaVentilacion}")
    public ResponseEntity<SistemaVentilacion> readById(@PathVariable String idSistemaVentilacion) {
        return sistemaVentilacionService.readById(idSistemaVentilacion)
        .map(sistemaVentilacion -> new ResponseEntity<>(sistemaVentilacion, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    // Actualizar un Sistema de Ventilacion
    @PutMapping("/{idSistemaVentilacion}")
    public ResponseEntity<SistemaVentilacion> update(@PathVariable String idSistemaVentilacion, @RequestBody SistemaVentilacion sistemaVentilacion) {
        sistemaVentilacion.setIdSistemaVentilacion(idSistemaVentilacion);
        SistemaVentilacion updatedSistemaVentilacion = sistemaVentilacionService.update(sistemaVentilacion);
        return new ResponseEntity<>(updatedSistemaVentilacion, HttpStatus.OK);
    }


    // Eliminar un Sistema de Ventilacion por ID
    @DeleteMapping("/{idSistemaVentilacion}")
    public ResponseEntity<Void> delete(@PathVariable String idSistemaVentilacion) {
        sistemaVentilacionService.delete(idSistemaVentilacion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

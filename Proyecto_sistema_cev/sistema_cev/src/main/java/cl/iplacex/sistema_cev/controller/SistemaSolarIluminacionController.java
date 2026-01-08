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


import cl.iplacex.sistema_cev.model.SistemaSolarIluminacion;
import cl.iplacex.sistema_cev.service.ISistemaSolarIluminacionService;



@RestController
@RequestMapping("/sistemassolarIluminacion")
@CrossOrigin(origins = "*")
public class SistemaSolarIluminacionController {

    @Autowired
    private ISistemaSolarIluminacionService sistemaSolarIluminacionService;

    // Crear un nuevo Sistema Solar de Iluminacion
    @PostMapping
    public ResponseEntity<SistemaSolarIluminacion> create(@RequestBody SistemaSolarIluminacion sistemaSolarIluminacion) {
        SistemaSolarIluminacion createdSistemaSolarIluminacion = sistemaSolarIluminacionService.create(sistemaSolarIluminacion);
        return new ResponseEntity<>(createdSistemaSolarIluminacion, HttpStatus.CREATED);
    }

    // Obtener todos los Sistemas Solar de Iluminacion
    @GetMapping
    public ResponseEntity<List<SistemaSolarIluminacion>> readAll() {
        List<SistemaSolarIluminacion> sistemaSolarIluminacionList = sistemaSolarIluminacionService.readAll();
        return new ResponseEntity<>(sistemaSolarIluminacionList, HttpStatus.OK);
    }

    // Obtener un Sistema Solar de Iluminacion por ID
    @GetMapping("/{idSistemaSolarIluminacion}")
    public ResponseEntity<SistemaSolarIluminacion> readById(@PathVariable String idSistemaSolarIluminacion) {
        return sistemaSolarIluminacionService.readById(idSistemaSolarIluminacion)
        .map(sistemaSolarIluminacion -> new ResponseEntity<>(sistemaSolarIluminacion, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    // Actualizar un Sistema Solar de Iluminacion
    @PutMapping("/{idSistemaSolarIluminacion}")
    public ResponseEntity<SistemaSolarIluminacion> update(@PathVariable String idSistemaSolarIluminacion, @RequestBody SistemaSolarIluminacion sistemaSolarIluminacion) {
        sistemaSolarIluminacion.setIdSistemaSolarIluminacion(idSistemaSolarIluminacion);
        SistemaSolarIluminacion updatedSistemaSolarIluminacion = sistemaSolarIluminacionService.update(sistemaSolarIluminacion);
        return new ResponseEntity<>(updatedSistemaSolarIluminacion, HttpStatus.OK);
    }


    // Eliminar u Sistema Solar de Iluminacion por ID
    @DeleteMapping("/{idSistemaSolarIluminacion}")
    public ResponseEntity<Void> delete(@PathVariable String idSistemaSolarIluminacion) {
        sistemaSolarIluminacionService.delete(idSistemaSolarIluminacion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

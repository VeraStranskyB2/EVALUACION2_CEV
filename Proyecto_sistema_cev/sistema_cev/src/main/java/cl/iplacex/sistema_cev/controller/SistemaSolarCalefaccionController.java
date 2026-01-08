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


import cl.iplacex.sistema_cev.model.SistemaSolarCalefaccion;
import cl.iplacex.sistema_cev.service.ISistemaSolarCalefaccionService;



@RestController
@RequestMapping("/sistemassolarcalefaccion")
@CrossOrigin(origins = "*")
public class SistemaSolarCalefaccionController {

    @Autowired
    private ISistemaSolarCalefaccionService sistemaSolarCalefaccionService;

    // Crear un nuevo Sistema Solar de Calefaccion
    @PostMapping
    public ResponseEntity<SistemaSolarCalefaccion> create(@RequestBody SistemaSolarCalefaccion sistemaSolarCalefaccion) {
        SistemaSolarCalefaccion createdSistemaSolarCalefaccion = sistemaSolarCalefaccionService.create(sistemaSolarCalefaccion);
        return new ResponseEntity<>(createdSistemaSolarCalefaccion, HttpStatus.CREATED);
    }

    // Obtener todos los Sistemas Solar de Calefaccion
    @GetMapping
    public ResponseEntity<List<SistemaSolarCalefaccion>> readAll() {
        List<SistemaSolarCalefaccion> sistemaSolarCalefaccionList = sistemaSolarCalefaccionService.readAll();
        return new ResponseEntity<>(sistemaSolarCalefaccionList, HttpStatus.OK);
    }

    // Obtener un Sistema Solar de Calefaccion por ID
    @GetMapping("/{idSistemaSolarCalefaccion}")
    public ResponseEntity<SistemaSolarCalefaccion> readById(@PathVariable String idSistemaSolarCalefaccion) {
        return sistemaSolarCalefaccionService.readById(idSistemaSolarCalefaccion)
        .map(sistemaSolarCalefaccion -> new ResponseEntity<>(sistemaSolarCalefaccion, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    // Actualizar un Sistema Solar de Calefaccion
    @PutMapping("/{idSistemaSolarCalefaccion}")
    public ResponseEntity<SistemaSolarCalefaccion> update(@PathVariable String idSistemaSolarCalefaccion, @RequestBody SistemaSolarCalefaccion sistemaSolarCalefaccion) {
        sistemaSolarCalefaccion.setIdSistemaSolarCalefaccion(idSistemaSolarCalefaccion);
        SistemaSolarCalefaccion updatedSistemaSolarCalefaccion = sistemaSolarCalefaccionService.update(sistemaSolarCalefaccion);
        return new ResponseEntity<>(updatedSistemaSolarCalefaccion, HttpStatus.OK);
    }


    // Eliminar u Sistema Solar de Calefaccion por ID
    @DeleteMapping("/{idSistemaSolarCalefaccion}")
    public ResponseEntity<Void> delete(@PathVariable String idSistemaSolarCalefaccion) {
        sistemaSolarCalefaccionService.delete(idSistemaSolarCalefaccion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

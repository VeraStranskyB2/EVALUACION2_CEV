package cl.iplacex.sistema_cev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import cl.iplacex.sistema_cev.model.SistemaCalefaccion;
import cl.iplacex.sistema_cev.service.ISistemaCalefaccionService;


@RestController
@RequestMapping("/sistemascalefaccion")
@CrossOrigin(origins = "*")
public class SistemaCalefaccionController {
    
    @Autowired
    private ISistemaCalefaccionService sistemaCalefaccionService;

    // Crear un nuevo Sistema de Calefaccion
    @PreAuthorize("hasRole('EVALUADOR')")
    @PostMapping
    public ResponseEntity<SistemaCalefaccion> create(@RequestBody SistemaCalefaccion sistemaCalefaccion) {
        SistemaCalefaccion createdSistemaCalefaccion = sistemaCalefaccionService.create(sistemaCalefaccion);
        return new ResponseEntity<>(createdSistemaCalefaccion, HttpStatus.CREATED);
    }

    // Obtener todos los Sistemas de Calefaccion
    @PreAuthorize("hasRole('EVALUADOR')")
    @GetMapping
    public ResponseEntity<List<SistemaCalefaccion>> readAll() {
        List<SistemaCalefaccion> sistemaCalefaccionList = sistemaCalefaccionService.readAll();
        return new ResponseEntity<>(sistemaCalefaccionList, HttpStatus.OK);
    }

    // Obtener un Sistema de Calefaccion por ID
    @PreAuthorize("hasRole('EVALUADOR')")
    @GetMapping("/{idSistemaCalefaccion}")
    public ResponseEntity<SistemaCalefaccion> readById(@PathVariable String idSistemaCalefaccion) {
        return sistemaCalefaccionService.readById(idSistemaCalefaccion)
        .map(sistemaCalefaccion -> new ResponseEntity<>(sistemaCalefaccion, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    // Actualizar un Sistema de Calefaccion
    @PreAuthorize("hasRole('EVALUADOR')")
    @PutMapping("/{idSistemaCalefaccion}")
    public ResponseEntity<SistemaCalefaccion> update(@PathVariable String idSistemaCalefaccion, @RequestBody SistemaCalefaccion sistemaCalefaccion) {
        sistemaCalefaccion.setIdSistemaCalefaccion(idSistemaCalefaccion);
        SistemaCalefaccion updatedSistemaCalefaccion = sistemaCalefaccionService.update(sistemaCalefaccion);
        return new ResponseEntity<>(updatedSistemaCalefaccion, HttpStatus.OK);
    }


    // Eliminar un Sistema Solar por ID
    @PreAuthorize("hasRole('EVALUADOR')")
    @DeleteMapping("/{idSistemaCalefaccion}")
    public ResponseEntity<Void> delete(@PathVariable String idSistemaCalefaccion) {
        sistemaCalefaccionService.delete(idSistemaCalefaccion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

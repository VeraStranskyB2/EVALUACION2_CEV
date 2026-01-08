package cl.iplacex.sistema_cev.controller;

import java.util.List;


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


import cl.iplacex.sistema_cev.model.SistemaACS;
import cl.iplacex.sistema_cev.service.ISistemaACSService;



@RestController
@RequestMapping("/sistemasacs")
@CrossOrigin(origins = "*")
public class SistemaACSController {

    private final ISistemaACSService sistemaACSService;

    
    public SistemaACSController (ISistemaACSService sistemaACSService){
        this.sistemaACSService = sistemaACSService;
    }
    // Crear un nuevo Sistema de Agua Caliente Sanitaria
    @PreAuthorize("hasRole('EVALUADOR')")
    @PostMapping
    public ResponseEntity<SistemaACS> create(@RequestBody SistemaACS sistemaACS) {
        SistemaACS createdSistemaACS = sistemaACSService.create(sistemaACS);
        return new ResponseEntity<>(createdSistemaACS, HttpStatus.CREATED);
    }

    // Obtener todos los Sistemas ACS
    @PreAuthorize("hasRole('EVALUADOR')")
    @GetMapping
    public ResponseEntity<List<SistemaACS>> readAll() {
        List<SistemaACS> sistemasACS = sistemaACSService.readAll();
        return new ResponseEntity<>(sistemasACS, HttpStatus.OK);
    }

    // Obtener un Sistema ACS por ID
    @PreAuthorize("hasRole('EVALUADOR')")
    @GetMapping("/{idSistemaACS}")
    public ResponseEntity<SistemaACS> readById(@PathVariable String idSistemaACS) {
        return sistemaACSService.readById(idSistemaACS)
        .map(sistemaACS -> new ResponseEntity<>(sistemaACS, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    // Actualizar un Sistema ACS
    @PreAuthorize("hasRole('EVALUADOR')")
    @PutMapping("/{idSistemaACS}")
    public ResponseEntity<SistemaACS> update(@PathVariable String idSistemaACS, @RequestBody SistemaACS sistemaACS) {
        sistemaACS.setIdSistemaACS(idSistemaACS);
        SistemaACS updatedSistemaACS = sistemaACSService.update(sistemaACS);
        return new ResponseEntity<>(updatedSistemaACS, HttpStatus.OK);
    }


    // Eliminar u Sistema ACS por ID
    @PreAuthorize("hasRole('EVALUADOR')")
    @DeleteMapping("/{idSistemaACS}")
    public ResponseEntity<Void> delete(@PathVariable String idSistemaACS) {
        sistemaACSService.delete(idSistemaACS);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

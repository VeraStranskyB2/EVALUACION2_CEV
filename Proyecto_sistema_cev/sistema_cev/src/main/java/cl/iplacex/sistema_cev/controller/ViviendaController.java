package cl.iplacex.sistema_cev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import cl.iplacex.sistema_cev.dto.ViviendaDTO;
import cl.iplacex.sistema_cev.service.IViviendaService;

@RestController
@RequestMapping("/viviendas")
@CrossOrigin(origins = "*")
public class ViviendaController {

    @Autowired
    private IViviendaService viviendaService;

    // Crear Vivienda
    @PostMapping
    public ResponseEntity<ViviendaDTO> create(@RequestBody ViviendaDTO viviendaDTO) {
        ViviendaDTO createdVivienda = viviendaService.create(viviendaDTO);
        return new ResponseEntity<>(createdVivienda, HttpStatus.CREATED);
    }

    // Obtener Viviendas
    @GetMapping
    public ResponseEntity<List<ViviendaDTO>> readAll() {
        List<ViviendaDTO> vivienda = viviendaService.readAll();
        return new ResponseEntity<>(vivienda, HttpStatus.OK);
    }

    // Obtener Vivienda por Rol
    @GetMapping("/{rolVivienda}")
    public ResponseEntity<ViviendaDTO> readByRolVivienda(@PathVariable String rolVivienda) {
        return viviendaService.readByRolVivienda(rolVivienda)
                .map(vivienda -> new ResponseEntity<>(vivienda, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar Vivienda
    @PutMapping("/{rolVivienda}")
    public ResponseEntity<ViviendaDTO> update(@PathVariable String rolVivienda, @RequestBody ViviendaDTO viviendaDTO) {
        viviendaDTO.setRolVivienda(rolVivienda);
        ViviendaDTO updatedVivienda = viviendaService.update(viviendaDTO);
        return new ResponseEntity<>(updatedVivienda, HttpStatus.OK);
    }

    // Eliminar Vivienda por Rol
    @DeleteMapping("/{rolVivienda}")
    public ResponseEntity<Void> delete(@PathVariable String rolVivienda) {
        viviendaService.delete(rolVivienda);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
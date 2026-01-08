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

import cl.iplacex.sistema_cev.dto.InformeDTO;
import cl.iplacex.sistema_cev.service.IInformeService;

@RestController
@RequestMapping("/informes")
@CrossOrigin(origins = "*")

public class InformeController {

    @Autowired
    private IInformeService informeService;

    // Crear una nueva Informe
    @PostMapping
    public ResponseEntity<InformeDTO> create(@RequestBody InformeDTO informeDTO) {
        InformeDTO createdInforme = informeService.create(informeDTO);
        return new ResponseEntity<>(createdInforme, HttpStatus.CREATED);
    }

    // Obtener Lista de Informes
    @GetMapping
    public ResponseEntity<List<InformeDTO>> readAll() {
        List<InformeDTO> informes = informeService.readAll();
        return new ResponseEntity<>(informes, HttpStatus.OK);
    }

    // Obtener Informe por ID
    @GetMapping("/{idInforme}")
    public ResponseEntity<InformeDTO> findById(@PathVariable String idInforme) {
        return informeService.findById(idInforme)
                .map(informe -> new ResponseEntity<>(informe, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar Informe
    @PutMapping("/{idInforme}")
    public ResponseEntity<InformeDTO> update(@PathVariable String idInforme, @RequestBody InformeDTO informeDTO) {
        informeDTO.setIdInforme(idInforme);
        InformeDTO updatedInforme = informeService.update(informeDTO);
        return new ResponseEntity<>(updatedInforme, HttpStatus.OK);
    }

    // Eliminar Informe por ID
    @DeleteMapping("/{idInforme}")
    public ResponseEntity<Void> delete(@PathVariable String idInforme) {
        informeService.delete(idInforme);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
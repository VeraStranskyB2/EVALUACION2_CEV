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

import cl.iplacex.sistema_cev.model.Comuna;
import cl.iplacex.sistema_cev.service.IComunaService;



@RestController
@RequestMapping("/comuna")
@CrossOrigin(origins = "*")
public class ComunaController {

    @Autowired
    private IComunaService comunaService;

    // Crear una nueva Comuna
    @PostMapping
    public ResponseEntity<Comuna> create(@RequestBody Comuna comuna) {
        if (comuna == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            // Bad Request si la comuna es nula
        }
        Comuna createdComuna = comunaService.create(comuna);
        return new ResponseEntity<>(createdComuna, HttpStatus.CREATED);
    }

    // Obtener todas las Comunas
    @GetMapping
    public ResponseEntity<List<Comuna>> readAll() {
        List<Comuna> comunas = comunaService.readAll();
        return new ResponseEntity<>(comunas, HttpStatus.OK);
    }

    // Obtener Comuna por ID
    @GetMapping("/{idComuna}")
    public ResponseEntity<Comuna> findById(@PathVariable Long idComuna) {
        return comunaService.findById(idComuna)
                .map(comuna -> new ResponseEntity<>(comuna, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    
    // Actualizar Comuna
    @PutMapping("/{idComuna}")
    public ResponseEntity<Comuna> update(@PathVariable Long idComuna, @RequestBody Comuna comuna) {
        if (comuna == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
            // Bad_Request si la comuna es nula
        }
        comuna.setIdComuna(idComuna);
        Comuna updatedComuna = comunaService.update(comuna);
        return new ResponseEntity<>(updatedComuna, HttpStatus.OK);
    }

    // Eliminar Comuna por ID
    @DeleteMapping("/{idComuna}")
    public ResponseEntity<Void> delete(@PathVariable Long idComuna) {
        if (!comunaService.findById(idComuna).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            // Not_Found si la comuna no existe
        }
        comunaService.delete(idComuna);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

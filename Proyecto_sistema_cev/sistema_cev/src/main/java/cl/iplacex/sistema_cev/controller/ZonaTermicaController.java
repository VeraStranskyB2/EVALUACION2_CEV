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

import cl.iplacex.sistema_cev.model.ZonaTermica;
import cl.iplacex.sistema_cev.service.IZonaTermicaService;

@RestController
@RequestMapping("/zonastermicas")
@CrossOrigin(origins = "*")
public class ZonaTermicaController {

    @Autowired
    private IZonaTermicaService zonaTermicaService;

    // Crear ZonaTermica
    @PostMapping
    public ResponseEntity<ZonaTermica> create(@RequestBody ZonaTermica zonaTermica) {
        ZonaTermica createdZonaTermica = zonaTermicaService.create(zonaTermica);
        return new ResponseEntity<>(createdZonaTermica, HttpStatus.CREATED);
    }

    // Obtener todas las ZonaTermica
    @GetMapping
    public ResponseEntity<List<ZonaTermica>> readAll() {
        List<ZonaTermica> zonaTermica = zonaTermicaService.readAll();
        return new ResponseEntity<>(zonaTermica, HttpStatus.OK);
    }

    // Encontrar la Zona Termica por Id
    @GetMapping("/{idZonaTermica}")
    public ResponseEntity<ZonaTermica> readByZonaTermica(@PathVariable String idZonaTermica) {
        return zonaTermicaService.readById(idZonaTermica)
                .map(zonaTermica -> new ResponseEntity<>(zonaTermica, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar Zona Termica
    @PutMapping("/{idZonaTermica}")
    public ResponseEntity<ZonaTermica> update(@PathVariable String idZonaTermica, @RequestBody ZonaTermica zonaTermica) {
        if (!zonaTermicaService.readById(idZonaTermica).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        zonaTermica.setIdZonaTermica(idZonaTermica);
        ZonaTermica updatedZonaTermica = zonaTermicaService.update(zonaTermica);
        return new ResponseEntity<>(updatedZonaTermica, HttpStatus.OK);
    }

    // Eliminar Zona Termica por ID
    @DeleteMapping("/{idZonaTermica}")
    public ResponseEntity<Void> delete(@PathVariable String idZonaTermica) {
        zonaTermicaService.delete(idZonaTermica);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

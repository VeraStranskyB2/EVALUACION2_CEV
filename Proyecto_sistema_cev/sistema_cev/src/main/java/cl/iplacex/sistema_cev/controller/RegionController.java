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

import cl.iplacex.sistema_cev.model.Region;
import cl.iplacex.sistema_cev.service.IRegionService;


@RestController
@RequestMapping("/regiones")
@CrossOrigin(origins = "*")
public class RegionController {

     @Autowired
    private IRegionService regionService;

    // Crear una nueva Region
    @PostMapping
    public ResponseEntity<Region> create(@RequestBody Region region) {
        Region createdRegion = regionService.create(region);
        return new ResponseEntity<>(createdRegion, HttpStatus.CREATED);
    }

    // Obtener todas las Regiones
    @GetMapping
    public ResponseEntity<List<Region>> readAll() {
        List<Region> regiones = regionService.readAll();
        return new ResponseEntity<>(regiones, HttpStatus.OK);
    }

    // Obtener Region por ID
    @GetMapping("/{idRegion}")
    public ResponseEntity<Region> readById(@PathVariable Long idRegion) {
        return regionService.readById(idRegion)
                .map(region -> new ResponseEntity<>(region, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Actualizar Region
    @PutMapping("/{idRegion}")
    public ResponseEntity<Region> update(@PathVariable Long idRegion, @RequestBody Region region) {
        region.setIdRegion(idRegion);
        Region updatedRegion = regionService.update(region);
        return new ResponseEntity<>(updatedRegion, HttpStatus.OK);
    }

    // Eliminar Region por ID
    @DeleteMapping("/{idRegion}")
    public ResponseEntity<Void> delete(@PathVariable Long idRegion) {
        regionService.delete(idRegion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

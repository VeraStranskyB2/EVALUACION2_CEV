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

import cl.iplacex.sistema_cev.dto.EvaluacionDTO;

import cl.iplacex.sistema_cev.service.IEvaluacionService;

@RestController
@RequestMapping("/evaluaciones")
@CrossOrigin(origins = "*")
public class EvaluacionController {

    @Autowired
    private IEvaluacionService evaluacionService;

    // Crear una nueva Evaluacion
    @PostMapping
    public ResponseEntity<EvaluacionDTO> create(@RequestBody EvaluacionDTO evaluacionDTO) {
        EvaluacionDTO createdEvaluacion = evaluacionService.create(evaluacionDTO);
        return new ResponseEntity<>(createdEvaluacion, HttpStatus.CREATED);
    }

    // Obtener todas las Evaluaciones
    @GetMapping
    public ResponseEntity<List<EvaluacionDTO>> readAll() {
        List<EvaluacionDTO> evaluaciones = evaluacionService.readAll();
        return new ResponseEntity<>(evaluaciones, HttpStatus.OK);
    }

    // Obtener Evaluacion por ID
    @GetMapping("/{idEvaluacion}")
    public ResponseEntity<EvaluacionDTO> readById(@PathVariable String idEvaluacion) {
        return evaluacionService.readById(idEvaluacion)
        .map(evaluacion -> new ResponseEntity<>(evaluacion, HttpStatus.OK))
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar Evaluacion
    @PutMapping("/{idSEvaluacion}")
    public ResponseEntity<EvaluacionDTO> update(@PathVariable String idEvaluacion, @RequestBody EvaluacionDTO evaluacionDTO) {
        evaluacionDTO.setIdEvaluacion(idEvaluacion);
        EvaluacionDTO updatedEvaluacion = evaluacionService.update(evaluacionDTO);
        return new ResponseEntity<>(updatedEvaluacion, HttpStatus.OK);
    }
    

    // Eliminar Evaluacion por ID
    @DeleteMapping("/{idEvaluacion}")
    public ResponseEntity<Void> delete(@PathVariable String idEvaluacion) {
        evaluacionService.delete(idEvaluacion);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
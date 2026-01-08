package cl.iplacex.sistema_cev.controller;

import java.util.List;
import java.util.Optional;

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

import cl.iplacex.sistema_cev.model.Evaluador;
import cl.iplacex.sistema_cev.dto.EvaluadorDTO; // Importar el DTO
import cl.iplacex.sistema_cev.service.IEvaluadorService;

@RestController
@RequestMapping("/api/evaluadores")
@CrossOrigin(origins = "*")
public class EvaluadorController {

    @Autowired
    private IEvaluadorService evaluadorService;

    // Crear un nuevo Evaluador (ahora con DTO)
    @PostMapping
    public ResponseEntity<Evaluador> create(@RequestBody EvaluadorDTO evaluadorDTO) {
        Evaluador createdEvaluador = evaluadorService.create(evaluadorDTO);
        return new ResponseEntity<>(createdEvaluador, HttpStatus.CREATED);
    }

    // Obtener todos los Evaluadores (solo para ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Evaluador>> readAll() {
        List<Evaluador> evaluadores = evaluadorService.readAll();
        return new ResponseEntity<>(evaluadores, HttpStatus.OK);
    }

    // Obtener Evaluador por RUT (para ADMIN y EVALUADOR)
    @PreAuthorize("hasRole('ADMIN') or hasRole('EVALUADOR')")
    @GetMapping("/{usuarioRutUsuario}")
    public ResponseEntity<Evaluador> findById(@PathVariable String usuarioRutUsuario) {
        Optional<Evaluador> evaluadorOptional = evaluadorService.findById(usuarioRutUsuario);
        return evaluadorOptional
                .map(evaluador -> new ResponseEntity<>(evaluador, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Actualizar Evaluador (con DTO)
    @PreAuthorize("hasRole('ADMIN') or (hasRole('EVALUADOR') and #usuarioRutUsuario == authentication.principal.rutUsuario)")
    @PutMapping("/{usuarioRutUsuario}")
    public ResponseEntity<Evaluador> update(@PathVariable String usuarioRutUsuario, @RequestBody EvaluadorDTO evaluadorDTO) {
        Evaluador updatedEvaluador = evaluadorService.update(usuarioRutUsuario, evaluadorDTO);
        return new ResponseEntity<>(updatedEvaluador, HttpStatus.OK);
    }

    // Eliminar Evaluador por Rut (solo para ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{usuarioRutUsuario}")
    public ResponseEntity<Void> delete(@PathVariable String usuarioRutUsuario) {
        evaluadorService.delete(usuarioRutUsuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
package cl.iplacex.sistema_cev.controller;

import java.util.List;
import java.util.Optional;

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

import cl.iplacex.sistema_cev.model.Documento;
import cl.iplacex.sistema_cev.service.IDocumentoService;

@RestController
@RequestMapping("/documentos")
@CrossOrigin(origins = "*")
public class DocumentoController {

    @Autowired
    private IDocumentoService documentoService;

    // Crear un nuevo Documento
    @PostMapping
    public ResponseEntity<Documento> create(@RequestBody Documento documento) {
        Documento createdDocumento = documentoService.create(documento);
        return new ResponseEntity<>(createdDocumento, HttpStatus.CREATED);
    }

    // Obtener todos los Documentos
    @GetMapping
    public ResponseEntity<List<Documento>> readAll() {
        List<Documento> documentos = documentoService.readAll();
        return new ResponseEntity<>(documentos, HttpStatus.OK);
    }

    // Obtener Documento por ID
    @GetMapping("/{idDocumento}")
    public ResponseEntity<Documento> readById(@PathVariable String idDocumento) {
        Optional<Documento> documentoOpt = documentoService.readById(idDocumento);
        return documentoOpt.map(documento -> new ResponseEntity<>(documento, HttpStatus.OK))
                           .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    

    // Actualizar Documento
    @PutMapping("/{idDocumento}")
    public ResponseEntity<Documento> update(@PathVariable String idDocumento, @RequestBody Documento documento) {
        documento.setIdDocumento(idDocumento);
        // Verificar si el documento existe antes de actualizar
        if (!documentoService.readById(idDocumento).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Documento updatedDocumento = documentoService.update(documento);
        return new ResponseEntity<>(updatedDocumento, HttpStatus.OK);
    }

    // Eliminar Documento por ID
    @DeleteMapping("/{idDocumento}")
    public ResponseEntity<Void> delete(@PathVariable String idDocumento) {
        // Verificar si el documento existe antes de eliminar
        if (!documentoService.readById(idDocumento).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        documentoService.delete(idDocumento);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
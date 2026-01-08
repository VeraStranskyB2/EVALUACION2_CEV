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

import cl.iplacex.sistema_cev.dto.SolicitudDTO;
import cl.iplacex.sistema_cev.service.ISolicitudService;

@RestController
@RequestMapping("api/solicitudes")
@CrossOrigin(origins = "*")
public class SolicitudController {

    @Autowired
    private ISolicitudService solicitudService;

    // Crear una nueva Solicitud
    @PreAuthorize("hasRole('CLIENTE')")
    @PostMapping
    public ResponseEntity<SolicitudDTO> create(@RequestBody SolicitudDTO solicitudDTO) {
        SolicitudDTO createdSolicitud = solicitudService.create(solicitudDTO);
        return new ResponseEntity<>(createdSolicitud, HttpStatus.CREATED);
    }

    // Obtener todas las Solicitudes
    @PreAuthorize("hasRole('EVALUADOR')")
    @GetMapping
    public ResponseEntity<List<SolicitudDTO>> readAll() {
        List<SolicitudDTO> solicitudes = solicitudService.readAll();
        return new ResponseEntity<>(solicitudes, HttpStatus.OK);
    }

    // Obtener Solicitud por ID
    @GetMapping("/{idSolicitud}")
    public ResponseEntity<SolicitudDTO> readById(@PathVariable String idSolicitud) {
        return solicitudService.readById(idSolicitud)
                .map(solicitud -> new ResponseEntity<>(solicitud, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Obtener Solicitudes por Estado
    @GetMapping("/estado/{idEstado}")
    public ResponseEntity<List<SolicitudDTO>> readByEstado(@PathVariable Long idEstado) {
        List<SolicitudDTO> solicitudes = solicitudService.readByEstado(idEstado);
        if (solicitudes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
          
        return new ResponseEntity<>(solicitudes, HttpStatus.OK);
    }

    // Actualizar Solicitud
    @PutMapping("/{idSolicitud}")
    public ResponseEntity<SolicitudDTO> update(@PathVariable String idSolicitud, @RequestBody SolicitudDTO solicitudDTO) {
        solicitudDTO.setIdSolicitud(idSolicitud);
        // Verificar si la solicitud existe antes de actualizar
        if (!solicitudService.readById(idSolicitud).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        SolicitudDTO updatedSolicitud = solicitudService.update(solicitudDTO);
        return new ResponseEntity<>(updatedSolicitud, HttpStatus.OK);
    }

    // Eliminar Solicitud por ID
    @DeleteMapping("/{idSolicitud}")
    public ResponseEntity<Void> delete(@PathVariable String idSolicitud) {
        // Verificar si la solicitud existe antes de eliminar
        if (!solicitudService.readById(idSolicitud).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        solicitudService.delete(idSolicitud);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
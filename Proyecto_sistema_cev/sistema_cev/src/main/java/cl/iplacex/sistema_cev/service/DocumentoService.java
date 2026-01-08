package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.iplacex.sistema_cev.model.Documento;
import cl.iplacex.sistema_cev.repository.DocumentoRepository;

@Service
public class DocumentoService implements IDocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;

    // Guardar Documento
    @Override
    public Documento create(Documento documento) {
        return documentoRepository.save(documento);
    }

    // Devuelve lista de Documentos
    @Override
    public List<Documento> readAll() {
        return documentoRepository.findAll();
    }

    // Devuelve Documento por Id
    @Override
    public Optional<Documento> readById(String idDocumento) {
        return documentoRepository.findById(idDocumento);
    }

    // Actualiza Documento
    @Override
    public Documento update(Documento documento) {
        // Verificar si el documento existe antes de actualizar
        if (!documentoRepository.existsById(documento.getIdDocumento())) {
            throw new RuntimeException("Documento no encontrado con ID: " + documento.getIdDocumento());
        }
        return documentoRepository.save(documento);
    }

    // Elimina Documento por ID
    @Override
    public void delete(String idDocumento) {
        // Verificar si el documento existe antes de eliminar
        if (!documentoRepository.existsById(idDocumento)) {
            throw new RuntimeException("Documento no encontrado con ID: " + idDocumento);
        }
        documentoRepository.deleteById(idDocumento);
    }

}

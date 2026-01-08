package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.iplacex.sistema_cev.exception.ResourceNotFoundException;
import cl.iplacex.sistema_cev.model.Etiqueta;
import cl.iplacex.sistema_cev.repository.EtiquetaRepository;

@Service
public class EtiquetaService implements IEtiquetaService {

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    // Devuelve lista de Etiquetas
    @Override
    public List<Etiqueta> findAll() {
        return etiquetaRepository.findAll();
    }

    // Devuelve Etiqueta por ID
    @Override
    public Optional<Etiqueta> findById(String idEtiqueta) {
        return etiquetaRepository.findById(idEtiqueta);
    }

    // Guardar una nueva Etiqueta
    @Override
    public Etiqueta save(Etiqueta etiqueta) {
        return etiquetaRepository.save(etiqueta);
    }

    // Actualiza Etiqueta
    @Override
    public Etiqueta update(String idEtiqueta, Etiqueta etiqueta) {
        if (!etiquetaRepository.existsById(idEtiqueta)) {
            throw new ResourceNotFoundException("Etiqueta no encontrada con idEtiqueta " + idEtiqueta);
        }
        etiqueta.setIdEtiqueta(idEtiqueta); // Asegúrate de que este método exista en la clase Etiqueta
        return etiquetaRepository.save(etiqueta);
    }

    // Elimina Etiqueta por ID
    @Override
    public void delete(String idEtiqueta) {
        if (!etiquetaRepository.existsById(idEtiqueta)) {
            throw new ResourceNotFoundException("Etiqueta no encontrada con idEtiqueta " + idEtiqueta);
        }
        etiquetaRepository.deleteById(idEtiqueta);
    }

}
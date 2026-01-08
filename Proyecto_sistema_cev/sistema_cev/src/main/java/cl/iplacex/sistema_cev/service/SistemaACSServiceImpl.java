package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import cl.iplacex.sistema_cev.model.SistemaACS;
import cl.iplacex.sistema_cev.repository.SistemaACSRepository;

@Service
@Primary
public class SistemaACSServiceImpl implements ISistemaACSService {

    private final SistemaACSRepository repository;

    // Inyección de dependencias a través del constructor
   
    public SistemaACSServiceImpl(SistemaACSRepository repository) {
        this.repository = repository;
    }

    @Override
    public SistemaACS create(SistemaACS sistemaACS) {
        return repository.save(sistemaACS);
    }

    @Override
    public List<SistemaACS> readAll() {
        return repository.findAll();
    }

    @Override
    public Optional<SistemaACS> readById(String idSistemaACS) {
        return repository.findById(idSistemaACS);
    }

    @Override
    public SistemaACS update(SistemaACS sistemaACS) {
        // Verifica si el objeto existe antes de actualizar
        if (!repository.existsById(sistemaACS.getIdSistemaACS())) {
            throw new IllegalArgumentException("El sistema ACS con ID " + sistemaACS.getIdSistemaACS() + " no existe.");
        }
        return repository.save(sistemaACS);
    }

    @Override
    public void delete(String idSistemaACS) {
        // Verifica si el objeto existe antes de eliminar
        if (!repository.existsById(idSistemaACS)) {
            throw new IllegalArgumentException("El sistema ACS con ID " + idSistemaACS + " no existe.");
        }
        repository.deleteById(idSistemaACS);
    }
}

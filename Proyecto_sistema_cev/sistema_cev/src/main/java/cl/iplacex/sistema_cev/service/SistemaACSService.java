package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.iplacex.sistema_cev.model.SistemaACS;
import cl.iplacex.sistema_cev.repository.SistemaACSRepository;



@Service
public class SistemaACSService implements ISistemaACSService {

    @Autowired
    private SistemaACSRepository sistemaACSRepository;


    // Crear Sistema ACS
    @Override
    public SistemaACS create(SistemaACS sistemaACS) {
        return sistemaACSRepository.save(sistemaACS);
    }

    // Obtener todos los indices de Sistema ACS
    @Override
    public List<SistemaACS> readAll() {
        return sistemaACSRepository.findAll();
    }

    // Encontrar Sistema ACS por ID
    @Override
    public Optional<SistemaACS> readById(String idSistemaACS) {
        return sistemaACSRepository.findById(idSistemaACS);
    }

    
    // Actualizar Sistema ACS
    @Override
    public SistemaACS update(SistemaACS sistemaACS) {
        return sistemaACSRepository.save(sistemaACS);
    }

    // Eliminar Sistema ACS por ID
    @Override
    public void delete(String idSistemaACS) {
        sistemaACSRepository.deleteById(idSistemaACS);
    }


}

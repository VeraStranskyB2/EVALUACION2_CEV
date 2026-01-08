package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import cl.iplacex.sistema_cev.model.SistemaSolarCalefaccion;
import cl.iplacex.sistema_cev.repository.SistemaSolarCalefaccionRepository;

@Service
public class SistemaSolarCalefaccionServiceImpl implements ISistemaSolarCalefaccionService {

private final SistemaSolarCalefaccionRepository repository;

    
    public SistemaSolarCalefaccionServiceImpl(SistemaSolarCalefaccionRepository repository) {
        this.repository = repository;
    }

    @Override
    public SistemaSolarCalefaccion create(SistemaSolarCalefaccion sistemaSolarCalefaccion) {
        return repository.save(sistemaSolarCalefaccion);
    }

    @Override
    public List<SistemaSolarCalefaccion> readAll() {
        return repository.findAll();
    }

    @Override
    public Optional<SistemaSolarCalefaccion> readById(String idSistemaSolarCalefaccion) {
        return repository.findById(idSistemaSolarCalefaccion);
    }

    @Override
    public SistemaSolarCalefaccion update(SistemaSolarCalefaccion sistemaSolarCalefaccion) {
        return repository.save(sistemaSolarCalefaccion);
    }

    @Override
    public void delete(String idSistemaSolarCalefaccion) {
        repository.deleteById(idSistemaSolarCalefaccion);
    }
}

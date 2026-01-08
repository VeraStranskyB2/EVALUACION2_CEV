package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.iplacex.sistema_cev.exception.RolNotFoundException;
import cl.iplacex.sistema_cev.model.Rol;
import cl.iplacex.sistema_cev.repository.RolRepository;

@Service
public class RolService implements IRolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Rol create(Rol rol) {
        return rolRepository.save(rol);
    }

    @Override
    public List<Rol> readAll() {
        return rolRepository.findAll();
    }

    @Override
    public Optional<Rol> findById(Long idRol) {
        return rolRepository.findById(idRol);
    }

    @Override
    public Rol update(Rol rol) {
        Rol existingRol = rolRepository.findById(rol.getIdRol())
                                       .orElseThrow(() -> new RolNotFoundException("Rol no encontrado con ID: " + rol.getIdRol()));
        
        existingRol.setNombre(rol.getNombre());

        return rolRepository.save(existingRol);
    }

    @Override
    public void delete(Long idRol) {
        // Se Busca rol primero; si no se encuentra, la excepción se lanzará
        // y el flujo se detendrá aquí.
        rolRepository.findById(idRol)
                     .orElseThrow(() -> new RolNotFoundException("Rol no encontrado con ID: " + idRol));

        // Si el rol se encuentra, se elimina.
        rolRepository.deleteById(idRol);
    }
}
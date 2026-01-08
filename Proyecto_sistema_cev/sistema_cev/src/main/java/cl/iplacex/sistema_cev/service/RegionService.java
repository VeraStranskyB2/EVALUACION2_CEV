package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.iplacex.sistema_cev.model.Region;
import cl.iplacex.sistema_cev.repository.RegionRepository;


@Service
public class RegionService implements IRegionService {

     @Autowired
    private RegionRepository regionRepository;

    // Crear nueva Region
    @Override
    public Region create(Region region) {
        return regionRepository.save(region);
    }

    // Obtener todas las Regiones
    @Override
    public List<Region> readAll() {
        return regionRepository.findAll();
    }

    // Encontrar Region por ID
    @Override
    public Optional<Region> readById(Long idRegion) {
        return regionRepository.findById(idRegion);
    }

    
    // Actualizar Region
    @Override
    public Region update(Region region) {
        return regionRepository.save(region);
    }

    // Eliminar Region por ID
    @Override
    public void delete(Long idRegion) {
        regionRepository.deleteById(idRegion);
    }

}

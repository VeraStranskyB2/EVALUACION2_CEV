package cl.iplacex.sistema_cev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.iplacex.sistema_cev.model.ZonaTermica;
import cl.iplacex.sistema_cev.repository.ZonaTermicaRepository;



@Service
public class ZonaTermicaService implements IZonaTermicaService {

    @Autowired
    private ZonaTermicaRepository zonaTermicaRepository;

    // Crear Zona Termica
    @Override
    public ZonaTermica create(ZonaTermica zonaTermica) {
        if (zonaTermica == null) {
            throw new IllegalArgumentException("La Zona Térmica no puede ser nula.");
        }
        return zonaTermicaRepository.save(zonaTermica);
        }

    // Obtener todas las Zonas Termica
    @Override
    public List<ZonaTermica> readAll() {
        return zonaTermicaRepository.findAll();
    }

    // Encontrar Zona Termica por ID
    @Override
    public Optional<ZonaTermica> readById(String idZonaTermica) {
        return zonaTermicaRepository.findById(idZonaTermica);
    }

    // Actualizar Zona Termica
    @Override
    public ZonaTermica update(ZonaTermica zonaTermica) {
        if (zonaTermica == null || !zonaTermicaRepository.existsById(zonaTermica.getIdZonaTermica())) {
            throw new IllegalArgumentException("La Zona Térmica no puede ser nula y debe existir para ser actualizada.");
        }
        return zonaTermicaRepository.save(zonaTermica);
    }

    // Eliminar Zona Termica por ID
    @Override
    public void delete(String idZonaTermica) {
        zonaTermicaRepository.deleteById(idZonaTermica);
    }

}

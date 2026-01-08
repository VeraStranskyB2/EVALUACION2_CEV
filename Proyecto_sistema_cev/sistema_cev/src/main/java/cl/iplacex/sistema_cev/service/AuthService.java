package cl.iplacex.sistema_cev.service;

import cl.iplacex.sistema_cev.dto.ClienteDTO;
import cl.iplacex.sistema_cev.dto.EvaluadorDTO;
import cl.iplacex.sistema_cev.dto.RegistroDTO;
import cl.iplacex.sistema_cev.model.Rol;
import cl.iplacex.sistema_cev.model.Usuario;
import cl.iplacex.sistema_cev.repository.RolRepository;
import cl.iplacex.sistema_cev.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EvaluadorService evaluadorService;

    @Override
    @Transactional
    public Usuario registrarUsuario(RegistroDTO registroDTO) {
        // 1. Validaciones de negocio
        if (usuarioRepository.existsByRutUsuario(registroDTO.getRutUsuario())) {
            throw new IllegalArgumentException("El RUT ya está registrado.");
        }
        if (usuarioRepository.existsByEmail(registroDTO.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado.");
        }

        // 2. Validación de campos específicos según el rol
        String rolNombre = registroDTO.getRol().toUpperCase();
        if ("CLIENTE".equals(rolNombre)) {
            if (registroDTO.getRazonSocial() == null || registroDTO.getRazonSocial().isBlank()) {
                throw new IllegalArgumentException("La razón social es obligatoria para el rol CLIENTE.");
            }
        } else if ("EVALUADOR".equals(rolNombre)) {
            if (registroDTO.getNombreEvaluador() == null || registroDTO.getNombreEvaluador().isBlank()) {
                throw new IllegalArgumentException("El nombre del evaluador es obligatorio para el rol EVALUADOR.");
            }
            if (registroDTO.getApellidos() == null || registroDTO.getApellidos().isBlank()) {
                throw new IllegalArgumentException("Los apellidos son obligatorios para el rol EVALUADOR.");
            }
            if (registroDTO.getProfesion() == null || registroDTO.getProfesion().isBlank()) {
                throw new IllegalArgumentException("La profesión es obligatoria para el rol EVALUADOR.");
            }
        } else {
            throw new IllegalArgumentException("Rol de usuario inválido.");
        }

        // 3. Creación y persistencia de Usuario
        Usuario usuario = new Usuario();
        usuario.setRutUsuario(registroDTO.getRutUsuario());
        usuario.setEmail(registroDTO.getEmail());
        usuario.setTelefono(registroDTO.getTelefono());
        usuario.setUserPassword(passwordEncoder.encode(registroDTO.getUserPassword()));

        Rol rol = rolRepository.findByNombre(rolNombre)
                             .orElseThrow(() -> new RuntimeException("Error: Rol no encontrado."));
        usuario.setRoles(Collections.singleton(rol));
        Usuario nuevoUsuario = usuarioRepository.save(usuario);

        // 4. Delegar la creación del perfil específico
        if ("CLIENTE".equals(rolNombre)) {
            ClienteDTO clienteDTO = new ClienteDTO();
            clienteDTO.setUsuarioRutUsuario(registroDTO.getRutUsuario());
            clienteDTO.setRazonSocial(registroDTO.getRazonSocial());
            clienteDTO.setRepresentanteLegal(registroDTO.getRepresentanteLegal());
            clienteDTO.setGiroComercial(registroDTO.getGiroComercial());
            clienteService.create(clienteDTO);
        } else if ("EVALUADOR".equals(rolNombre)) {
            EvaluadorDTO evaluadorDTO = new EvaluadorDTO();
            evaluadorDTO.setUsuarioRutUsuario(registroDTO.getRutUsuario());
            evaluadorDTO.setNombreEvaluador(registroDTO.getNombreEvaluador());
            evaluadorDTO.setApellidos(registroDTO.getApellidos());
            evaluadorDTO.setProfesion(registroDTO.getProfesion());
            evaluadorService.create(evaluadorDTO);
        }

        return nuevoUsuario;
    }
}
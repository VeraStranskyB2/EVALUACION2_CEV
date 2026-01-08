package cl.iplacex.sistema_cev.service;

import cl.iplacex.sistema_cev.model.Rol;
import cl.iplacex.sistema_cev.model.Usuario;
import cl.iplacex.sistema_cev.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/* Servicio para cargar los detalles del usuario desde la base de datos.
   Permite la autenticación usando tanto el RUT como el email del usuario. */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String rutUsuarioOrEmail) throws UsernameNotFoundException {
        // Busca al usuario por su RUT o Email en la base de datos.
        Usuario usuario = usuarioRepository.findByRutUsuarioOrEmail(rutUsuarioOrEmail, rutUsuarioOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado con ese rut o email: " + rutUsuarioOrEmail));

        // Mapea los roles de la entidad Usuario a autoridades de Spring Security.
        Collection<? extends GrantedAuthority> authorities = mapearRoles(usuario.getRoles());

        // Retorna un objeto UserDetails para la autenticación de Spring Security.
        // Utiliza el email como "username" para la autenticación.
        return new User(usuario.getEmail(), usuario.getUserPassword(), authorities);
    }

    /* Convierte un conjunto de roles de la entidad a una colección de GrantedAuthority.
     * Añade el prefijo "ROLE_" que es estándar en Spring Security. */
    private Collection<? extends GrantedAuthority> mapearRoles(Set<Rol> roles) {
        return roles.stream()
                .map(rol -> new SimpleGrantedAuthority("ROLE_" + rol.getNombre()))
                .collect(Collectors.toList());
    }
}


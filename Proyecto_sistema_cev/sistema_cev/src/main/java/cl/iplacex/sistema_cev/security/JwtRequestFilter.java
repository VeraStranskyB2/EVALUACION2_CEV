package cl.iplacex.sistema_cev.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import cl.iplacex.sistema_cev.service.CustomUserDetailsService;

import java.io.IOException;

//Filtro de seguridad que intercepta las solicitudes HTTP para validar los tokens JWT.

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService userDetailsService;

    public JwtRequestFilter(JwtTokenProvider jwtTokenProvider, CustomUserDetailsService userDetailsService2) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailsService = userDetailsService2;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // Obtener el token JWT del encabezado de la solicitud
            String jwt = getJwtFromRequest(request);

            // Validar el token y autenticar al usuario
            if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
                String username = jwtTokenProvider.getUsernameFromToken(jwt);

                // Cargar los detalles del usuario y crear la autenticación
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // Establecer la autenticación en el contexto de seguridad de Spring
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            // Si ocurre un error, limpiar el contexto de seguridad
            SecurityContextHolder.clearContext();
            // Puedes agregar un logger aquí para registrar el error
        }

        // Continuar con la cadena de filtros
        filterChain.doFilter(request, response);
    }

    //Extrae el token JWT del encabezado "Authorization

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
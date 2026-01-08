package cl.iplacex.sistema_cev.controller;

import cl.iplacex.sistema_cev.dto.LoginDTO;
import cl.iplacex.sistema_cev.dto.RegistroDTO;
import cl.iplacex.sistema_cev.dto.AuthResponseDTO;
import cl.iplacex.sistema_cev.security.JwtTokenProvider;
import cl.iplacex.sistema_cev.service.IAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;

/* Controlador REST para manejar los puntos de entrada de autenticación y registro.
   Proporciona endpoints para el inicio de sesión de usuarios y la creación de nuevas cuentas. */

@RestController
@RequestMapping("/api/auth") // Define la URL base para todos los endpoints en este controlador
@CrossOrigin(origins = "*") // Permite solicitudes desde cualquier origen, útil para el desarrollo frontend
public class AuthController {

    // Inyección de dependencias a través del constructor
    private final AuthenticationManager authenticationManager;
    private final IAuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Constructor del controlador que inyecta los servicios necesarios.
     * @param authenticationManager Gestor de autenticación de Spring Security.
     * @param authService Servicio personalizado para la lógica de registro.
     * @param jwtTokenProvider Componente para la generación de tokens JWT.
     */
    public AuthController(
            AuthenticationManager authenticationManager,
            IAuthService authService,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.authenticationManager = authenticationManager;
        this.authService = authService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    /**
     * Endpoint para el inicio de sesión de un usuario.
     * @param loginDto DTO que contiene las credenciales de inicio de sesión (RUT/Email y contraseña).
     * @return ResponseEntity con un token JWT y un mensaje de éxito si la autenticación es exitosa. */
    
    @PostMapping("/login") // Mapea las peticiones POST a /api/auth/login
    public ResponseEntity<AuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDto) {
        // 1. Autentica al usuario usando el AuthenticationManager de Spring Security.
        //    Crea un token de autenticación a partir de las credenciales del DTO.
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginDto.getRutUsuarioOrEmail(), loginDto.getUserPassword())
        );
        
        // 2. Establece la autenticación en el contexto de seguridad actual del hilo de ejecución.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. Genera un token JWT a partir del objeto de autenticación.
        String jwt = jwtTokenProvider.generateToken(authentication);

        // 4. Crea y devuelve un objeto de respuesta que contiene el token JWT.
        AuthResponseDTO authResponseDTO = new AuthResponseDTO(jwt, "Inicio de sesión exitoso.");
        return ResponseEntity.ok(authResponseDTO); // Retorna un código de estado 200 OK
    }


    /**Endpoint para el registro de un nuevo usuario.
     * @param registroDTO DTO que contiene los datos completos del nuevo usuario.
     * @return ResponseEntity con un mensaje de éxito o un mensaje de error si el registro falla. */

    @PostMapping("/registrar") // Mapea las peticiones POST a /api/auth/registrar
    public ResponseEntity<String> registrarUsuario(@RequestBody RegistroDTO registroDTO) {
        try {
            // 1. Delega la lógica de registro al AuthService.
            //    Este servicio maneja la creación del usuario y sus perfiles (Cliente/Evaluador).
            authService.registrarUsuario(registroDTO);
            
            // 2. Retorna una respuesta con estado 201 CREATED si el registro fue exitoso.
            return new ResponseEntity<>("Usuario registrado exitosamente.", HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {

            // 3. Captura cualquier excepción de negocio lanzada por el servicio
            //    y retorna una respuesta con estado 400 BAD REQUEST y el mensaje de error.
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
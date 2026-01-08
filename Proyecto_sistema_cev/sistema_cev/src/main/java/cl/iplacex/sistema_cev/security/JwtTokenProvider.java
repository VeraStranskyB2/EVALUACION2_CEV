package cl.iplacex.sistema_cev.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private final Key secretKey;
    private final long expiration;

    // Constructor para inyectar los valores desde application.properties
    public JwtTokenProvider(
            @Value("${app.jwt.secret}") String secret,
            @Value("${app.jwt.expiration-ms}") long expiration) {
        try {
            byte[] keyBytes = Decoders.BASE64.decode(secret);
            this.secretKey = Keys.hmacShaKeyFor(keyBytes);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("La clave secreta JWT en application.properties no es una Base64 válida.", e);
        }
        this.expiration = expiration;
    }

    /* Genera un token JWT a partir de la autenticación de un usuario.
       Incluye los roles del usuario en el token. */

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }

    /* Valida la autenticidad y vigencia de un token JWT.
       return true si el token es válido, false en caso contrario */

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            System.out.println("Firma JWT inválida.");
        } catch (MalformedJwtException ex) {
            System.out.println("Token JWT mal formado.");
        } catch (ExpiredJwtException ex) {
            System.out.println("Token JWT expirado.");
        } catch (UnsupportedJwtException ex) {
            System.out.println("Token JWT no soportado.");
        } catch (IllegalArgumentException ex) {
            System.out.println("El token JWT está vacío o es nulo.");
        }
        return false;
    }

    /* Extrae el nombre de usuario y retorna el nombre de usuario */
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    /* Genera una nueva clave secreta en formato Base64.
    Útil para generar la clave inicial para el archivo application.properties.
    return la clave Base64 generada */
    
    public String generateNewSecretKey() {
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        return Encoders.BASE64.encode(key.getEncoded());
    }
}
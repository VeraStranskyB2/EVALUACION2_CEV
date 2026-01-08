package cl.iplacex.sistema_cev.utilities;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class GeneradorClaveJWT {
    public static void main(String[] args) {
        // Genera una clave HMAC-SHA-512 segura
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        // Codifica la clave en formato Base64 para guardarla fácilmente.
        String base64Key = Encoders.BASE64.encode(key.getEncoded());

        // Imprime la clave generada en la consola
        System.out.println("Tu clave JWT generada (guárdala en application.properties):");
        System.out.println(base64Key);
    }
}

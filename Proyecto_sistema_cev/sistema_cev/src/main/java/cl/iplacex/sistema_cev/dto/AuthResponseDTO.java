package cl.iplacex.sistema_cev.dto;


//Para la respuesta de autenticación.Contiene el token JWT y un mensaje para el cliente.
 
public class AuthResponseDTO {

    private String token;
    private String message;

    public AuthResponseDTO(String token, String message) {
        this.token = token;
        this.message = message;
    }

    // Getters
    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }

    // Setters
    public void setToken(String token) {
        this.token = token;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

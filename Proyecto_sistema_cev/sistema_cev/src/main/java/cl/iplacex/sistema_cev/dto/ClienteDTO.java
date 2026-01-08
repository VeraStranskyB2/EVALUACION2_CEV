package cl.iplacex.sistema_cev.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/* Este objeto se utiliza para transferir datos entre el cliente (frontend) y
 * el servidor, asegurando que solo se expongan los campos necesarios */
public class ClienteDTO {

    @NotNull(message = "El RUT del usuario no puede ser nulo")
    @Size(min = 1, max = 12, message = "El RUT debe tener entre 1 y 12 caracteres")
    private String usuarioRutUsuario;

    @NotNull(message = "La razón social no puede ser nula")
    @Size(max = 45, message = "La razón social no puede exceder los 45 caracteres")
    private String razonSocial;

    @Size(max = 100, message = "El representante legal no puede exceder los 100 caracteres")
    private String representanteLegal;

    @Size(max = 60, message = "El giro comercial no puede exceder los 60 caracteres")
    private String giroComercial;

    // Constructor vacío (necesario para la deserialización de JSON)
    public ClienteDTO() {
    }

    // Constructor con parámetros
    public ClienteDTO(String usuarioRutUsuario, String razonSocial, String representanteLegal, String giroComercial) {
        this.usuarioRutUsuario = usuarioRutUsuario;
        this.razonSocial = razonSocial;
        this.representanteLegal = representanteLegal;
        this.giroComercial = giroComercial;
    }

    // Getters y Setters
    public String getUsuarioRutUsuario() {
        return usuarioRutUsuario;
    }

    public void setUsuarioRutUsuario(String usuarioRutUsuario) {
        this.usuarioRutUsuario = usuarioRutUsuario;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public String getGiroComercial() {
        return giroComercial;
    }

    public void setGiroComercial(String giroComercial) {
        this.giroComercial = giroComercial;
    }
}

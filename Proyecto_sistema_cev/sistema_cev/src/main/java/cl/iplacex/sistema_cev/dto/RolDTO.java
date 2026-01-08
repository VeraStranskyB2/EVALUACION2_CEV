package cl.iplacex.sistema_cev.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


// Se utiliza para transferir datos de roles de manera segura y controlada.
public class RolDTO {

    // Validaciones de negocio y de la API
    @NotBlank(message = "El nombre del rol no puede estar en blanco")
    @Size(max = 45, message = "El nombre del rol no puede exceder los 45 caracteres")
    private String nombre;

    public RolDTO() {
    }

    public RolDTO(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
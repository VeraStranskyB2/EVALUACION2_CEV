package cl.iplacex.sistema_cev.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class RegistroDTO {

    // Usuario - Obligatorio para todos los roles
    @NotBlank(message = "El RUT del usuario no puede estar en blanco")
    @Size(min = 9, max = 12, message = "El RUT debe tener entre 9 y 12 caracteres")
    private String rutUsuario;

    @NotBlank(message = "El correo electrónico no puede estar en blanco")
    @Email(message = "El correo electrónico debe tener un formato válido")
    @Size(max = 100, message = "El email no puede exceder los 100 caracteres")
    private String email;

    @NotBlank(message = "La contraseña no puede estar en blanco")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String userPassword;

    @Size(max = 20, message = "El teléfono no puede exceder los 20 caracteres")
    private String telefono;

    // Rol - Obligatorio para todos los roles
    @NotBlank(message = "El rol no puede estar en blanco")
    private String rol;

    // Campos de Cliente - Estas validaciones se deben hacer en el servicio
    private String razonSocial;
    private String representanteLegal;
    private String giroComercial;
    private LocalDateTime fechaRegistro;

    // Campos de Evaluador - Estas validaciones se deben hacer en el servicio
    private String nombreEvaluador;
    private String apellidos;
    private String profesion;
    private LocalDateTime fechaAcreditacion;

    // Getters y Setters
    public String getRutUsuario() {
        return rutUsuario;
    }

    public void setRutUsuario(String rutUsuario) {
        this.rutUsuario = rutUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNombreEvaluador() {
        return nombreEvaluador;
    }

    public void setNombreEvaluador(String nombreEvaluador) {
        this.nombreEvaluador = nombreEvaluador;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public LocalDateTime getFechaAcreditacion() {
        return fechaAcreditacion;
    }

    public void setFechaAcreditacion(LocalDateTime fechaAcreditacion) {
        this.fechaAcreditacion = fechaAcreditacion;
    }
}
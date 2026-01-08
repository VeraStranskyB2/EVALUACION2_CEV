package cl.iplacex.sistema_cev.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public class EvaluadorDTO {

    private String usuarioRutUsuario;

    @NotBlank(message = "El nombre del evaluador no puede estar en blanco")
    @Size(max = 45, message = "El nombre no puede exceder los 45 caracteres")
    private String nombreEvaluador;

    @NotBlank(message = "Los apellidos no pueden estar en blanco")
    @Size(max = 45, message = "Los apellidos no pueden exceder los 45 caracteres")
    private String apellidos;

    @Size(max = 60, message = "La profesión no puede exceder los 60 caracteres")
    private String profesion;

    @NotNull(message = "La fecha de acreditación no puede ser nula")
    private LocalDateTime fechaAcreditacion;

    // Constructores
    public EvaluadorDTO() {}

    public EvaluadorDTO(String usuarioRutUsuario, String nombreEvaluador, String apellidos, String profesion, LocalDateTime fechaAcreditacion) {
        this.usuarioRutUsuario = usuarioRutUsuario;
        this.nombreEvaluador = nombreEvaluador;
        this.apellidos = apellidos;
        this.profesion = profesion;
        this.fechaAcreditacion = fechaAcreditacion;
    }

    // Getters y Setters
    public String getUsuarioRutUsuario() {
        return usuarioRutUsuario;
    }

    public void setUsuarioRutUsuario(String usuarioRutUsuario) {
        this.usuarioRutUsuario = usuarioRutUsuario;
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
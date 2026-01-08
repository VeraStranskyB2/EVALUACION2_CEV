package cl.iplacex.sistema_cev.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EvaluacionDTO {

    
    @NotNull 
    private String idEvaluacion;

    @NotNull
    private LocalDateTime fechaEvaluacion;

    @NotNull
    @Size(max = 12)
    private String idProyecto;

    @NotNull
    private String usuarioRutUsuario;

   // Constructor vacío
    public EvaluacionDTO() {
    }

    // Constructor con Parámetros
    public EvaluacionDTO(String idEvaluacion, LocalDateTime fechaEvaluacion,
    String idProyecto, String usuarioRutUsuario) {

        if (idEvaluacion == null || fechaEvaluacion == null || idProyecto == null || usuarioRutUsuario == null) {
            throw new IllegalArgumentException("Ningún atributo puede ser nulo.");
        }
        this.idEvaluacion = idEvaluacion;
        this.fechaEvaluacion = fechaEvaluacion;
        this.idProyecto = idProyecto;
        this.usuarioRutUsuario = usuarioRutUsuario;
       
    }
    
    //Getter and Setter

    public String getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(String idEvaluacion) {
    this.idEvaluacion = idEvaluacion;
}

    public LocalDateTime getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(LocalDateTime fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getUsuarioRutUsuario() {
        return usuarioRutUsuario;
    }

    public void setUsuarioRutUsuario(String usuarioRutUsuario) {
        this.usuarioRutUsuario = usuarioRutUsuario;
    }

   
    @Override
    public String toString() {
        return "EvaluacionDTO{" +
                "idEvaluacion='" + idEvaluacion + '\'' +
                ", fechaEvaluacion=" + fechaEvaluacion +
                ", idProyecto='" + idProyecto + '\'' +
                ", usuarioRutUsuario='" + usuarioRutUsuario + '\'' +
                '}';
    }

   
}    


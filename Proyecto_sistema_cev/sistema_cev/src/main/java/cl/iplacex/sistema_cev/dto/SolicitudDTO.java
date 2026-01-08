package cl.iplacex.sistema_cev.dto;


import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public class SolicitudDTO {
    
    @NotNull
    private String idSolicitud;
    
    @NotNull
    private LocalDateTime fechaIngreso;
    
    @NotNull
    private String tipo;
    
    private String permisoEdificacion;
   
    @NotNull
    private String recepcionFinal;

    @NotNull
    private String idProyecto;

    
    @NotNull
    private String usuarioRutUsuario;

    // Constructor vacío
    public SolicitudDTO() {      
    }

    // Constructores
    public SolicitudDTO(String idSolicitud, LocalDateTime fechaIngreso, String tipo,
            String permisoEdificacion, String recepcionFinal, String idProyecto,
            String usuarioRutUsuario) {

                if (idSolicitud == null || fechaIngreso == null || tipo == null || 
                permisoEdificacion == null || recepcionFinal == null || 
                idProyecto == null || usuarioRutUsuario == null) {
                    
                throw new IllegalArgumentException("Ningún atributo puede ser nulo.");
            }
        
            this.idSolicitud = idSolicitud;
            this.fechaIngreso = fechaIngreso;
            this.tipo = tipo;
            this.permisoEdificacion = permisoEdificacion;
            this.recepcionFinal = recepcionFinal;
            this.idProyecto = idProyecto;
            this.usuarioRutUsuario = usuarioRutUsuario;

    }

    
    /* Getter and Setter, accesadores y modificadores de una clase */

    public String getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPermisoEdificacion() {
        return permisoEdificacion;
    }

    public void setPermisoEdificacion(String permisoEdificacion) {
        this.permisoEdificacion = permisoEdificacion;
    }

    public String getRecepcionFinal() {
        return recepcionFinal;
    }

    public void setRecepcionFinal(String recepcionFinal) {
        this.recepcionFinal = recepcionFinal;
    }

    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIProyecto(String idProyecto) {
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
        return "SolicitudDTO{" + "idSolicitud='" + idSolicitud + '\'' +
                ", fechaIngreso=" + fechaIngreso + ", tipo='" + tipo + '\'' +
                ", permisoEdificacion='" + permisoEdificacion + '\'' +
                ", recepcionFinal='" + recepcionFinal + '\'' + ", idProyecto=" + idProyecto +
                ", usuarioRutUsuario=" + usuarioRutUsuario +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SolicitudDTO)) return false;
        SolicitudDTO solicitudDTO = (SolicitudDTO) o;
        return idSolicitud.equals(solicitudDTO.idSolicitud);
    }

    @Override
    public int hashCode() {
        return idSolicitud.hashCode();
    }

    

}
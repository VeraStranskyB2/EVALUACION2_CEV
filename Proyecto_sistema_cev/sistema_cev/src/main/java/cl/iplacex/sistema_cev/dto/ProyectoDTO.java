package cl.iplacex.sistema_cev.dto;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public class ProyectoDTO {
    // Atributos
    @NotNull
    private  String idProyecto;

    @NotNull
    private String rolMatriz;

    @NotNull
    private String nombreProyecto;

    @NotNull
    private String direccion;

    @NotNull
    private Long numeroViviendas;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;

    // Constructor vacío
    public ProyectoDTO() {
        super();
    }

    // Constructor con Parámetros
    public ProyectoDTO(String idProyecto, String rolMatriz, String nombreProyecto,
                       String direccion, Long numeroViviendas, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.idProyecto = idProyecto;
        this.rolMatriz = rolMatriz;
        this.nombreProyecto = nombreProyecto;
        this.direccion = direccion;
        this.numeroViviendas = numeroViviendas;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters y Setters
    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getRolMatriz() {
        return rolMatriz;
    }

    public void setRolMatriz(String rolMatriz) {
        this.rolMatriz = rolMatriz;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Long getNumeroViviendas() {
        return numeroViviendas;
    }

    public void setNumeroViviendas(Long numeroViviendas) {
        this.numeroViviendas = numeroViviendas;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ProyectoDTO{" +
                "idProyecto='" + idProyecto + '\'' +
                ", rolMatriz='" + rolMatriz + '\'' +
                ", nombreProyecto='" + nombreProyecto + '\'' +
                ", direccion='" + direccion + '\'' +
                ", numeroViviendas=" + numeroViviendas +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProyectoDTO)) return false;
        ProyectoDTO that = (ProyectoDTO) o;
        return Objects.equals(idProyecto, that.idProyecto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProyecto);
    }
}



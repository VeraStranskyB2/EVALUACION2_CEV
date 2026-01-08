package cl.iplacex.sistema_cev.model;


import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Solicitud", uniqueConstraints = { @UniqueConstraint(columnNames = { "id_solicitud" }) })
public class Solicitud {

    @Id
    @Column(name = "id_solicitud", nullable = false, length = 12)
    @NotNull
    private String idSolicitud;

    @ManyToOne
    @JoinColumn(name = "cliente_rut_usuario", referencedColumnName = "usuario_rut_usuario")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "proyecto_id_proyecto", referencedColumnName = "id_proyecto")
    private Proyecto proyecto;

    @ManyToOne
    @JoinColumn(name = "estado_id_estado", referencedColumnName = "id_estado")
    private Estado estado;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "fecha_ingreso")
    private LocalDateTime fechaIngreso;

    @Column(name = "permiso_edificacion", length = 100)
    private String permisoEdificacion;

    @Column(name = "recepcion_final", length = 100)
    private String recepcionFinal;

    @Column(name = "tipo", length = 50)
    private String tipo;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;


    public Solicitud() {
        super();
    }

    // Constructores
    public Solicitud(String idSolicitud, LocalDateTime fechaIngreso, String tipo,
            String permisoEdificacion, String recepcionFinal, Proyecto proyecto,
            Estado estado, Cliente cliente) {

                if (idSolicitud == null || fechaIngreso == null || tipo == null || 
                permisoEdificacion == null || recepcionFinal == null || 
                proyecto == null || estado == null || cliente == null) {
                    
                throw new IllegalArgumentException("Ningún atributo puede ser nulo.");
            }
        
            this.idSolicitud = idSolicitud;
            this.fechaIngreso = fechaIngreso;
            this.tipo = tipo;
            this.permisoEdificacion = permisoEdificacion;
            this.recepcionFinal = recepcionFinal;
            this.proyecto = proyecto;
            this.cliente = cliente;

    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
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

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getupdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Solicitud{" + "idSolicitud='" + idSolicitud + '\'' +
                ", fechaIngreso=" + fechaIngreso + ", tipo='" + tipo + '\'' +
                ", permisoEdificacion='" + permisoEdificacion + '\'' +
                ", recepcionFinal='" + recepcionFinal + '\'' + ", proyecto=" + proyecto +
                ", estado=" + estado + ", cliente=" + cliente +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Solicitud)) return false;
        Solicitud solicitud = (Solicitud) o;
        return Objects.equals(idSolicitud, solicitud.idSolicitud);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSolicitud);
    }

}
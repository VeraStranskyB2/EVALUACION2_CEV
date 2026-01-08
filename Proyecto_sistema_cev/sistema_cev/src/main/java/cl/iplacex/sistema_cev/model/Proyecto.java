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
@Table(name = "Proyecto", uniqueConstraints = { @UniqueConstraint(columnNames = { "rol_matriz" }) })
public class Proyecto {

    // Atributos
    @Id
    @NotNull
    @Column(name = "id_proyecto", nullable = false, length = 12)
    private String idProyecto;

    @Column(name = "rol_matriz", nullable = false, length = 12)
    @NotNull
    private String rolMatriz;

    @Column(name = "nombre_proyecto", nullable = false, length = 45)
    private String nombreProyecto;

    @Column(name = "direccion", length = 45)
    private String direccion;

    @Column(name = "numero_viviendas")
    @NotNull
    private Long numeroViviendas;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Relacion con Comuna
    @ManyToOne
    @JoinColumn(name = "comuna_id_Comuna", nullable = false)
    private Comuna comuna;

    // Relacion con ZonaTermica
    @ManyToOne
    @JoinColumn(name = "zona_termica_id_zona_termica", nullable = false)
    private ZonaTermica zonaTermica;

    public Proyecto() {
        super();
    }

    // Constructores con Parámetros
    public Proyecto(String idProyecto, String rolMatriz, String nombreProyecto,
            String direccion, Long numeroViviendas, ZonaTermica zonaTermica, Comuna comuna) {
                if (idProyecto == null) {
                    throw new IllegalArgumentException("El idProyecto no puede ser nulo.");
                }
                if (rolMatriz == null) {
                    throw new IllegalArgumentException("El rolMatriz no puede ser nulo.");
                }
                if (numeroViviendas == null) {
                    throw new IllegalArgumentException("El numeroViviendas no puede ser nulo.");
                }
       
                this.idProyecto = idProyecto;
                this.rolMatriz = rolMatriz;
                this.nombreProyecto = nombreProyecto;
                this.direccion = direccion;
                this.numeroViviendas = numeroViviendas;
                this.zonaTermica = zonaTermica;
                this.comuna = comuna;

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

    /* Getter and Setter, accesadores y modificadores de la clase Proyecto */
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

    public ZonaTermica getZonaTermica() {
        return zonaTermica;
    }

    public void setZonaTermica(ZonaTermica zonaTermica) {
        this.zonaTermica = zonaTermica;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "idProyecto='" + idProyecto + '\'' + ", rolMatriz='" + rolMatriz + '\'' +
                ", nombreProyecto='" + nombreProyecto + '\'' + ", direccion='" + direccion + '\'' +
                ", numeroViviendas=" + numeroViviendas + ", zonaTermica=" + zonaTermica + ", comuna=" + comuna +
                ", createdAt=" + createdAt + ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Proyecto)) return false;
        Proyecto proyecto = (Proyecto) o;
        return Objects.equals(idProyecto, proyecto.idProyecto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProyecto);
    }
}

package cl.iplacex.sistema_cev.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;



@Entity
@Table(name = "Estado", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombre_estado" }) })
public class Estado {

    // Atributos
    /* tipo=calificacion o Precalificacion */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Generación automática de ID
    @NotNull
    @Column(name = "id_estado", nullable = false) 
    private Long idEstado;

    @Column(name = "nombre_estado", length = 50, nullable = false)
    @NotNull
    private String nombreEstado;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "color", length = 7)
    private String color;

    @Column(name = "activo", nullable = false)
    @NotNull
    private boolean activo;

    // Fecha de creación
    @Column(name = "created_at", updatable = false)
    @NotNull
    private LocalDateTime createdAt; 


    // Fecha de actualización
    @Column(name = "updated_at")
    @NotNull
    private LocalDateTime updatedAt; 

    //Métodos de Persistencia
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    } 

    @PreUpdate
    protected void onUpdate() {           
        updatedAt = LocalDateTime.now();
    } 




    // Constructor vacio
    public Estado() {
        super();
       
    }
    

    // Constructores con parámetros
    public Estado(@NotNull Long idEstado, String nombreEstado, String descripcion,
            String color, boolean activo) {
        super();
        this.idEstado = idEstado;
        this.nombreEstado = nombreEstado;
        this.descripcion = descripcion;
        this.color = color;
        this.activo = activo;
    }


    

    /* Getter and Setter, accesadores y modificadores de una clase */
    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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


    /*
     * Como los clientes van a ser varios vamos a ocupar una
     * sobreescritura @override
     */
    @Override
    public String toString() {
        return "Estado{" + "idEstado=" + idEstado + ", nombreEstado='" + nombreEstado + '\'' +
                ", descripcion='" + descripcion + '\'' + ", color='" + color + '\'' +
                ", activo=" + activo + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt +
                '}';
    }

    @Override
    //equals comparar si dos objetos son equivalentes.
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Estado estado = (Estado) obj;
        return idEstado.equals(estado.idEstado);
    }

    @Override
    //Hash devuelve un valor hash entero que representa el objeto
    public int hashCode() {
        return Objects.hash(idEstado);
    }

}

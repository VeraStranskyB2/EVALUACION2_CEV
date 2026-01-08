package cl.iplacex.sistema_cev.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "ZonaTermica", uniqueConstraints = { @UniqueConstraint(columnNames = { "tipo_zona_termica" }) })

public class ZonaTermica {

    // Atributos
    @Id
    @Column(name="id_zona_termica", nullable = false, length = 12)    
    private String idZonaTermica;

    @Column(name="tipo_zona_termica", nullable = false, length = 45)   
    private String tipoZonaTermica;

    @Column(name="temperatura", nullable = false) 
    private BigDecimal temperatura;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructor vacío
    public ZonaTermica() {
        

    }

    // Constructores
    public ZonaTermica(String idZonaTermica, String tipoZonaTermica, BigDecimal temperatura) {
        this.idZonaTermica = idZonaTermica;
        this.tipoZonaTermica = tipoZonaTermica;
        this.temperatura = temperatura;
    }

    // Métodos de persistencia
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
    public String getIdZonaTermica() {
        return idZonaTermica;
    }

    public void setIdZonaTermica(String idZonaTermica) {
        this.idZonaTermica = idZonaTermica;
    }

    public String getTipoZonaTermica() {
        return tipoZonaTermica;
    }

    public void setTipoZonaTermica(String tipoZonaTermica) {
        this.tipoZonaTermica = tipoZonaTermica;
    }

    public BigDecimal getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(BigDecimal temperatura) {
        this.temperatura = temperatura;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "ZonaTermica{" + "idZonaTermica='" + idZonaTermica + '\'' +
                ", tipoZonaTermica='" + tipoZonaTermica + '\'' +
                ", temperatura=" + temperatura +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
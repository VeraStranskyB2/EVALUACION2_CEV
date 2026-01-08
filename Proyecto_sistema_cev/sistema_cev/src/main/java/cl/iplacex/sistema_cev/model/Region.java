package cl.iplacex.sistema_cev.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Region", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombre_region" }) })
public class Region {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_region", nullable = false)
    @NotNull  
    private Long idRegion;

    @Column(name = "nombre_region", length = 45, nullable = false)
    @NotNull  
    private String nombreRegion;

    // Constructor vacío
    public Region() {
    }

    // Constructor con parámetros
    public Region(Long idRegion, String nombreRegion) {
        if (nombreRegion == null) {
            throw new IllegalArgumentException("El nombre de la región no puede ser nulo.");
        }
        this.idRegion = idRegion;
        this.nombreRegion = nombreRegion;
    }

    // Getters y Setters
    public Long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Long idRegion) {
        this.idRegion = idRegion;
    }

    public String getNombreRegion() {
        return nombreRegion;
    }

    public void setNombreRegion(String nombreRegion) {
        if (nombreRegion == null) {
            throw new IllegalArgumentException("El nombre de la región no puede ser nulo.");
        }

        this.nombreRegion = nombreRegion;
    }

    @Override
    public String toString() {
        return "Region{" +
                "idRegion=" + idRegion +
                ", nombreRegion='" + nombreRegion + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Region region = (Region) obj;
        return idRegion != null && idRegion.equals(region.idRegion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRegion);
    }
}



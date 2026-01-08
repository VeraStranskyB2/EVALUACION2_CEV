package cl.iplacex.sistema_cev.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "comuna", uniqueConstraints = { @UniqueConstraint(columnNames = { "nombre_comuna" }) })

public class Comuna {

    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Para auto-incrementar
    @Column(name="id_comuna")
    private Long id;

    @Column(name = "nombre_comuna", nullable = false)
    @NotNull
    private String nombreComuna;

    // Relación con Region
    @JsonIgnore // Ignora este campo en la serializacion JSON
    @ManyToOne
    @JoinColumn(name = "region_id_region", referencedColumnName = "id_region")
    private Region region;

    // Constructor vacio
    public Comuna() {
        super();

    }

    // Constructores
    public Comuna(@NotNull String nombreComuna, Long id, Region region) {

        this.nombreComuna = nombreComuna;
        this.region = region;
        this.id = id;

    }

    /* Getter and Setter, accesadores y modificadores de una clase */
    public Long getIdComuna() {
        return id;
    }

    public void setIdComuna(Long idComuna) {
        this.id = idComuna;
    }

    public String getNombreComuna() {
        return nombreComuna;
    }

    public void setNombreComuna(String nombreComuna) {
        this.nombreComuna = nombreComuna;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Comuna{" +
                "id=" + id +
                ", nombreComuna='" + nombreComuna + '\'' +
                ", region=" + region +
                '}';
    }

    

}
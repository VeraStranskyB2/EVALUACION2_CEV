package cl.iplacex.sistema_cev.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "SistemaSolarIluminacion", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "idSistemaSolarIluminacion" }) })

public class SistemaSolarIluminacion {

    // Atributos
    @Id
    @Column(name = "id_sistema_solar_iluminacionn", nullable = false)
    @NotNull
    private String idSistemaSolarIluminacion;

    @Column(name = "tipo_distribucion_iluminacion", length = 60)
    @NotNull
    private String tipoDistribucionIluminacion;

    @Column(name = "numero_paneles")
    @NotNull
    private Integer numeroPaneles;

    @Column(name = "marca", length = 20)
    @NotNull
    private String marca;

    @Column(name = "modelo", length = 20)
    @NotNull
    private String modelo;

    @Column(name = "superficie_total_ssi", precision = 10, scale = 2)
    @NotNull
    private BigDecimal superficieTotalSSI;

    @ManyToOne
    @JoinColumn(name = "evaluacion_id_evaluacion", nullable = false,
    foreignKey = @ForeignKey(name = "fk_sistema_solar_iluminacion_evaluacion"))

    private Evaluacion evaluacion;

    // Constructor vacío
    public SistemaSolarIluminacion() {
    }

    // Constructores con parámetros
    public SistemaSolarIluminacion(String idSistemaSolarIluminacion, String tipoDistribucionIluminacion,
            Integer numeroPaneles, String marca, String modelo, BigDecimal superficieTotalSSI, Evaluacion evaluacion) {

                if (idSistemaSolarIluminacion == null || tipoDistribucionIluminacion == null || 
            numeroPaneles == null || marca == null || modelo == null || 
            superficieTotalSSI == null || evaluacion == null) {
            throw new IllegalArgumentException("Ningún atributo puede ser nulo.");
                }
        
                this.idSistemaSolarIluminacion = idSistemaSolarIluminacion;
                this.tipoDistribucionIluminacion = tipoDistribucionIluminacion;
                this.numeroPaneles = numeroPaneles;
                this.marca = marca;
                this.modelo = modelo;
                this.superficieTotalSSI = superficieTotalSSI;
                this.evaluacion = evaluacion;

    }

    /* Getter and Setter, accesadores y modificadores de una clase */
    public String getIdSistemaSolarIluminacion() {
        return idSistemaSolarIluminacion;
    }

    public void setIdSistemaSolarIluminacion(String idSistemaSolarIluminacion) {
        this.idSistemaSolarIluminacion = idSistemaSolarIluminacion;
    }

    public String getTipoDistribucionIluminacion() {
        return tipoDistribucionIluminacion;
    }

    public void setTipoDistribucionIluminacion(String tipoDistribucionIluminacion) {
        this.tipoDistribucionIluminacion = tipoDistribucionIluminacion;
    }

    public Integer getNumeroPaneles() {
        return numeroPaneles;
    }

    public void setNumeroPaneles(Integer numeroPaneles) {
        this.numeroPaneles = numeroPaneles;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public BigDecimal getSuperficieTotalSSI() {
        return superficieTotalSSI;
    }

    public void setSuperficieTotalSSI(BigDecimal SuperficieTotalSSI) {
        this.superficieTotalSSI = SuperficieTotalSSI;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    @Override
    public String toString() {
        return "SistemaSolarIluminacion{" + "idSistemaSolarIluminacion='" + idSistemaSolarIluminacion + '\'' +
                ", tipoDistribucionIluminacion='" + tipoDistribucionIluminacion + '\'' +
                ", numeroPaneles=" + numeroPaneles + ", marca='" + marca + '\'' + ", modelo='" + modelo + '\'' +
                ", superficieTotalSSI=" + superficieTotalSSI + ", evaluacion=" + evaluacion +
                '}';
    }

    

}

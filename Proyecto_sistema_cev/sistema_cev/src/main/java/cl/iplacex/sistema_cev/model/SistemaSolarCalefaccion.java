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
@Table(name = "SistemaSolarCalefaccion", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "idSistemaSolarCalefaccion" }) })

public class SistemaSolarCalefaccion {

    // Atributos
    @Id
    @Column(name = "id_sistema_solar_calefaccion", nullable = false)
    @NotNull
    private String idSistemaSolarCalefaccion;

    @Column(name = "tipo_distribucion_solar", length = 60)
    @NotNull
    private String tipoDistribucionSolar;

    @Column(name = "sistema_aporte", length = 45)
    @NotNull
    private String sistemaAporte;

    @Column(name = "tipo_colector", length = 20)
    @NotNull
    private String tipoColector;

    @Column(name = "marca", length = 20)
    @NotNull
    private String marca;

    @Column(name = "modelo", length = 20)
    @NotNull
    private String modelo;

    @Column(name = "superficie_total_ssc", precision = 10, scale = 2)
    @NotNull
    private BigDecimal superficieTotalSSC;

    @ManyToOne
    @JoinColumn(name = "evaluacion_id_evaluacion", nullable = false,
    foreignKey = @ForeignKey(name = "fk_sistema_solar_calefaccion_evaluacion"))

    private Evaluacion evaluacion;

    // Constructor vacío
    public SistemaSolarCalefaccion() {
    }

    // Constructores
    public SistemaSolarCalefaccion(String idSistemaSolarCalefaccion, String tipoDistribucionSolar,
            String sistemaAporte, String tipoColector, String marca, String modelo, BigDecimal superficieTotalSSC,
            Evaluacion evaluacion) {
                
                if (idSistemaSolarCalefaccion == null || tipoDistribucionSolar == null || sistemaAporte == null || 
            tipoColector == null || marca == null || modelo == null || 
            superficieTotalSSC == null || evaluacion == null) {
            throw new IllegalArgumentException("Ningún atributo puede ser nulo.");
                }
       
                this.idSistemaSolarCalefaccion = idSistemaSolarCalefaccion;
                this.tipoDistribucionSolar = tipoDistribucionSolar;
                this.sistemaAporte = sistemaAporte;
                this.tipoColector = tipoColector;
                this.marca = marca;
                this.modelo = modelo;
                this.superficieTotalSSC = superficieTotalSSC;
                this.evaluacion = evaluacion;

    }

    /* Getter and Setter, accesadores y modificadores de una clase */
    public String getIdSistemaSolarCalefaccion() {
        return idSistemaSolarCalefaccion;
    }

    public void setIdSistemaSolarCalefaccion(String idSistemaSolarCalefaccion) {
        this.idSistemaSolarCalefaccion = idSistemaSolarCalefaccion;
    }

    public String getTipoDistribucionSolar() {
        return tipoDistribucionSolar;
    }

    public void setTipoDistribucionSolar(String tipoDistribucionSolar) {
        this.tipoDistribucionSolar = tipoDistribucionSolar;
    }

    public String getSistemaAporte() {
        return sistemaAporte;
    }

    public void setSistemaAporte(String sistemaAporte) {
        this.sistemaAporte = sistemaAporte;
    }

    public String getTipoColector() {
        return tipoColector;
    }

    public void setTipoColector(String tipoColector) {
        this.tipoColector = tipoColector;
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

    public BigDecimal getSuperficieTotalSSC() {
        return superficieTotalSSC;
    }

    public void setSuperficieTotalSSC(BigDecimal superficieTotalSSC) {
        this.superficieTotalSSC = superficieTotalSSC;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    @Override
    public String toString() {
        return "SistemaSolarCalefaccion{" + "idSistemaSolarCalefaccion='" + idSistemaSolarCalefaccion + '\'' +
                ", tipoDistribucionSolar='" + tipoDistribucionSolar + '\'' + ", sistemaAporte='" + sistemaAporte + '\''
                +
                ", tipoColector='" + tipoColector + '\'' + ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' + ", superficieTotalSSC=" + superficieTotalSSC + ", evaluacion="
                + evaluacion +
                '}';
    }

}

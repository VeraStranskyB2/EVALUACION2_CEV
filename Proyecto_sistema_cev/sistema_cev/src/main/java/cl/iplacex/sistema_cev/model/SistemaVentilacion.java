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
@Table(name = "SistemaVentilacion", uniqueConstraints = { @UniqueConstraint(columnNames = { "idSistemaVentilacion" }) })

public class SistemaVentilacion {

    // Atributos
    @Id
    @Column(name = "id_sistema_ventilacion", nullable = false)
    @NotNull
    private String idSistemaVentilacion;

    @Column(name = "tipo_sistema_ventilacion", length = 45)
    @NotNull
    private String tipoSistemaVentilacion;

    @Column(name = "potencia_ventilador", precision = 10, scale = 2)
    @NotNull
    private BigDecimal potenciaVentilador;

    @Column(name = "potencia_recuperador", precision = 10, scale = 2)
    @NotNull
    private BigDecimal potenciaRecuperador;

    @Column(name = "consumo_energia", precision = 10)
    @NotNull
    private BigDecimal consumoEnergia;

    @Column(name = "marca", length = 20)
    @NotNull
    private String marca;

    @Column(name = "modelo", length = 20)
    @NotNull
    private String modelo;

    @Column(name = "potencia_nominal_sv", precision = 10, scale = 2)
    @NotNull
    private BigDecimal potenciaNominalSV;

    @ManyToOne
    @JoinColumn(name = "evaluacion_id_evaluacion", nullable = false,
    foreignKey = @ForeignKey(name = "fk_sistema_ventilacion_evaluacion"))

    private Evaluacion evaluacion;

    // Constructor vacío
    public SistemaVentilacion() {
    }

    // Constructores
    public SistemaVentilacion(String idSistemaVentilacion, String tipoSistemaVentilacion, BigDecimal potenciaVentilador,
            BigDecimal potenciaRecuperador, BigDecimal consumoEnergia, String marca, String modelo,
            BigDecimal potenciaNominalSV, Evaluacion evaluacion) {
        super();
        this.idSistemaVentilacion = idSistemaVentilacion;
        this.tipoSistemaVentilacion = tipoSistemaVentilacion;
        this.potenciaVentilador = potenciaVentilador;
        this.potenciaRecuperador = potenciaRecuperador;
        this.consumoEnergia = consumoEnergia;
        this.marca = marca;
        this.modelo = modelo;
        this.potenciaNominalSV = potenciaNominalSV;

    }

    /* Getter and Setter, accesadores y modificadores de una clase */
    public String getIdSistemaVentilacion() {
        return idSistemaVentilacion;
    }

    public void setIdSistemaVentilacion(String idSistemaVentilacion) {
        this.idSistemaVentilacion = idSistemaVentilacion;
    }

    public String getTipoSistemaVentilacion() {
        return tipoSistemaVentilacion;
    }

    public void setTipoSistemaVentilacion(String tipoSistemaVentilacion) {
        this.tipoSistemaVentilacion = tipoSistemaVentilacion;
    }

    public BigDecimal getPotenciaVentilador() {
        return potenciaVentilador;
    }

    public void setPotenciaVentilador(BigDecimal potenciaVentilador) {
        this.potenciaVentilador = potenciaVentilador;
    }

    public BigDecimal getPotenciaRecuperador() {
        return potenciaRecuperador;
    }

    public void setPotenciaRecuperador(BigDecimal potenciaRecuperador) {
        this.potenciaRecuperador = potenciaRecuperador;
    }

    public BigDecimal getConsumoEnergia() {
        return consumoEnergia;
    }

    public void setConsumoEnergia(BigDecimal consumoEnergia) {
        this.consumoEnergia = consumoEnergia;
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

    public BigDecimal getPotenciaNominalSV() {
        return potenciaNominalSV;
    }

    public void setPotenciaNominalSV(BigDecimal potenciaNominalSV) {
        this.potenciaNominalSV = potenciaNominalSV;
    }

    

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    @Override
    public String toString() {
        return "SistemaVentilacion{" + "idSistemaVentilacion='" + idSistemaVentilacion + '\'' +
                ", tipoSistemaVentilacion='" + tipoSistemaVentilacion + '\'' + ", potenciaVentilador="
                + potenciaVentilador +
                ", potenciaRecuperador=" + potenciaRecuperador + ", consumoEnergia=" + consumoEnergia +
                ", marca='" + marca + '\'' + ", modelo='" + modelo + '\'' + ", potenciaNominalSV=" + potenciaNominalSV +
                ", evaluacion=" + evaluacion +
                '}';
    }

}

package cl.iplacex.sistema_cev.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "SistemaACS", uniqueConstraints = { @UniqueConstraint(columnNames = { "id_sistema_acs" }) })


public class SistemaACS {

    // Atributos Sistema Agua caliente sanitaria
    @Id
    @Column(name = "id_sistema_acs", nullable = false)
    @NotNull
    private String idSistemaACS;

    @Column(name = "tipo_distribucion", length = 45)
    @NotNull
    private String tipoDistribucion;

    @Column(name = "sistema_encendido", length = 20)
    @NotNull
    private String sistemaEncendido;

    @Column(name = "correcion_distribucion", length = 45)
    @NotNull
    private String correccionDistribucion;
 
    @Column(name = "tiene_estanque", nullable = false)
    @NotNull
    private String tieneEstanque;

    @Column(name = "marca", length = 20)
    @NotNull
    private String marca;

    @Column(name = "modelo", length = 20)
    @NotNull
    private String modelo;

    @Column(name = "potencia_nominal_acs", precision = 10, scale = 2)
    @NotNull
    private BigDecimal potenciaNominalACS;

    @ManyToOne
    @JoinColumn(name = "evaluacion_id_evaluacion", nullable = false,
    foreignKey = @ForeignKey(name = "fk_sistema_acs_evaluacion"))
    private Evaluacion evaluacion;

    // Constructor vacío
    public SistemaACS() {
    }

    // Constructores
    public SistemaACS(String idSistemaACS, String tipoDistribucion, String sistemaEncendido,
            String correccionDistribucion,
            String tieneEstanque, String marca, String modelo, BigDecimal potenciaNominalACS, Evaluacion evaluacion) {

                if (idSistemaACS == null || tipoDistribucion == null || sistemaEncendido == null || 
                correccionDistribucion == null || tieneEstanque == null || marca == null || 
                modelo == null || potenciaNominalACS == null || evaluacion == null) {
                throw new IllegalArgumentException("Ningún atributo puede ser nulo.");
            }
        
            this.idSistemaACS = idSistemaACS;
            this.tipoDistribucion = tipoDistribucion;
            this.sistemaEncendido = sistemaEncendido;
            this.correccionDistribucion = correccionDistribucion;
            this.tieneEstanque = tieneEstanque;
            this.marca = marca;
            this.modelo = modelo;
            this.potenciaNominalACS = potenciaNominalACS;
            this.evaluacion = evaluacion;

    }

    /* Getter and Setter, accesadores y modificadores de una clase */
    public String getIdSistemaACS() {
        return idSistemaACS;
    }

    public void setIdSistemaACS(String idSistemaACS) {
        this.idSistemaACS = idSistemaACS;
    }

    public String getTipoDistribucion() {
        return tipoDistribucion;
    }

    public void setTipoDistribucion(String tipoDistribucion) {
        this.tipoDistribucion = tipoDistribucion;
    }

    public String getSistemaEncendido() {
        return sistemaEncendido;
    }

    public void setSistemaEncendido(String sistemaEncendido) {
        this.sistemaEncendido = sistemaEncendido;
    }

    public String getCorreccionDistribucion() {
        return correccionDistribucion;
    }

    public void setCorreccionDistribucion(String correccionDistribucion) {
        this.correccionDistribucion = correccionDistribucion;
    }

    public String getTieneEstanque() {
        return tieneEstanque;
    }

    public void setTieneEstanque(String tieneEstanque) {
        this.tieneEstanque = tieneEstanque;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getmodelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public BigDecimal getPotenciaNominalACS() {
        return potenciaNominalACS;
    }

    public void setPotenciaNominalACS(BigDecimal potenciaNominalACS) {
        this.potenciaNominalACS = potenciaNominalACS;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    @Override
    public String toString() {
        return "SistemaACS{" +
                "idSistemaACS='" + idSistemaACS + '\'' +
                ", tipoDistribucion='" + tipoDistribucion + '\'' +
                ", sistemaEncendido='" + sistemaEncendido + '\'' +
                ", correccionDistribucion='" + correccionDistribucion + '\'' +
                ", tieneEstanque='" + tieneEstanque + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", potenciaNominalACS=" + potenciaNominalACS +
                ", evaluacion=" + evaluacion +
                '}';
    }

}

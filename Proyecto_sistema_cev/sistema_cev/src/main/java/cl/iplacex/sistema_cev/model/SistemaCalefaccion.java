package cl.iplacex.sistema_cev.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.Column;

@Entity
@Table(name = "SistemaCalefaccion", uniqueConstraints = { @UniqueConstraint(columnNames = { "id_sistema_calefaccion" }) })
public class SistemaCalefaccion {

    // Atributos
    @Id
    @Column(name = "id_sistema_calefaccion", nullable = false)
    @NotNull
    private String idSistemaCalefaccion;

    @Column(name = "tipo_distribucion", length = 60)
    @NotNull
    private String tipoDistribucion;

    @Column(name = "sistema_control", length = 45)
    @NotNull
    private String sistemaControl;

    @Column(name = "marca", length = 20)
    @NotNull
    private String marca;

    @Column(name = "modelo", length = 20)
    @NotNull
    private String modelo;

    @Column(name = "potencia_nominal_sc", precision = 10, scale = 2)
    @NotNull
    private BigDecimal potenciaNominalSC;

    @ManyToOne
    @JoinColumn(name = "evaluacion_id_evaluacion", nullable = false,
    foreignKey = @ForeignKey(name = "fk_sistema_calefaccion_evaluacion"))


    private Evaluacion evaluacion;

    // Constructor vacío
    public SistemaCalefaccion() {
    }

    // Constructores
    public SistemaCalefaccion(String idSistemaCalefaccion, String tipoDistribucion, String sistemaControl,
            String marca, String modelo, BigDecimal potenciaNominalSC, Evaluacion evaluacion) {
                
                if (idSistemaCalefaccion == null || tipoDistribucion == null || sistemaControl == null || 
            marca == null || modelo == null || potenciaNominalSC == null || evaluacion == null) {
            throw new IllegalArgumentException("Ningún atributo puede ser nulo.");
                }
       
                this.idSistemaCalefaccion = idSistemaCalefaccion;
                this.tipoDistribucion = tipoDistribucion;
                this.sistemaControl = sistemaControl;
                this.marca = marca;
                this.modelo = modelo;
                this.potenciaNominalSC = potenciaNominalSC;
                this.evaluacion = evaluacion;

    }

    /* Getter and Setter, accesadores y modificadores de una clase */
    
    public String getIdSistemaCalefaccion() {
        return idSistemaCalefaccion;
    }

    public void setIdSistemaCalefaccion(String idSistemaCalefaccion) {
        this.idSistemaCalefaccion = idSistemaCalefaccion;
    }

    public String getTipoDistribucion() {
        return tipoDistribucion;
    }

    public void setTipoDistribucion(String tipoDistribucion) {
        this.tipoDistribucion = tipoDistribucion;
    }

    public String getSistemaControl() {
        return sistemaControl;
    }

    public void setSistemaControl(String sistemaControl) {
        this.sistemaControl = sistemaControl;
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

    public BigDecimal getPotenciaNominalSC() {
        return potenciaNominalSC;
    }

    public void setPotenciaNominalSC(BigDecimal potenciaNominalSC) {
        this.potenciaNominalSC = potenciaNominalSC;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaliacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    @Override
    public String toString() {
        return "SistemaCalefaccion{" + "idSistemaCalefaccion='" + idSistemaCalefaccion + '\'' +
                ", tipoDistribucion='" + tipoDistribucion + '\'' + ", sistemaControl='" +
                sistemaControl + '\'' + ", marca='" + marca + '\'' + ", modelo='" + modelo + '\'' +
                ", potenciaNominalSC=" + potenciaNominalSC + ", evaluacion=" + evaluacion +
                '}';
    }
}

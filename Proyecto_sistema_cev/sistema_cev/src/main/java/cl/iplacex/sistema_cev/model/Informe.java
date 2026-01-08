package cl.iplacex.sistema_cev.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

//Indica que la clase es una entidad JPA
@Entity
//Mapea la clase a la tabla Informe y establece una restriccion única de idInforme
@Table(name = "Informe", uniqueConstraints = { @UniqueConstraint(columnNames = { "id_informe" }) })

public class Informe {

    // Atributos
    //Indica que este campo es la clave Primaria
    @Id
    @Column(name = "id_informe", nullable = false, length = 12)
    @NotNull
    private String idInforme;

    //Almacena la fecha de generacion del informe
    @Column(name = "fecha_generacion", nullable = false)
    @NotNull
    private LocalDateTime fechaGeneracion;


    @Column(name = "potencia_nominal_sc", nullable = false, precision = 10, scale = 2)
    @NotNull
    private BigDecimal potenciaNominalSC;

    @Column(name = "potencia_nominal_sv", nullable = false, precision = 10, scale = 2)
    @NotNull
    private BigDecimal potenciaNominalSV;

    @Column(name = "potencia_nominal_acs", nullable = false, precision = 10, scale = 2)
    private BigDecimal potenciaNominalACS;

    @Column(name = "superficie_total_ssc", nullable = false, precision = 10, scale = 2)
    @NotNull
    private BigDecimal superficieTotalSSC;

    @Column(name = "superficie_total_ssi", nullable = false, precision = 10, scale = 2)
    @NotNull
    private BigDecimal superficieTotalSSI;



    @ManyToOne
    @JoinColumn(name = "evaluacion_id_evaluacion", nullable = false, 
    foreignKey = @ForeignKey(name = "fk_informe_evaluacion"))
    private Evaluacion evaluacion;

    @ManyToOne
    @JoinColumn(name = "id_sistema_calefaccion", foreignKey = @ForeignKey(name = "fk_informe_calefaccion"))
    private SistemaCalefaccion sistemaCalefaccion;

    @ManyToOne
    @JoinColumn(name = "id_sistema_ventilacion", foreignKey = @ForeignKey(name = "fk_informe_ventilacion"))
    private SistemaVentilacion sistemaVentilacion;

    @ManyToOne
    @JoinColumn(name = "id_sistema_acs", foreignKey = @ForeignKey(name = "fk_informe_acs"))
    private SistemaACS sistemaACS;

    @ManyToOne
    @JoinColumn(name = "id_sistema_solar_calefaccion", foreignKey = @ForeignKey(name = "fk_informe_solar_calefaccion"))
    private SistemaSolarCalefaccion sistemaSolarCalefaccion;

    @ManyToOne
    @JoinColumn(name = "id_sistema_solar_iluminacion", foreignKey = @ForeignKey(name = "fk_informe_solar_iluminacion"))
    private SistemaSolarIluminacion sistemaSolarIluminacion;

    // Campo para la fecha de creación
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Campo para la fecha de última actualización
    @Column(name = "updatedAt", nullable = false)
    private LocalDateTime updatedAt;

    // Constructor vacío
    public Informe() {
        
    }

    // Constructores con Parametros
    public Informe(String idInforme, LocalDateTime fechaGeneracion, BigDecimal potenciaNominalSC,
            BigDecimal potenciaNominalSV, BigDecimal potenciaNominalACS, BigDecimal superficieTotalSSC,
            BigDecimal superficieTotalSSI, Evaluacion evaluacion, SistemaCalefaccion sistemaCalefaccion,
            SistemaVentilacion sistemaVentilacion, SistemaACS sistemaACS, 
            SistemaSolarCalefaccion sistemaSolarCalefaccion, SistemaSolarIluminacion sistemaSolarIluminacion) {
                
                if (idInforme == null || fechaGeneracion == null || potenciaNominalSC == null || 
            potenciaNominalSV == null || potenciaNominalACS == null || 
            superficieTotalSSC == null || superficieTotalSSI == null || 
            evaluacion == null) {
            throw new IllegalArgumentException("Ningún atributo puede ser nulo.");
            }

      
            this.idInforme = idInforme;
            this.fechaGeneracion = fechaGeneracion;
            this.potenciaNominalSC = potenciaNominalSC;
            this.potenciaNominalSV = potenciaNominalSV;
            this.potenciaNominalACS = potenciaNominalACS;
            this.superficieTotalSSC = superficieTotalSSC;
            this.superficieTotalSSI = superficieTotalSSI;
            this.evaluacion = evaluacion;
            this.sistemaCalefaccion = sistemaCalefaccion;
            this.sistemaVentilacion = sistemaVentilacion;
            this.sistemaACS = sistemaACS;
            this.sistemaSolarCalefaccion = sistemaSolarCalefaccion;
            this.sistemaSolarIluminacion = sistemaSolarIluminacion;
 
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

    /* Getter and Setter, accesadores y modificadores de una clase */
    public String getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(String idInforme) {
        this.idInforme = idInforme;
    }

    public LocalDateTime getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(LocalDateTime fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public BigDecimal getPotenciaNominalSC() {
        return potenciaNominalSC;
    }

    public void setPotenciaNominalSC(BigDecimal potenciaNominalSC) {
        this.potenciaNominalSC = potenciaNominalSC;
    }

    public BigDecimal getPotenciaNominalSV() {
        return potenciaNominalSV;
    }

    public void setPotenciaNominalSV(BigDecimal potenciaNominalSV) {
        this.potenciaNominalSV = potenciaNominalSV;
    }

    public BigDecimal getPotenciaNominalACS() {
        return potenciaNominalACS;
    }

    public void setPotenciaNominalACS(BigDecimal potenciaNominalACS) {
        this.potenciaNominalACS = potenciaNominalACS;
    }

    public BigDecimal getSuperficieTotalSSC() {
        return superficieTotalSSC;
    }

    public void setSuperficieTotalSSC(BigDecimal superficieTotalSSC) {
        this.superficieTotalSSC = superficieTotalSSC;
    }

    public BigDecimal getSuperficieTotalSSI() {
        return superficieTotalSSI;
    }

    public void setSuperficieTotalSSI(BigDecimal superficieTotalSSI) {
        this.superficieTotalSSI = superficieTotalSSI;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public SistemaCalefaccion getSistemaCalefaccion() {
        return sistemaCalefaccion;
    }

    public void setSistemaCalefaccion(SistemaCalefaccion sistemaCalefaccion) {
        this.sistemaCalefaccion = sistemaCalefaccion;
    }

    public SistemaVentilacion getSistemaVentilacion() {
        return sistemaVentilacion;
    }

    public void setSistemaVentilacion(SistemaVentilacion sistemaVentilacion) {
        this.sistemaVentilacion = sistemaVentilacion;
    }

    public SistemaACS getSistemaACS() {
        return sistemaACS;
    }

    public void setSistemaACS(SistemaACS sistemaACS) {
        this.sistemaACS = sistemaACS;
    }

    public SistemaSolarCalefaccion getSistemaSolarCalefaccion() {
        return sistemaSolarCalefaccion;
    }

    public void setSistemaSolarCalefaccion(SistemaSolarCalefaccion sistemaSolarCalefaccion) {
        this.sistemaSolarCalefaccion = sistemaSolarCalefaccion;
    }

    public SistemaSolarIluminacion getSistemaSolarIluminacion() {
        return sistemaSolarIluminacion;
    }

    public void setSistemaSolarIluminacion(SistemaSolarIluminacion sistemaSolarIluminacion) {
        this.sistemaSolarIluminacion = sistemaSolarIluminacion;
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


   @Override
    public String toString() {
        return "Informe{" + "idInforme='" + idInforme + '\'' +
                ", fechaGeneracion=" + fechaGeneracion + ", potenciaNominalSC=" + potenciaNominalSC +
                ", potenciaNominalSV=" + potenciaNominalSV + ", potenciaNominalACS=" + potenciaNominalACS +
                ", superficieTotalSSC=" + superficieTotalSSC + ", superficieTotalSSI=" + superficieTotalSSI +
                ", evaluacion=" + evaluacion + ", sistemaCalefaccion=" + sistemaCalefaccion +
                ", sistemaVentilacion=" + sistemaVentilacion + ", sistemaACS=" + sistemaACS +
                ", sistemaSolarCalefaccion=" + sistemaSolarCalefaccion + ", sistemaSolarIluminacion=" + sistemaSolarIluminacion +
                ", createdAt=" + createdAt + ", updatedAt=" + updatedAt +
                '}';
                
    }

       
}

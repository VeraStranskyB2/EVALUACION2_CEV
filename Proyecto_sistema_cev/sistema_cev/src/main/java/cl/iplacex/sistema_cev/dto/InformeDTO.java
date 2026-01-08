package cl.iplacex.sistema_cev.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;



public class InformeDTO {
    // Atributos
   
    
    @NotNull
    private String idInforme;

    //Almacena la fecha de generacion del informe
   
    @NotNull
    private LocalDateTime fechaGeneracion;

    @NotNull
    private BigDecimal potenciaNominalSC;
    
    @NotNull
    private BigDecimal potenciaNominalSV;

    @NotNull
    private BigDecimal potenciaNominalACS;
    
    @NotNull
    private BigDecimal superficieTotalSSC;
    
    @NotNull
    private BigDecimal superficieTotalSSI;


    
    // Constructor vacío
    public InformeDTO() {
        
    }

    // Constructores con Parametros
    public InformeDTO(String idInforme, LocalDateTime fechaGeneracion, BigDecimal potenciaNominalSC,
            BigDecimal potenciaNominalSV, BigDecimal potenciaNominalACS, BigDecimal superficieTotalSSC,
            BigDecimal superficieTotalSSI) {
                
                if (idInforme == null || fechaGeneracion == null || potenciaNominalSC == null || 
            potenciaNominalSV == null || potenciaNominalACS == null || 
            superficieTotalSSC == null || superficieTotalSSI == null) {
            throw new IllegalArgumentException("Ningún atributo puede ser nulo.");
            }

      
            this.idInforme = idInforme;
            this.fechaGeneracion = fechaGeneracion;
            this.potenciaNominalSC = potenciaNominalSC;
            this.potenciaNominalSV = potenciaNominalSV;
            this.potenciaNominalACS = potenciaNominalACS;
            this.superficieTotalSSC = superficieTotalSSC;
            this.superficieTotalSSI = superficieTotalSSI;
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

    

   @Override
    public String toString() {
        return "InformeDTO{" + "idInforme='" + idInforme + '\'' +
                ", fechaGeneracion=" + fechaGeneracion + ", potenciaNominalSC=" + potenciaNominalSC +
                ", potenciaNominalSV=" + potenciaNominalSV + ", potenciaNominalACS=" + potenciaNominalACS +
                ", superficieTotalSSC=" + superficieTotalSSC + ", superficieTotalSSI=" + superficieTotalSSI +                
                '}';                
    }
      
}




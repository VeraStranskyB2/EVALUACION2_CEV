package cl.iplacex.sistema_cev.model;

import java.time.LocalDateTime;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "evaluacion")

public class Evaluacion {

    @Id
    @Column(name = "id_evaluacion", length = 12, nullable = false)
    @NotNull
    private String idEvaluacion;


    @Column(name = "fecha_evaluacion", nullable = false)
    @NotNull
    private LocalDateTime fechaEvaluacion;

      
    // Relación con la tabla Proyecto
    @ManyToOne
    @JoinColumn(name = "proyecto_id_proyecto", referencedColumnName = "id_proyecto")
    private Proyecto proyecto;

    // Relación con la tabla Evaluador
    @ManyToOne
    @JoinColumn(name = "evaluador_rut_usuario", referencedColumnName = "usuario_rut_usuario")
    private Evaluador evaluador;
    
    
        
        
        
            
        
            // Constructor vacío
            public Evaluacion() {
            }
        
            // Constructor con Parámnetros
            public Evaluacion(String idEvaluacion, LocalDateTime fechaEvaluacion, Proyecto proyecto, Evaluador evaluador) {
                if (idEvaluacion == null || fechaEvaluacion == null || proyecto == null || evaluador == null) {
                    throw new IllegalArgumentException("Ningún atributo puede ser nulo.");
                }
                this.idEvaluacion = idEvaluacion;
                this.fechaEvaluacion = fechaEvaluacion;
                this.proyecto =proyecto;
        this.evaluador = evaluador;
    }

    // Getters y Setters
    public String getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(String idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public LocalDateTime getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public void setFechaEvaluacion(LocalDateTime fechaEvaluacion) {
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Evaluador getEvaluador() {
        return evaluador;
    }

    public void setEvaluador(Evaluador evaluador) {
        if (evaluador == null) {
            throw new IllegalArgumentException("El evaluador no puede ser nulo.");
        }
        
        this.evaluador = evaluador;
    }

       
    @Override
    public String toString() {
        return "Evaluacion{" + "idEvaluacion='" + idEvaluacion + '\'' +
                ", fechaEvaluacion=" + fechaEvaluacion + ", proyecto=" + proyecto + '\'' +
                ", evaluador=" + evaluador +
                '}';
    }

   
}
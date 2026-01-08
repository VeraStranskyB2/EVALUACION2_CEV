package cl.iplacex.sistema_cev.model;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Etiqueta")

public class Etiqueta {

    //Define Lista de calificaciones existentes
    private static final List<String> CALIFICACIONES_VALIDAS =
    Arrays.asList("A+", "A", "B", "C", "D", "E", "F", "G");

    // Atributo
    @Id
    @NotNull
    @Column(name = "id_etiqueta", nullable = false, length = 12)
    private String idEtiqueta;

    @Column(name = "calificacion", nullable = false, length = 2)
    @NotNull
    private String calificacion;

    @Column(name = "fecha_emision", nullable = false,
    columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    @NotNull
    private LocalDateTime fechaEmision;

    @ManyToOne
    @JoinColumn(name = "evaluacion_id_ealuacion", nullable = false,
    foreignKey = @ForeignKey(name = "fk_etiqueta_evaluacion"))
    @NotNull
    private Evaluacion evaluacion;

    // Constructor vacío
    public Etiqueta() {
        //Se asigna por defecto la fecha actual
        this.fechaEmision = LocalDateTime.now();

    }

    // Constructor con Parametros
    public Etiqueta(String idEtiqueta, String calificacion,Evaluacion evaluacion) {
        super();
        this.idEtiqueta = idEtiqueta;
        // Verificar la calificación directamente aquí
        if (!CALIFICACIONES_VALIDAS.contains(calificacion)) {
            throw new IllegalArgumentException("Calificación inválida: " +
                    calificacion + ". Debe ser una de " + CALIFICACIONES_VALIDAS);
        }
        this.calificacion = calificacion;
        this.fechaEmision = LocalDateTime.now();
        

        // Validar que evaluacion no sea nulo
        if (evaluacion == null) {
            throw new IllegalArgumentException("La evaluación no puede ser nula.");
        }
        this.evaluacion = evaluacion;
    }

    

    /* Getter and Setter, accesadores y modificadores de una clase */
    public String getIdEtiqueta() {
        return idEtiqueta;
    }

    public void setIdEtiqueta(String idEtiqueta) {
        this.idEtiqueta = idEtiqueta;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        if (!CALIFICACIONES_VALIDAS.contains(calificacion)) {
            throw new IllegalArgumentException("Calificación inválida: " +
            calificacion + ". Debe ser una de " + CALIFICACIONES_VALIDAS);
        }
        this.calificacion = calificacion;

    }    

    public LocalDateTime getFechaEmision() {
        return fechaEmision;
    }

    public void setfechaEmision(LocalDateTime fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        if (evaluacion == null) {
            throw new IllegalArgumentException("La evaluación no puede ser nula.");
        }
        this.evaluacion = evaluacion;
    }

    @Override
    public String toString() {
        return "Etiqueta{" + "idEtiqueta='" + idEtiqueta + '\'' +
                ", calificacion='" + calificacion + '\'' + ", fechaEmision=" + fechaEmision +
                ", evaluacion=" + evaluacion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Etiqueta)) return false;
        Etiqueta etiqueta = (Etiqueta) o;
        return Objects.equals(idEtiqueta, etiqueta.idEtiqueta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEtiqueta);
    }


}  

 


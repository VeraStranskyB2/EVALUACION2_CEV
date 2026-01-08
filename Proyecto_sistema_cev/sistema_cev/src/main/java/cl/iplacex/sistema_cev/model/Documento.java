package cl.iplacex.sistema_cev.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Documento", uniqueConstraints = { @UniqueConstraint(columnNames = { "id_documento" }) })

// Atributos
/* tipo=plano, certificado, EETT */
public class Documento {

    @Id
    @Column(name = "id_documento", nullable = false, length = 12)
    @NotNull
    private String idDocumento;

    @Column(name = "tipo", nullable = false, length = 45)
    @NotNull
    private String tipo;

    @Column(name = "fecha_ingreso", nullable = false)
    @NotNull
    private LocalDateTime fechaIngreso;

    @ManyToOne
    @JoinColumn(name = "solicitud_id_Solicitud", nullable = false)
    private Solicitud solicitud;

    public Documento() {
        super();
    }

    // Constructores
    public Documento(@NotNull String idDocumento, String tipo, LocalDateTime fechaIngreso, Solicitud solicitud) {
        super();
        this.idDocumento = idDocumento;
        this.tipo = tipo;
        this.fechaIngreso = fechaIngreso;
        this.solicitud = solicitud;
    }

    /* Getter and Setter, accesadores y modificadores de una clase */
    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Solicitud getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(Solicitud solicitud) {
        this.solicitud = solicitud;
    }

    @Override
    public String toString() {
        return "Documento{" +
                "idDocumento='" + idDocumento + '\'' +
                ", tipo='" + tipo + '\'' +
                ", fechaIngreso=" + fechaIngreso +
                ", solicitud=" + solicitud +
                '}';
    }

}

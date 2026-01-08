package cl.iplacex.sistema_cev.model;

import java.time.LocalDateTime;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Evaluador")
public class Evaluador {

    @Id
    @Column(name = "usuario_rut_usuario", length = 12, nullable = false)
    private String usuarioRutUsuario;

    @Column(name = "nombre_evaluador", length = 45, nullable = false)
    private String nombreEvaluador;

    @Column(name = "apellidos", nullable = false, length = 45)
    private String apellidos;

    @Column(name = "profesion", length = 60)
    private String profesion;

    @Column(name = "fecha_acreditacion", nullable = false)
    private LocalDateTime fechaAcreditacion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_rut_usuario", referencedColumnName = "rut_usuario",
                insertable = false, updatable = false)
    private Usuario usuario;
    
    // Constructor vacío
    public Evaluador() {
    }

    // Constructor con parámetros
    public Evaluador(String usuarioRutUsuario, String nombreEvaluador, String apellidos,
                     String profesion, LocalDateTime fechaAcreditacion, Usuario usuario) {
        this.usuarioRutUsuario = usuarioRutUsuario;
        this.nombreEvaluador = nombreEvaluador;
        this.apellidos = apellidos;
        this.profesion = profesion;
        this.fechaAcreditacion = fechaAcreditacion;
        this.usuario = usuario;
    }

    // Getters and Setters
    public String getUsuarioRutUsuario() {
        return usuarioRutUsuario;
    }

    public void setUsuarioRutUsuario(String usuarioRutUsuario) {
        this.usuarioRutUsuario = usuarioRutUsuario;
    }
    
    public String getNombreEvaluador() {
        return nombreEvaluador;
    }

    public void setNombreEvaluador(String nombreEvaluador) {
        this.nombreEvaluador = nombreEvaluador;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public LocalDateTime getFechaAcreditacion() {
        return fechaAcreditacion;
    }

    public void setFechaAcreditacion(LocalDateTime fechaAcreditacion) {
        this.fechaAcreditacion = fechaAcreditacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "Evaluador{" +
                "usuarioRutUsuario='" + usuarioRutUsuario + '\'' +
                ", nombreEvaluador='" + nombreEvaluador + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", profesion='" + profesion + '\'' +
                ", fechaAcreditacion='" + fechaAcreditacion + '\'' +
                ", usuarioRut=" + (usuario != null ? usuario.getRutUsuario() : "null") +
                '}';
    }

    // Método equals() para comparar con Evaluador
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evaluador evaluador = (Evaluador) o;
        return Objects.equals(usuarioRutUsuario, evaluador.usuarioRutUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioRutUsuario);
    }
}
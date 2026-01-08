package cl.iplacex.sistema_cev.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Cliente")
public class Cliente {

    @Id
    @Column(name = "usuario_rut_usuario", nullable = false, length = 12)
    private String usuarioRutUsuario;

    @Column(name = "razon_social", nullable = false, length = 45)
    private String razonSocial;

    @Column(name = "representante_legal", length = 100)
    private String representanteLegal;

    @Column(name = "giro_comercial", length = 60)
    private String giroComercial;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro;

    @OneToOne
    @JoinColumn(name = "usuario_rut_usuario", referencedColumnName = "rut_usuario",
                insertable = false, updatable = false)
    private Usuario usuario;

    // Constructor vacío (necesario para JPA)
    public Cliente() {
    }

    // Constructor con parámetros
    public Cliente(String usuarioRutUsuario, String razonSocial,
                   String representanteLegal, String giroComercial, LocalDateTime fechaRegistro, Usuario usuario) {
        this.usuarioRutUsuario = usuarioRutUsuario;
        this.razonSocial = razonSocial;
        this.representanteLegal = representanteLegal;
        this.giroComercial = giroComercial;
        this.fechaRegistro = fechaRegistro;
        this.usuario = usuario;
    }

    // Getters y Setters
    public String getUsuarioRutUsuario() {
        return usuarioRutUsuario;
    }

    public void setUsuarioRutUsuario(String usuarioRutUsuario) {
        this.usuarioRutUsuario = usuarioRutUsuario;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getRepresentanteLegal() {
        return representanteLegal;
    }

    public void setRepresentanteLegal(String representanteLegal) {
        this.representanteLegal = representanteLegal;
    }

    public String getGiroComercial() {
        return giroComercial;
    }

    public void setGiroComercial(String giroComercial) {
        this.giroComercial = giroComercial;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // Métodos 'override'
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(usuarioRutUsuario, cliente.usuarioRutUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usuarioRutUsuario);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "usuarioRutUsuario='" + usuarioRutUsuario + '\'' +
                ", razonSocial='" + razonSocial + '\'' +
                ", representanteLegal='" + representanteLegal + '\'' +
                ", giroComercial='" + giroComercial + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", usuario=" + (usuario != null ? usuario.getRutUsuario() : "null") +
                '}';
    }
}
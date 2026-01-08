package cl.iplacex.sistema_cev.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "direccion")
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_direccion")
    private Long idDireccion;

    @Column(name = "calle", length = 100, nullable = false)
    @NotNull
    private String calle;

    @Column(name = "numero")
    private Integer numero;

    @Column(name = "complemento", length = 45)
    private String complemento;

    @Column(name = "latitud", precision = 10, scale = 8)
    private BigDecimal latitud;

    @Column(name = "longitud", precision = 11, scale = 8)
    private BigDecimal longitud;

    // Relación ManyToOne con la entidad Comuna
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comuna_id_comuna", referencedColumnName = "id_comuna")
    @NotNull
    private Comuna comuna;

    // Relación ManyToOne con la entidad ZonaTermica
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zona_termica_id_zona_termica", referencedColumnName = "id_zona_termica")
    @NotNull
    private ZonaTermica zonaTermica;

    // Constructor vacio
    public Direccion() {        
    }


    //Constructores
    public Direccion(Long idDireccion, String calle, Integer numero, String complemento,
                     BigDecimal latitud, BigDecimal longitud, Comuna comuna, ZonaTermica zonaTermica) {
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.numero = numero;
        this.complemento = complemento;
        this.latitud = latitud;
        this.longitud = longitud;
        this.comuna = comuna;
        this.zonaTermica = zonaTermica;
    }

    public Long getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(Long idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public BigDecimal getLatitud() {
        return latitud;
    }

    public void setLatitud(BigDecimal latitud) {
        this.latitud = latitud;
    }

    public BigDecimal getLongitud() {
        return longitud;
    }

    public void setLongitud(BigDecimal longitud) {
        this.longitud = longitud;
    }

    public Comuna getComuna() {
        return comuna;
    }

    public void setComuna(Comuna comuna) {
        this.comuna = comuna;
    }

    public ZonaTermica getZonaTermica() {
        return zonaTermica;
    }

    public void setZonaTermica(ZonaTermica zonaTermica) {
        this.zonaTermica = zonaTermica;
    }

    // --- Métodos 'override' ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Direccion direccion = (Direccion) o;
        return Objects.equals(idDireccion, direccion.idDireccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDireccion);
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "idDireccion=" + idDireccion +
                ", calle='" + calle + '\'' +
                ", numero=" + numero +
                ", complemento='" + complemento + '\'' +
                ", latitud=" + latitud +
                ", longitud=" + longitud +
                // Se utilizan los IDs para evitar LazyInitializationException
                ", IdComuna=" + (comuna != null ? comuna.getIdComuna() : "null") +
                ", zonaTermicaId=" + (zonaTermica != null ? zonaTermica.getIdZonaTermica() : "null") +
                '}';
    }

    
}

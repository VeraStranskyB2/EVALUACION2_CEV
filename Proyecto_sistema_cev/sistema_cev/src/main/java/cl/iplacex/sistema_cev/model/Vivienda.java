package cl.iplacex.sistema_cev.model;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Vivienda", uniqueConstraints = { @UniqueConstraint(columnNames = { "rol_vivienda" }) })
public class Vivienda {

    // Atributos
    /* tipo=casa o departamento, estado=nueva o usada */
    @Id
    @Column(name = "rol_vivienda", nullable = false, length = 12)
    @NotNull
    private String rolVivienda;

    @Column(name = "direccion", nullable = false, length = 45)
    private String direccion;

    @Column(name = "tipo", nullable = false, length = 20)
    @NotNull
    private String tipo;

    @Column(name = "estado", nullable = false, length = 20)
    @NotNull
    private String estado;

    @Column(name = "lote", length = 12)
    @NotNull
    private String lote;

    @Column(name = "manzana", length = 12)
    @NotNull
    private String manzana;

    @Column(name = "edificio", length = 20)
    private String edificio;

    @Column(name = "piso")
    private Long piso;

    @Column(name = "unidad", length = 12)
    private String unidad;

    @Column(name = "orientacion", length = 20)
    private String orientacion;

    @Column(name = "materialidad", length = 20)
    @NotNull
    private String materialidad;

    // Relacion con Proyecto

    @ManyToOne
    @JoinColumn(name = "proyecto_id_Proyecto", nullable = false)
    private Proyecto proyecto;

    // Fecha de creación
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    // Fecha de actualización
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Constructor vacio
    public Vivienda() {
        super();
       
    }

    // Constructores con Parámetros
    public Vivienda(String rolVivienda, String direccion, String tipo, String estado, String lote, String manzana,
            String edificio, Long piso, String unidad, String orientacion, String materialidad, Proyecto proyecto) {

                if (rolVivienda == null || direccion == null || tipo == null || estado == null || 
            lote == null || manzana == null || materialidad == null || proyecto == null) {
            throw new IllegalArgumentException("Ningún atributo puede ser nulo.");
            }
        
        this.rolVivienda = rolVivienda;
        this.direccion = direccion;
        this.tipo = tipo;
        this.estado = estado;
        this.lote = lote;
        this.manzana = manzana;
        this.edificio = edificio;
        this.piso = piso;
        this.unidad = unidad;
        this.orientacion = orientacion;
        this.materialidad = materialidad;
        this.proyecto = proyecto;

    }

     
    /* Getter and Setter, accesadores y modificadores de una clase */
    public String getRolVivienda() {
        return rolVivienda;
    }

    public void setRolVivienda(String rolVivienda) {
        this.rolVivienda = rolVivienda;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getManzana() {
        return manzana;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public Long getPiso() {
        return piso;
    }

    public void setPiso(Long piso) {
        this.piso = piso;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(String orientacion) {
        this.orientacion = orientacion;
    }

    public String getMaterialidad() {
        return materialidad;
    }

    public void setMaterialidad(String materialidad) {
        this.materialidad = materialidad;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Vivienda{" +
                "rolVivienda='" + rolVivienda + '\'' +
                ", direccion='" + direccion + '\'' +
                ", tipo='" + tipo + '\'' +
                ", estado='" + estado + '\'' +
                ", lote='" + lote + '\'' +
                ", manzana='" + manzana + '\'' +
                ", edificio='" + edificio + '\'' +
                ", piso=" + piso +
                ", unidad=" + unidad +
                ", orientacion='" + orientacion + '\'' +
                ", materialidad='" + materialidad + '\'' +
                ", proyecto=" + proyecto +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vivienda)) return false;
        Vivienda vivienda = (Vivienda) o;
        return Objects.equals(rolVivienda, vivienda.rolVivienda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rolVivienda);
    }
}

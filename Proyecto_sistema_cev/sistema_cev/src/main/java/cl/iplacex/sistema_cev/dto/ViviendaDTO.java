package cl.iplacex.sistema_cev.dto;



import java.util.Objects;

import jakarta.validation.constraints.NotNull;

public class ViviendaDTO {

    // Atributos
    /* tipo=casa o departamento, estado=nueva o usada */

    @NotNull
    private String rolVivienda;


    private String direccion;

    
    @NotNull
    private String tipo;

    
    @NotNull
    private String estado;

    
    @NotNull
    private String lote;

    
    @NotNull
    private String manzana;

    
    private String edificio;

    
    private Long piso;

    
    private String unidad;

   
    private String orientacion;

    
    @NotNull
    private String materialidad;

    
    public ViviendaDTO() {
       
    }

    // Constructores con Parámetros
    public ViviendaDTO(String rolVivienda, String direccion, String tipo, String estado, String lote, String manzana,
            String edificio, Long piso, String unidad, String orientacion, String materialidad) {

                if (rolVivienda == null || direccion == null || tipo == null || estado == null || 
            lote == null || manzana == null || materialidad == null) {
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

   
    @Override
    public String toString() {
        return "ViviendaDTO{" +
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
                
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ViviendaDTO)) return false;
        ViviendaDTO viviendaDTO = (ViviendaDTO) o;
        return Objects.equals(rolVivienda, viviendaDTO.rolVivienda);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rolVivienda);
    }
}




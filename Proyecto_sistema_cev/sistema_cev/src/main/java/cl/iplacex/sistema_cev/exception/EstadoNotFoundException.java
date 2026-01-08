package cl.iplacex.sistema_cev.exception;

public class EstadoNotFoundException extends RuntimeException {
    
    public EstadoNotFoundException(Long idEstado) {
        super("No se encontró el estado con ID: " + idEstado);
    }

    // Constructor que acepta un mensaje personalizado
    public EstadoNotFoundException(String message) {
        super(message); 
    }



}

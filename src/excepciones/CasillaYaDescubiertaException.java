package excepciones;

/**
 * Excepción personalizada que se lanza cuando se intenta descubrir una casilla ya descubierta
 * Implementa el manejo de excepciones específicas del juego Buscaminas
 */
public class CasillaYaDescubiertaException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor con mensaje personalizado
     * @param mensaje Mensaje descriptivo del error
     */
    public CasillaYaDescubiertaException(String mensaje) {
        super(mensaje);
    }
    
    /**
     * Constructor por defecto
     */
    public CasillaYaDescubiertaException() {
        super("La casilla ya ha sido descubierta");
    }
}
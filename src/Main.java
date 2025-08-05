import controlador.ControladorJuego;

/**
 * Clase principal para ejecutar el juego Buscaminas
 * Punto de entrada de la aplicación
 * 
 * @author Paul Crespo
 */
public class Main {
    
    /**
     * Método principal que inicia la aplicación
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        System.out.println("=== BUSCAMINAS - EXAMEN PRÁCTICO POO - Paul Crespo ===");
        System.out.println();
        
        try {
            // Crear e iniciar el controlador del juego
            ControladorJuego controlador = new ControladorJuego();
            controlador.iniciarJuego();
            
        } catch (Exception e) {
            System.err.println("Error fatal en la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
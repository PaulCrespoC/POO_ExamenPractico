package vista;

import modelo.Jugador;
import modelo.Tablero;

/**
 * Vista para mostrar el juego en consola
 * Implementa la capa de presentación del patrón MVC
 */
public class VistaConsola {
    
    /**
     * Muestra el tablero en consola con formato similar a la imagen proporcionada
     * @param tablero El tablero a mostrar
     */
    public void mostrarTablero(Tablero tablero) {
        System.out.println();
        
        // Mostrar encabezado con números de columnas
        System.out.print("   ");
        for (int i = 1; i <= tablero.getTamaño(); i++) {
            System.out.printf("%3d", i);
        }
        System.out.println();
        
        // Mostrar línea superior del tablero
        System.out.print("  ");
        for (int i = 0; i <= tablero.getTamaño(); i++) {
            System.out.print("---");
        }
        System.out.println();
        
        // Mostrar filas del tablero
        for (int i = 0; i < tablero.getTamaño(); i++) {
            char letraFila = (char)('A' + i);
            System.out.printf("%c |", letraFila);
            
            for (int j = 0; j < tablero.getTamaño(); j++) {
                System.out.printf(" %s |", tablero.getCasilla(i, j).getRepresentacion());
            }
            System.out.println();
            
            // Línea separadora
            System.out.print("  ");
            for (int k = 0; k <= tablero.getTamaño(); k++) {
                System.out.print("---");
            }
            System.out.println();
        }
        
        mostrarLeyenda();
    }
    
    /**
     * Muestra la leyenda de caracteres del juego
     */
    private void mostrarLeyenda() {
        System.out.println("\nCaracteres:");
        System.out.println("• X = Ubicación de una mina (marcada)");
        System.out.println("• V = Espacio vacío seleccionado");
        System.out.println("• * = Mina descubierta");
        System.out.println("• Número = Cantidad de minas adyacentes");
        System.out.println("• Espacio = Casilla no descubierta");
    }
    
    /**
     * Muestra el menú principal del juego
     */
    public void mostrarMenu() {
        System.out.println("\n=== BUSCAMINAS ===");
        System.out.println("1. Nuevo juego");
        System.out.println("2. Cargar juego");
        System.out.println("3. Ver estadísticas");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    /**
     * Muestra las opciones de juego
     */
    public void mostrarOpcionesJuego() {
        System.out.println("\nOpciones:");
        System.out.println("• Descubrir casilla: Ingrese coordenada (ej: A5)");
        System.out.println("• Marcar casilla: Ingrese 'M' seguido de coordenada (ej: MA5)");
        System.out.println("• Guardar juego: Ingrese 'GUARDAR'");
        System.out.println("• Salir al menú: Ingrese 'MENU'");
        System.out.print("Su jugada: ");
    }
    
    /**
     * Muestra mensaje de victoria
     */
    public void mostrarVictoria() {
        System.out.println("\n¡FELICIDADES! ¡HAS GANADO!");
        System.out.println("Has descubierto todas las casillas sin minas.");
    }
    
    /**
     * Muestra mensaje de derrota
     */
    public void mostrarDerrota() {
        System.out.println("\n¡Juego terminado!");
        System.out.println("Has descubierto una mina. El juego ha terminado.");
    }
    
    /**
     * Muestra las estadísticas del jugador
     * @param jugador El jugador cuyas estadísticas mostrar
     */
    public void mostrarEstadisticas(Jugador jugador) {
        System.out.println("\n=== ESTADÍSTICAS ===");
        System.out.println(jugador.getEstadisticas());
    }
    
    /**
     * Muestra un mensaje de error
     * @param mensaje El mensaje de error a mostrar
     */
    public void mostrarError(String mensaje) {
        System.out.println("Error: " + mensaje);
    }
    
    /**
     * Muestra un mensaje informativo
     * @param mensaje El mensaje a mostrar
     */
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
    
    /**
     * Solicita el nombre del jugador
     */
    public void solicitarNombre() {
        System.out.print("Ingrese su nombre: ");
    }
    
    /**
     * Muestra mensaje de bienvenida
     * @param nombreJugador El nombre del jugador
     */
    public void mostrarBienvenida(String nombreJugador) {
        System.out.println("\n¡Bienvenido al Buscaminas, " + nombreJugador + "!");
        System.out.println("Objetivo: Descubre todas las casillas sin minas.");
        System.out.println("Hay 10 minas ocultas en el tablero de 10x10.");
    }
    
    /**
     * Limpia la pantalla (simulado)
     */
    public void limpiarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
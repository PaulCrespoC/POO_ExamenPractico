package controlador;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;

import excepciones.CasillaYaDescubiertaException;
import modelo.Jugador;
import modelo.Tablero;
import persistencia.GestorArchivos;
import vista.VistaConsola;

/**
 * Controlador principal del juego Buscaminas
 * Implementa la lógica de control del patrón MVC
 */
public class ControladorJuego {
    private Tablero tablero;
    private Jugador jugador;
    private VistaConsola vista;
    private Scanner scanner;
    private GestorArchivos gestorArchivos;
    private boolean juegoActivo;
    
    /**
     * Constructor del controlador
     */
    public ControladorJuego() {
        this.vista = new VistaConsola();
        this.scanner = new Scanner(System.in);
        this.gestorArchivos = new GestorArchivos();
        this.juegoActivo = false;
    }
    
    /**
     * Inicia el juego principal
     */
    public void iniciarJuego() {
        vista.mostrarBienvenida("Jugador");
        configurarJugador();
        
        boolean continuar = true;
        while (continuar) {
            vista.mostrarMenu();
            
            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
                
                switch (opcion) {
                    case 1:
                        nuevoJuego();
                        break;
                    case 2:
                        cargarJuego();
                        break;
                    case 3:
                        vista.mostrarEstadisticas(jugador);
                        break;
                    case 4:
                        continuar = false;
                        vista.mostrarMensaje("¡Gracias por jugar!");
                        break;
                    default:
                        vista.mostrarError("Opción no válida. Seleccione del 1 al 4.");
                }
            } catch (InputMismatchException e) {
                vista.mostrarError("Por favor, ingrese un número válido.");
                scanner.nextLine(); // Limpiar buffer
            }
        }
        
        scanner.close();
    }
    
    /**
     * Configura el jugador al inicio del juego
     */
    private void configurarJugador() {
        vista.solicitarNombre();
        String nombre = scanner.nextLine().trim();
        
        if (nombre.isEmpty()) {
            nombre = "Jugador";
        }
        
        this.jugador = new Jugador(nombre);
        vista.mostrarBienvenida(nombre);
    }
    
    /**
     * Inicia un nuevo juego
     */
    private void nuevoJuego() {
        this.tablero = new Tablero();
        this.juegoActivo = true;
        
        vista.mostrarMensaje("¡Nuevo juego iniciado!");
        bucleJuego();
    }
    
    /**
     * Carga un juego guardado
     */
    private void cargarJuego() {
        try {
            Object[] datos = gestorArchivos.cargarJuego();
            if (datos != null) {
                this.tablero = (Tablero) datos[0];
                this.jugador = (Jugador) datos[1];
                this.juegoActivo = true;
                
                vista.mostrarMensaje("¡Juego cargado exitosamente!");
                bucleJuego();
            } else {
                vista.mostrarError("No se encontró ningún juego guardado.");
            }
        } catch (Exception e) {
            vista.mostrarError("Error al cargar el juego: " + e.getMessage());
        }
    }
    
    /**
     * Bucle principal del juego
     */
    private void bucleJuego() {
        while (juegoActivo && !tablero.estaTerminado()) {
            vista.mostrarTablero(tablero);
            vista.mostrarOpcionesJuego();
            
            String entrada = scanner.nextLine().trim().toUpperCase();
            procesarEntrada(entrada);
        }
        
        if (tablero.estaTerminado()) {
            vista.mostrarTablero(tablero);
            finalizarJuego();
        }
    }
    
    /**
     * Procesa la entrada del usuario
     * @param entrada La cadena ingresada por el usuario
     */
    private void procesarEntrada(String entrada) {
        try {
            if (entrada.equals("MENU")) {
                juegoActivo = false;
                return;
            }
            
            if (entrada.equals("GUARDAR")) {
                guardarJuego();
                return;
            }
            
            if (entrada.startsWith("M") && entrada.length() >= 3) {
                // Marcar casilla
                String coordenada = entrada.substring(1);
                int[] posicion = parsearCoordenada(coordenada);
                tablero.marcarCasilla(posicion[0], posicion[1]);
                vista.mostrarMensaje("Casilla marcada/desmarcada en " + coordenada);
            } else {
                // Descubrir casilla
                int[] posicion = parsearCoordenada(entrada);
                tablero.descubrirCasilla(posicion[0], posicion[1]);
                
                if (tablero.getCasilla(posicion[0], posicion[1]).tieneMina()) {
                    vista.mostrarMensaje("¡Has descubierto una mina!");
                } else {
                    vista.mostrarMensaje("Casilla descubierta en " + entrada);
                }
            }
        } catch (CasillaYaDescubiertaException e) {
            vista.mostrarError(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            vista.mostrarError("Coordenada fuera del tablero. Use formato como A5 (A-J, 1-10).");
        } catch (IllegalArgumentException e) {
            vista.mostrarError("Formato de coordenada inválido. Use formato como A5 o MA5 para marcar.");
        } catch (Exception e) {
            vista.mostrarError("Error inesperado: " + e.getMessage());
        }
    }
    
    /**
     * Parsea una coordenada en formato A5 a índices de matriz
     * @param coordenada La coordenada en formato letra-número
     * @return Array con [fila, columna]
     */
    private int[] parsearCoordenada(String coordenada) {
        if (!Pattern.matches("^[A-J][1-9]|[A-J]10$", coordenada)) {
            throw new IllegalArgumentException("Formato de coordenada inválido");
        }
        
        char letra = coordenada.charAt(0);
        String numeroStr = coordenada.substring(1);
        
        int fila = letra - 'A';
        int columna = Integer.parseInt(numeroStr) - 1;
        
        return new int[]{fila, columna};
    }
    
    /**
     * Guarda el estado actual del juego
     */
    private void guardarJuego() {
        try {
            gestorArchivos.guardarJuego(tablero, jugador);
            vista.mostrarMensaje("Juego guardado exitosamente.");
        } catch (Exception e) {
            vista.mostrarError("Error al guardar el juego: " + e.getMessage());
        }
    }
    
    /**
     * Finaliza el juego y muestra resultados
     */
    private void finalizarJuego() {
        if (tablero.esVictoria()) {
            vista.mostrarVictoria();
            jugador.registrarVictoria();
        } else {
            vista.mostrarDerrota();
            jugador.registrarDerrota();
        }
        
        vista.mostrarEstadisticas(jugador);
        juegoActivo = false;
    }
}
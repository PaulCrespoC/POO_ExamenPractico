package modelo;

import java.io.Serializable;
import java.util.Random;

import excepciones.CasillaYaDescubiertaException;

/**
 * Representa el tablero del juego Buscaminas
 * Gestiona la matriz de casillas y la lógica del juego
 */
public class Tablero implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final int TAMAÑO = 10;
    private static final int NUMERO_MINAS = 10;
    
    private Casilla[][] matriz;
    private int casillasDescubiertas;
    private boolean juegoTerminado;
    private boolean victoria;
    
    /**
     * Constructor del tablero
     */
    public Tablero() {
        this.matriz = new Casilla[TAMAÑO][TAMAÑO];
        this.casillasDescubiertas = 0;
        this.juegoTerminado = false;
        this.victoria = false;
        inicializarTablero();
        colocarMinas();
        calcularMinasAdyacentes();
    }
    
    /**
     * Inicializa todas las casillas del tablero
     */
    private void inicializarTablero() {
        for (int i = 0; i < TAMAÑO; i++) {
            for (int j = 0; j < TAMAÑO; j++) {
                matriz[i][j] = new Casilla();
            }
        }
    }
    
    /**
     * Coloca las minas aleatoriamente en el tablero
     */
    private void colocarMinas() {
        Random random = new Random();
        int minasColocadas = 0;
        
        while (minasColocadas < NUMERO_MINAS) {
            int fila = random.nextInt(TAMAÑO);
            int columna = random.nextInt(TAMAÑO);
            
            if (!matriz[fila][columna].tieneMina()) {
                matriz[fila][columna].colocarMina();
                minasColocadas++;
            }
        }
    }
    
    /**
     * Calcula el número de minas adyacentes para cada casilla
     */
    private void calcularMinasAdyacentes() {
        for (int i = 0; i < TAMAÑO; i++) {
            for (int j = 0; j < TAMAÑO; j++) {
                if (!matriz[i][j].tieneMina()) {
                    int minas = contarMinasAdyacentes(i, j);
                    matriz[i][j].setMinasAdyacentes(minas);
                }
            }
        }
    }
    
    /**
     * Cuenta las minas adyacentes a una posición específica
     */
    private int contarMinasAdyacentes(int fila, int columna) {
        int contador = 0;
        
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nuevaFila = fila + i;
                int nuevaColumna = columna + j;
                
                if (esValida(nuevaFila, nuevaColumna) && 
                    matriz[nuevaFila][nuevaColumna].tieneMina()) {
                    contador++;
                }
            }
        }
        
        return contador;
    }
    
    /**
     * Verifica si una posición es válida en el tablero
     */
    private boolean esValida(int fila, int columna) {
        return fila >= 0 && fila < TAMAÑO && columna >= 0 && columna < TAMAÑO;
    }
    
    /**
     * Descubre una casilla en la posición especificada
     */
    public void descubrirCasilla(int fila, int columna) throws CasillaYaDescubiertaException {
        if (!esValida(fila, columna)) {
            throw new ArrayIndexOutOfBoundsException("Posición fuera del tablero");
        }
        
        Casilla casilla = matriz[fila][columna];
        
        if (casilla.estaDescubierta()) {
            throw new CasillaYaDescubiertaException("La casilla ya está descubierta");
        }
        
        if (casilla.estaMarcada()) {
            return; // No se puede descubrir una casilla marcada
        }
        
        casilla.descubrir();
        casillasDescubiertas++;
        
        if (casilla.tieneMina()) {
            juegoTerminado = true;
            victoria = false;
            revelarTodasLasMinas();
        } else if (casilla.getMinasAdyacentes() == 0) {
            // Revelar automáticamente casillas adyacentes vacías
            revelarCasillasVacias(fila, columna);
        }
        
        // Verificar condición de victoria
        if (casillasDescubiertas == (TAMAÑO * TAMAÑO - NUMERO_MINAS)) {
            juegoTerminado = true;
            victoria = true;
        }
    }
    
    /**
     * Revela automáticamente las casillas vacías adyacentes
     */
    private void revelarCasillasVacias(int fila, int columna) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nuevaFila = fila + i;
                int nuevaColumna = columna + j;
                
                if (esValida(nuevaFila, nuevaColumna)) {
                    Casilla casilla = matriz[nuevaFila][nuevaColumna];
                    
                    if (!casilla.estaDescubierta() && !casilla.tieneMina() && !casilla.estaMarcada()) {
                        casilla.descubrir();
                        casillasDescubiertas++;
                        
                        if (casilla.getMinasAdyacentes() == 0) {
                            revelarCasillasVacias(nuevaFila, nuevaColumna);
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Revela todas las minas al finalizar el juego
     */
    private void revelarTodasLasMinas() {
        for (int i = 0; i < TAMAÑO; i++) {
            for (int j = 0; j < TAMAÑO; j++) {
                if (matriz[i][j].tieneMina()) {
                    matriz[i][j].descubrir();
                }
            }
        }
    }
    
    /**
     * Marca o desmarca una casilla
     */
    public void marcarCasilla(int fila, int columna) {
        if (esValida(fila, columna) && !matriz[fila][columna].estaDescubierta()) {
            matriz[fila][columna].marcar();
        }
    }
    
    // Getters
    public Casilla getCasilla(int fila, int columna) {
        return matriz[fila][columna];
    }
    
    public boolean estaTerminado() {
        return juegoTerminado;
    }
    
    public boolean esVictoria() {
        return victoria;
    }
    
    public int getTamaño() {
        return TAMAÑO;
    }
    
    public int getCasillasDescubiertas() {
        return casillasDescubiertas;
    }
}
package modelo;

/**
 * Representa una casilla individual del tablero de Buscaminas
 * Implementa encapsulamiento para gestionar el estado de cada casilla
 */
public class Casilla {
    private boolean tieneMina;
    private boolean descubierta;
    private boolean marcada;
    private int minasAdyacentes;
    
    /**
     * Constructor de la casilla
     */
    public Casilla() {
        this.tieneMina = false;
        this.descubierta = false;
        this.marcada = false;
        this.minasAdyacentes = 0;
    }
    
    // Métodos getter con encapsulamiento
    public boolean tieneMina() {
        return tieneMina;
    }
    
    public boolean estaDescubierta() {
        return descubierta;
    }
    
    public boolean estaMarcada() {
        return marcada;
    }
    
    public int getMinasAdyacentes() {
        return minasAdyacentes;
    }
    
    // Métodos setter con encapsulamiento
    public void colocarMina() {
        this.tieneMina = true;
    }
    
    public void descubrir() {
        this.descubierta = true;
    }
    
    public void marcar() {
        this.marcada = !this.marcada;
    }
    
    public void setMinasAdyacentes(int minas) {
        this.minasAdyacentes = minas;
    }
    
    /**
     * Obtiene la representación visual de la casilla
     * @return String que representa el estado visual de la casilla
     */
    public String getRepresentacion() {
        if (marcada && !descubierta) {
            return "X";
        }
        if (!descubierta) {
            return " ";
        }
        if (tieneMina) {
            return "*";
        }
        if (minasAdyacentes == 0) {
            return "V";
        }
        return String.valueOf(minasAdyacentes);
    }
}
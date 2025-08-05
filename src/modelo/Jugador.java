package modelo;

import java.io.Serializable;

/**
 * Representa al jugador del juego Buscaminas
 * Gestiona la información y estadísticas del jugador
 */
public class Jugador implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private int partidasJugadas;
    private int partidasGanadas;
    private int partidasPerdidas;
    
    /**
     * Constructor del jugador
     * @param nombre Nombre del jugador
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.partidasJugadas = 0;
        this.partidasGanadas = 0;
        this.partidasPerdidas = 0;
    }
    
    /**
     * Constructor por defecto
     */
    public Jugador() {
        this("Jugador");
    }
    
    /**
     * Registra una partida ganada
     */
    public void registrarVictoria() {
        partidasJugadas++;
        partidasGanadas++;
    }
    
    /**
     * Registra una partida perdida
     */
    public void registrarDerrota() {
        partidasJugadas++;
        partidasPerdidas++;
    }
    
    /**
     * Calcula el porcentaje de victorias
     * @return Porcentaje de victorias
     */
    public double getPorcentajeVictorias() {
        if (partidasJugadas == 0) {
            return 0.0;
        }
        return (double) partidasGanadas / partidasJugadas * 100;
    }
    
    // Getters y setters con encapsulamiento
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getPartidasJugadas() {
        return partidasJugadas;
    }
    
    public int getPartidasGanadas() {
        return partidasGanadas;
    }
    
    public int getPartidasPerdidas() {
        return partidasPerdidas;
    }
    
    /**
     * Obtiene las estadísticas del jugador como String
     * @return String con las estadísticas
     */
    public String getEstadisticas() {
        return String.format("Jugador: %s\nPartidas jugadas: %d\nVictorias: %d\nDerrotas: %d\nPorcentaje de victorias: %.2f%%",
                nombre, partidasJugadas, partidasGanadas, partidasPerdidas, getPorcentajeVictorias());
    }
}
package test;

import modelo.Jugador;

/**
 * Clase de pruebas unitarias para la clase Jugador
 * Implementa principios de TDD (Test-Driven Development)
 */
public class TestJugador {
    
    /**
     * Ejecuta todas las pruebas unitarias para Jugador
     */
    public static void ejecutarPruebas() {
        System.out.println("=== EJECUTANDO PRUEBAS UNITARIAS - JUGADOR ===");
        
        testCreacionJugador();
        testRegistrarVictoria();
        testRegistrarDerrota();
        testPorcentajeVictorias();
        testEstadisticas();
        
        System.out.println("=== TODAS LAS PRUEBAS JUGADOR COMPLETADAS ===\n");
    }
    
    /**
     * Prueba la creación correcta de un jugador
     */
    private static void testCreacionJugador() {
        System.out.print("Test: Creación de jugador... ");
        
        // Jugador con nombre específico
        Jugador jugador1 = new Jugador("TestPlayer");
        assert jugador1.getNombre().equals("TestPlayer") : "El nombre debe ser TestPlayer";
        assert jugador1.getPartidasJugadas() == 0 : "Debe tener 0 partidas jugadas inicialmente";
        assert jugador1.getPartidasGanadas() == 0 : "Debe tener 0 partidas ganadas inicialmente";
        assert jugador1.getPartidasPerdidas() == 0 : "Debe tener 0 partidas perdidas inicialmente";
        
        // Jugador con constructor por defecto
        Jugador jugador2 = new Jugador();
        assert jugador2.getNombre().equals("Jugador") : "El nombre por defecto debe ser 'Jugador'";
        
        System.out.println("✓ PASÓ");
    }
    
    /**
     * Prueba el registro de victorias
     */
    private static void testRegistrarVictoria() {
        System.out.print("Test: Registrar victoria... ");
        
        Jugador jugador = new Jugador("TestPlayer");
        
        // Registrar primera victoria
        jugador.registrarVictoria();
        assert jugador.getPartidasJugadas() == 1 : "Debe tener 1 partida jugada";
        assert jugador.getPartidasGanadas() == 1 : "Debe tener 1 partida ganada";
        assert jugador.getPartidasPerdidas() == 0 : "Debe tener 0 partidas perdidas";
        
        // Registrar segunda victoria
        jugador.registrarVictoria();
        assert jugador.getPartidasJugadas() == 2 : "Debe tener 2 partidas jugadas";
        assert jugador.getPartidasGanadas() == 2 : "Debe tener 2 partidas ganadas";
        
        System.out.println("✓ PASÓ");
    }
    
    /**
     * Prueba el registro de derrotas
     */
    private static void testRegistrarDerrota() {
        System.out.print("Test: Registrar derrota... ");
        
        Jugador jugador = new Jugador("TestPlayer");
        
        // Registrar primera derrota
        jugador.registrarDerrota();
        assert jugador.getPartidasJugadas() == 1 : "Debe tener 1 partida jugada";
        assert jugador.getPartidasGanadas() == 0 : "Debe tener 0 partidas ganadas";
        assert jugador.getPartidasPerdidas() == 1 : "Debe tener 1 partida perdida";
        
        // Registrar segunda derrota
        jugador.registrarDerrota();
        assert jugador.getPartidasJugadas() == 2 : "Debe tener 2 partidas jugadas";
        assert jugador.getPartidasPerdidas() == 2 : "Debe tener 2 partidas perdidas";
        
        System.out.println("✓ PASÓ");
    }
    
    /**
     * Prueba el cálculo del porcentaje de victorias
     */
    private static void testPorcentajeVictorias() {
        System.out.print("Test: Porcentaje de victorias... ");
        
        Jugador jugador = new Jugador("TestPlayer");
        
        // Sin partidas jugadas
        assert jugador.getPorcentajeVictorias() == 0.0 : "Sin partidas debe ser 0%";
        
        // Con una victoria
        jugador.registrarVictoria();
        assert jugador.getPorcentajeVictorias() == 100.0 : "1 victoria de 1 partida debe ser 100%";
        
        // Con una derrota
        jugador.registrarDerrota();
        assert jugador.getPorcentajeVictorias() == 50.0 : "1 victoria de 2 partidas debe ser 50%";
        
        // Con otra victoria
        jugador.registrarVictoria();
        double porcentajeEsperado = (2.0 / 3.0) * 100;
        assert Math.abs(jugador.getPorcentajeVictorias() - porcentajeEsperado) < 0.01 : 
               "2 victorias de 3 partidas debe ser 66.67%";
        
        System.out.println("✓ PASÓ");
    }
    
    /**
     * Prueba la generación de estadísticas
     */
    private static void testEstadisticas() {
        System.out.print("Test: Estadísticas... ");
        
        Jugador jugador = new Jugador("TestPlayer");
        jugador.registrarVictoria();
        jugador.registrarDerrota();
        
        String estadisticas = jugador.getEstadisticas();
        
        // Verificar que contiene información básica
        assert estadisticas.contains("TestPlayer") : "Debe contener el nombre del jugador";
        assert estadisticas.contains("2") : "Debe contener el número de partidas jugadas";
        assert estadisticas.contains("1") : "Debe contener el número de victorias y derrotas";
        assert estadisticas.contains("50") : "Debe contener el porcentaje correcto";
        
        System.out.println("✓ PASÓ");
    }
}
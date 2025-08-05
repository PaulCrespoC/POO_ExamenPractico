package test;

import excepciones.CasillaYaDescubiertaException;
import modelo.Casilla;
import modelo.Tablero;

/**
 * Clase de pruebas unitarias para la clase Tablero
 * Implementa principios de TDD (Test-Driven Development)
 */
public class TestTablero {
    
    /**
     * Ejecuta todas las pruebas unitarias
     */
    public static void ejecutarPruebas() {
        System.out.println("=== EJECUTANDO PRUEBAS UNITARIAS - TABLERO ===");
        
        testCreacionTablero();
        testColocacionMinas();
        testDescubrirCasilla();
        testMarcarCasilla();
        testExcepcionCasillaYaDescubierta();
        testCondicionVictoria();
        
        System.out.println("=== TODAS LAS PRUEBAS COMPLETADAS ===\n");
    }
    
    /**
     * Prueba la creación correcta del tablero
     */
    private static void testCreacionTablero() {
        System.out.print("Test: Creación del tablero... ");
        
        Tablero tablero = new Tablero();
        
        // Verificar que el tablero se crea con el tamaño correcto
        assert tablero.getTamaño() == 10 : "El tamaño del tablero debe ser 10";
        
        // Verificar que todas las casillas están inicializadas
        for (int i = 0; i < tablero.getTamaño(); i++) {
            for (int j = 0; j < tablero.getTamaño(); j++) {
                Casilla casilla = tablero.getCasilla(i, j);
                assert casilla != null : "Todas las casillas deben estar inicializadas";
                assert !casilla.estaDescubierta() : "Las casillas deben empezar sin descubrir";
                assert !casilla.estaMarcada() : "Las casillas deben empezar sin marcar";
            }
        }
        
        // Verificar estado inicial del juego
        assert !tablero.estaTerminado() : "El juego no debe estar terminado al inicio";
        assert !tablero.esVictoria() : "No debe haber victoria al inicio";
        assert tablero.getCasillasDescubiertas() == 0 : "No debe haber casillas descubiertas al inicio";
        
        System.out.println("✓ PASÓ");
    }
    
    /**
     * Prueba que se coloquen exactamente 10 minas
     */
    private static void testColocacionMinas() {
        System.out.print("Test: Colocación de minas... ");
        
        Tablero tablero = new Tablero();
        int minasEncontradas = 0;
        
        // Contar minas en el tablero
        for (int i = 0; i < tablero.getTamaño(); i++) {
            for (int j = 0; j < tablero.getTamaño(); j++) {
                if (tablero.getCasilla(i, j).tieneMina()) {
                    minasEncontradas++;
                }
            }
        }
        
        assert minasEncontradas == 10 : "Debe haber exactamente 10 minas, encontradas: " + minasEncontradas;
        
        System.out.println("✓ PASÓ (Minas encontradas: " + minasEncontradas + ")");
    }
    
    /**
     * Prueba el descubrimiento de casillas
     */
    private static void testDescubrirCasilla() {
        System.out.print("Test: Descubrir casilla... ");
        
        Tablero tablero = new Tablero();
        
        try {
            // Encontrar una casilla sin mina para probar
            boolean casillaEncontrada = false;
            for (int i = 0; i < tablero.getTamaño() && !casillaEncontrada; i++) {
                for (int j = 0; j < tablero.getTamaño() && !casillaEncontrada; j++) {
                    if (!tablero.getCasilla(i, j).tieneMina()) {
                        tablero.descubrirCasilla(i, j);
                        
                        assert tablero.getCasilla(i, j).estaDescubierta() : "La casilla debe estar descubierta";
                        assert tablero.getCasillasDescubiertas() > 0 : "Debe haber al menos una casilla descubierta";
                        
                        casillaEncontrada = true;
                    }
                }
            }
            
            assert casillaEncontrada : "Debe existir al menos una casilla sin mina";
            
        } catch (CasillaYaDescubiertaException e) {
            assert false : "No debería lanzarse excepción en casilla no descubierta";
        }
        
        System.out.println("✓ PASÓ");
    }
    
    /**
     * Prueba el marcado de casillas
     */
    private static void testMarcarCasilla() {
        System.out.print("Test: Marcar casilla... ");
        
        Tablero tablero = new Tablero();
        
        // Marcar una casilla
        tablero.marcarCasilla(0, 0);
        assert tablero.getCasilla(0, 0).estaMarcada() : "La casilla debe estar marcada";
        
        // Desmarcar la misma casilla
        tablero.marcarCasilla(0, 0);
        assert !tablero.getCasilla(0, 0).estaMarcada() : "La casilla debe estar desmarcada";
        
        System.out.println("✓ PASÓ");
    }
    
    /**
     * Prueba la excepción de casilla ya descubierta
     */
    private static void testExcepcionCasillaYaDescubierta() {
        System.out.print("Test: Excepción casilla ya descubierta... ");
        
        Tablero tablero = new Tablero();
        
        try {
            // Encontrar una casilla sin mina
            for (int i = 0; i < tablero.getTamaño(); i++) {
                for (int j = 0; j < tablero.getTamaño(); j++) {
                    if (!tablero.getCasilla(i, j).tieneMina()) {
                        // Descubrir la casilla
                        tablero.descubrirCasilla(i, j);
                        
                        try {
                            // Intentar descubrir la misma casilla nuevamente
                            tablero.descubrirCasilla(i, j);
                            assert false : "Debería lanzarse CasillaYaDescubiertaException";
                        } catch (CasillaYaDescubiertaException e) {
                            // Esta excepción es esperada
                            System.out.println("✓ PASÓ");
                            return;
                        }
                    }
                }
            }
        } catch (CasillaYaDescubiertaException e) {
            // No debería llegar aquí en la primera llamada
        }
        
        assert false : "No se pudo completar el test";
    }
    
    /**
     * Prueba la condición de victoria (simplificada)
     */
    private static void testCondicionVictoria() {
        System.out.print("Test: Verificación de condiciones de juego... ");
        
        Tablero tablero = new Tablero();
        
        // Verificar estado inicial
        assert !tablero.estaTerminado() : "El juego no debe estar terminado inicialmente";
        assert !tablero.esVictoria() : "No debe haber victoria inicialmente";
        
        // Probar descubrimiento de mina (si existe)
        boolean minaEncontrada = false;
        for (int i = 0; i < tablero.getTamaño() && !minaEncontrada; i++) {
            for (int j = 0; j < tablero.getTamaño() && !minaEncontrada; j++) {
                if (tablero.getCasilla(i, j).tieneMina()) {
                    try {
                        tablero.descubrirCasilla(i, j);
                        assert tablero.estaTerminado() : "El juego debe terminar al descubrir una mina";
                        assert !tablero.esVictoria() : "No debe haber victoria al descubrir una mina";
                        minaEncontrada = true;
                    } catch (CasillaYaDescubiertaException e) {
                        // Continuar buscando otra mina
                    }
                }
            }
        }
        
        System.out.println("✓ PASÓ");
    }
}
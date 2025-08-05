package test;

import modelo.Casilla;

/**
 * Clase de pruebas unitarias para la clase Casilla
 * Implementa principios de TDD (Test-Driven Development)
 */
public class TestCasilla {
    
    /**
     * Ejecuta todas las pruebas unitarias para Casilla
     */
    public static void ejecutarPruebas() {
        System.out.println("=== EJECUTANDO PRUEBAS UNITARIAS - CASILLA ===");
        
        testCreacionCasilla();
        testColocarMina();
        testDescubrirCasilla();
        testMarcarCasilla();
        testMinasAdyacentes();
        testRepresentacionVisual();
        
        System.out.println("=== TODAS LAS PRUEBAS CASILLA COMPLETADAS ===\n");
    }
    
    /**
     * Prueba la creación correcta de una casilla
     */
    private static void testCreacionCasilla() {
        System.out.print("Test: Creación de casilla... ");
        
        Casilla casilla = new Casilla();
        
        // Verificar estado inicial
        assert !casilla.tieneMina() : "La casilla no debe tener mina inicialmente";
        assert !casilla.estaDescubierta() : "La casilla no debe estar descubierta inicialmente";
        assert !casilla.estaMarcada() : "La casilla no debe estar marcada inicialmente";
        assert casilla.getMinasAdyacentes() == 0 : "Debe tener 0 minas adyacentes inicialmente";
        
        System.out.println("✓ PASÓ");
    }
    
    /**
     * Prueba la colocación de minas
     */
    private static void testColocarMina() {
        System.out.print("Test: Colocar mina... ");
        
        Casilla casilla = new Casilla();
        
        // Colocar mina
        casilla.colocarMina();
        assert casilla.tieneMina() : "La casilla debe tener mina después de colocarla";
        
        System.out.println("✓ PASÓ");
    }
    
    /**
     * Prueba el descubrimiento de casillas
     */
    private static void testDescubrirCasilla() {
        System.out.print("Test: Descubrir casilla... ");
        
        Casilla casilla = new Casilla();
        
        // Descubrir casilla
        casilla.descubrir();
        assert casilla.estaDescubierta() : "La casilla debe estar descubierta";
        
        System.out.println("✓ PASÓ");
    }
    
    /**
     * Prueba el marcado de casillas
     */
    private static void testMarcarCasilla() {
        System.out.print("Test: Marcar casilla... ");
        
        Casilla casilla = new Casilla();
        
        // Marcar casilla
        casilla.marcar();
        assert casilla.estaMarcada() : "La casilla debe estar marcada";
        
        // Desmarcar casilla
        casilla.marcar();
        assert !casilla.estaMarcada() : "La casilla debe estar desmarcada";
        
        System.out.println("✓ PASÓ");
    }
    
    /**
     * Prueba el conteo de minas adyacentes
     */
    private static void testMinasAdyacentes() {
        System.out.print("Test: Minas adyacentes... ");
        
        Casilla casilla = new Casilla();
        
        // Establecer número de minas adyacentes
        casilla.setMinasAdyacentes(3);
        assert casilla.getMinasAdyacentes() == 3 : "Debe tener 3 minas adyacentes";
        
        casilla.setMinasAdyacentes(0);
        assert casilla.getMinasAdyacentes() == 0 : "Debe tener 0 minas adyacentes";
        
        casilla.setMinasAdyacentes(8);
        assert casilla.getMinasAdyacentes() == 8 : "Debe tener 8 minas adyacentes";
        
        System.out.println("✓ PASÓ");
    }
    
    /**
     * Prueba la representación visual de las casillas
     */
    private static void testRepresentacionVisual() {
        System.out.print("Test: Representación visual... ");
        
        // Casilla no descubierta
        Casilla casilla = new Casilla();
        assert casilla.getRepresentacion().equals(" ") : "Casilla no descubierta debe mostrar espacio";
        
        // Casilla marcada
        casilla.marcar();
        assert casilla.getRepresentacion().equals("X") : "Casilla marcada debe mostrar X";
        
        // Casilla descubierta sin mina y sin minas adyacentes
        casilla = new Casilla();
        casilla.descubrir();
        assert casilla.getRepresentacion().equals("V") : "Casilla vacía debe mostrar V";
        
        // Casilla descubierta con minas adyacentes
        casilla = new Casilla();
        casilla.setMinasAdyacentes(3);
        casilla.descubrir();
        assert casilla.getRepresentacion().equals("3") : "Casilla con 3 minas adyacentes debe mostrar 3";
        
        // Casilla descubierta con mina
        casilla = new Casilla();
        casilla.colocarMina();
        casilla.descubrir();
        assert casilla.getRepresentacion().equals("*") : "Casilla con mina debe mostrar *";
        
        System.out.println("✓ PASÓ");
    }
}
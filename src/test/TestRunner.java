package test;

/**
 * Ejecutor de todas las pruebas unitarias del proyecto
 * Implementa el patrón de ejecución de pruebas para TDD
 */
public class TestRunner {
    
    /**
     * Método principal para ejecutar todas las pruebas
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        System.out.println("=== INICIANDO SUITE DE PRUEBAS UNITARIAS ===");
        System.out.println("Proyecto: Buscaminas - Examen Práctico POO");
        System.out.println("Aplicando principios de TDD (Test-Driven Development)");
        System.out.println();
        
        boolean todasPasaron = true;
        
        try {
            // Ejecutar pruebas de Casilla
            TestCasilla.ejecutarPruebas();
            
            // Ejecutar pruebas de Tablero
            TestTablero.ejecutarPruebas();
            
            // Ejecutar pruebas de Jugador
            TestJugador.ejecutarPruebas();
            
            System.out.println("=== RESUMEN DE PRUEBAS ===");
            System.out.println("✓ Todas las pruebas unitarias PASARON exitosamente");
            System.out.println("✓ El código cumple con los requisitos especificados");
            System.out.println("✓ Principios de TDD aplicados correctamente");
            
        } catch (AssertionError e) {
            System.err.println("❌ FALLO EN PRUEBA: " + e.getMessage());
            todasPasaron = false;
        } catch (Exception e) {
            System.err.println("❌ ERROR INESPERADO: " + e.getMessage());
            e.printStackTrace();
            todasPasaron = false;
        }
        
        if (!todasPasaron) {
            System.out.println("\n=== PRUEBAS FALLIDAS ===");
            System.out.println("Se encontraron errores en las pruebas.");
            System.out.println("Revise el código y ejecute las pruebas nuevamente.");
            System.exit(1);
        } else {
            System.out.println("\n=== PRUEBAS COMPLETADAS EXITOSAMENTE ===");
            System.out.println("El proyecto está listo para su entrega.");
        }
    }
    
    /**
     * Ejecuta todas las pruebas sin terminar el programa
     * Útil para integración con otros sistemas
     * @return true si todas las pruebas pasaron, false en caso contrario
     */
    public static boolean ejecutarTodasLasPruebas() {
        try {
            TestCasilla.ejecutarPruebas();
            TestTablero.ejecutarPruebas();
            TestJugador.ejecutarPruebas();
            return true;
        } catch (AssertionError | Exception e) {
            System.err.println("Error en pruebas: " + e.getMessage());
            return false;
        }
    }
}
package persistencia;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import modelo.Jugador;
import modelo.Tablero;

/**
 * Gestor de archivos para la persistencia del juego
 * Implementa la serialización y deserialización de objetos
 */
public class GestorArchivos {
    private static final String DIRECTORIO_GUARDADO = "guardados";
    private static final String ARCHIVO_JUEGO = "juego.dat";
    private static final String RUTA_COMPLETA = DIRECTORIO_GUARDADO + File.separator + ARCHIVO_JUEGO;
    
    /**
     * Constructor que asegura la existencia del directorio de guardado
     */
    public GestorArchivos() {
        crearDirectorioSiNoExiste();
    }
    
    /**
     * Crea el directorio de guardado si no existe
     */
    private void crearDirectorioSiNoExiste() {
        try {
            Path directorio = Paths.get(DIRECTORIO_GUARDADO);
            if (!Files.exists(directorio)) {
                Files.createDirectories(directorio);
            }
        } catch (IOException e) {
            System.err.println("Error al crear directorio de guardado: " + e.getMessage());
        }
    }
    
    /**
     * Guarda el estado del juego en un archivo binario
     * @param tablero El tablero a guardar
     * @param jugador El jugador a guardar
     * @throws IOException Si ocurre un error de E/O
     */
    public void guardarJuego(Tablero tablero, Jugador jugador) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(RUTA_COMPLETA))) {
            
            // Guardar timestamp para verificar la validez del archivo
            oos.writeLong(System.currentTimeMillis());
            
            // Guardar objetos del juego
            oos.writeObject(tablero);
            oos.writeObject(jugador);
            
            // Limpiar archivos temporales antiguos según memoria
            limpiarArchivosTemporales();
            
        } catch (IOException e) {
            throw new IOException("Error al guardar el juego: " + e.getMessage());
        }
    }
    
    /**
     * Carga el estado del juego desde un archivo binario
     * @return Array con [Tablero, Jugador] o null si no existe el archivo
     * @throws IOException Si ocurre un error de E/O
     * @throws ClassNotFoundException Si no se puede deserializar la clase
     */
    public Object[] cargarJuego() throws IOException, ClassNotFoundException {
        File archivo = new File(RUTA_COMPLETA);
        
        if (!archivo.exists()) {
            return null;
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(RUTA_COMPLETA))) {
            
            // Leer timestamp
            long timestamp = ois.readLong();
            
            // Verificar que el archivo no sea demasiado antiguo (opcional)
            long horaActual = System.currentTimeMillis();
            long diferencia = horaActual - timestamp;
            
            // Si el archivo tiene más de 7 días, considerarlo obsoleto
            if (diferencia > 7 * 24 * 60 * 60 * 1000) {
                System.out.println("Advertencia: El archivo guardado es muy antiguo.");
            }
            
            // Cargar objetos del juego
            Tablero tablero = (Tablero) ois.readObject();
            Jugador jugador = (Jugador) ois.readObject();
            
            return new Object[]{tablero, jugador};
            
        } catch (IOException | ClassNotFoundException e) {
            throw new IOException("Error al cargar el juego: " + e.getMessage());
        }
    }
    
    /**
     * Verifica si existe un archivo de juego guardado
     * @return true si existe un archivo guardado, false en caso contrario
     */
    public boolean existeJuegoGuardado() {
        return new File(RUTA_COMPLETA).exists();
    }
    
    /**
     * Elimina el archivo de juego guardado
     * @return true si se eliminó exitosamente, false en caso contrario
     */
    public boolean eliminarJuegoGuardado() {
        File archivo = new File(RUTA_COMPLETA);
        if (archivo.exists()) {
            return archivo.delete();
        }
        return true;
    }
    
    /**
     * Limpia archivos temporales antiguos para evitar saturación del directorio
     * Implementa la funcionalidad requerida en la memoria
     */
    private void limpiarArchivosTemporales() {
        try {
            Path directorio = Paths.get(DIRECTORIO_GUARDADO);
            if (Files.exists(directorio)) {
                long horaActual = System.currentTimeMillis();
                long unaHoraEnMillis = 60 * 60 * 1000;
                
                Files.list(directorio)
                    .filter(path -> {
                        try {
                            String nombre = path.getFileName().toString().toLowerCase();
                            // Buscar archivos temporales (xlsx, pdf, tmp, etc.)
                            boolean esArchivoTemporal = nombre.endsWith(".xlsx") || 
                                                     nombre.endsWith(".pdf") || 
                                                     nombre.endsWith(".tmp") ||
                                                     nombre.contains("temp");
                            
                            if (esArchivoTemporal) {
                                long tiempoModificacion = Files.getLastModifiedTime(path).toMillis();
                                return (horaActual - tiempoModificacion) > unaHoraEnMillis;
                            }
                            return false;
                        } catch (IOException e) {
                            return false;
                        }
                    })
                    .forEach(path -> {
                        try {
                            Files.deleteIfExists(path);
                            System.out.println("Archivo temporal eliminado: " + path.getFileName());
                        } catch (IOException e) {
                            System.err.println("Error al eliminar archivo temporal: " + e.getMessage());
                        }
                    });
            }
        } catch (IOException e) {
            System.err.println("Error al limpiar archivos temporales: " + e.getMessage());
        }
    }
    
    /**
     * Obtiene información sobre el archivo guardado
     * @return String con información del archivo o null si no existe
     */
    public String obtenerInfoArchivoGuardado() {
        File archivo = new File(RUTA_COMPLETA);
        
        if (!archivo.exists()) {
            return null;
        }
        
        long tamaño = archivo.length();
        long fechaModificacion = archivo.lastModified();
        
        return String.format("Archivo: %s\nTamaño: %d bytes\nÚltima modificación: %s",
                archivo.getName(), tamaño, new java.util.Date(fechaModificacion).toString());
    }
}
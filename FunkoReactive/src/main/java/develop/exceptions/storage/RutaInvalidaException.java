package develop.exceptions.storage;
/**
 * Excepcion lanzada cuando se detecta una ruta invalida en el sistema de almacenamiento.
 * Esta excepción esta relacionada con errores en las rutas de acceso a archivos o directorios
 * en el sistema de almacenamiento.
 *
 * @version 1.0
 */
public class RutaInvalidaException extends StorageException {
    /**
     * Construye una nueva RutaInvalidaException con el mensaje detallado especificado.
     *
     * @param message El mensaje detallado que explica la razon de la excepcion.
     */
    public RutaInvalidaException(String message) {
        super(message);
    }
}
package develop.exceptions.funkos;

/**
 * Una clase base abstracta para excepciones personalizadas relacionadas con objetos Funko.
 * Esta clase extiende la clase de excepcion estándar de Java para crear excepciones personalizadas
 * especificas para objetos Funko y sus operaciones.
 *
 * @version 1.0
 */
public abstract class FunkoException extends Exception {

    /**
     * Construye una nueva FunkoException con el mensaje detallado especificado.
     *
     * @param message El mensaje detallado que explica la razon de la excepcion.
     */
    public FunkoException(String message) {
        super(message);
    }
}

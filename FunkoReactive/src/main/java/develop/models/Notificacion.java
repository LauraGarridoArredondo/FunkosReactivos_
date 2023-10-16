package develop.models;
/**
 * Clase que representa una notificacion generica con un tipo y contenido asociado.
 *
 * @param <T> El tipo de contenido de la notificacion.
 */
public class Notificacion<T> {
    private Tipo tipo;
    private T contenido;
    /**
     * Crea una nueva instancia de Notificacion con un tipo y contenido especificos.
     *
     * @param tipo      El tipo de la notificacion (NEW, UPDATED, o DELETED).
     * @param contenido El contenido asociado a la notificacion.
     */
    public Notificacion(Tipo tipo, T contenido) {
        this.tipo = tipo;
        this.contenido = contenido;
    }
    /**
     * Obtiene el tipo de la notificacion.
     *
     * @return El tipo de la notificacion.
     */
    public Tipo getTipo() {
        return tipo;
    }
    /**
     * Establece el tipo de la notificacioon.
     *
     * @param tipo El tipo de la notificacion.
     */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    /**
     * Obtiene el contenido asociado a la notificacion.
     *
     * @return El contenido de la notificacion.
     */
    public T getContenido() {
        return contenido;
    }
    /**
     * Establece el contenido asociado a la notificacion.
     *
     * @param contenido El contenido de la notificacion.
     */
    public void setContenido(T contenido) {
        this.contenido = contenido;
    }
    /**
     * Devuelve una representacion en forma de cadena de la notificacion.
     *
     * @return Una cadena que representa la notificacion, incluyendo su tipo y contenido.
     */
    @Override
    public String toString() {
        return "Notificacion{" +
                "tipo=" + tipo +
                ", contenido=" + contenido +
                '}';
    }
    /**
     * Enumeracion que define los tipos de notificacion posibles.
     */
    public enum Tipo {
        NEW, UPDATED, DELETED
    }
}
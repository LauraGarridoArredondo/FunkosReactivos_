package develop.services.cache;

import develop.models.Funko;
import reactor.core.publisher.Mono;
/**
 * Interfaz que define operaciones basicas para un sistema de cache generico.
 *
 * @param <K> El tipo de clave utilizada para acceder a elementos en el cache.
 * @param <V> El tipo de valor almacenado en el cache.
 */
public interface Cache<K, V> {
    /**
     * Almacena un valor en el cache asociado a una clave.
     *
     * @param key   La clave para asociar al valor.
     * @param value El valor que se almacenara en el cache.
     * @return Un Mono que indica la finalizacion de la operacion de almacenamiento.
     */
    Mono <Void> put(K key, V value);
    /**
     * Obtiene un valor del cache asociado a una clave.
     *
     * @param key La clave para buscar en el cache.
     * @return Un Mono que emite el valor asociado a la clave, o vacio si no se encuentra.
     */
    Mono<Funko> get(K key);
    /**
     * Elimina un valor del cache asociado a una clave.
     *
     * @param key La clave para eliminar del cache.
     * @return Un Mono que indica la finalizacion de la operacion de eliminacion.
     */
    Mono<Void> remove(K key);
    /**
     * Limpia el cache, eliminando todos los valores almacenados en el.
     */
    void clear();
    /**
     * Detiene y libera todos los recursos del sistema de cache.
     */
    void shutdown();
}
package develop.services.funkos;


import develop.models.Funko;
import develop.services.cache.Cache;
/**
 * Interfaz que extiende la funcionalidad de un sistema de cache para almacenar objetos Funko.
 * Proporciona operaciones especificas para trabajar con Funkos en el cache.
 */
public interface FunkosCache extends Cache<Long, Funko> {
}
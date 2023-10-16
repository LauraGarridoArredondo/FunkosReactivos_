package develop.services.funkos;

import develop.models.Funko;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Implementacion de un sistema de cache para objetos Funko.
 * Esta implementacion utiliza un mapa con eliminacion automatica de elementos mas antiguos.
 */
public class FunkosCacheImpl implements FunkosCache {
    private final Logger logger = LoggerFactory.getLogger(FunkosCacheImpl.class);
    private final int maxSize;
    private final Map<Long, Funko> cache;
    private final ScheduledExecutorService cleaner;

    /**
     * Constructor que crea una instancia de FunkosCacheImpl con un tamano maximo especificado.
     *
     * @param maxSize El tamano maximo de la cache.
     */
    public FunkosCacheImpl(int maxSize) {
        this.maxSize = maxSize;
        this.cache = new LinkedHashMap<Long, Funko>(maxSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Long, Funko> eldest) {
                return size() > maxSize;
            }
        };
        this.cleaner = Executors.newSingleThreadScheduledExecutor();
        this.cleaner.scheduleAtFixedRate(this::clear, 2, 2, TimeUnit.MINUTES);
    }
    /**
     * Agrega un objeto Funko al cache con una clave especifica.
     *
     * @param key   La clave para asociar al objeto Funko.
     * @param value El objeto Funko que se almacenara en el cache.
     * @return Un Mono que indica la finalizacion de la operacion de almacenamiento.
     */
    @Override
    public Mono<Void> put(Long key, Funko value) {
        logger.debug("Anadiendo funko a cache con id: " + key + " y valor: " + value);
        return Mono.fromRunnable(() -> cache.put(key, value));
    }
    /**
     * Obtiene un objeto Funko del cache con una clave especifica.
     *
     * @param key La clave para buscar en el cache.
     * @return Un Mono que emite el objeto Funko asociado a la clave, o vacio si no se encuentra.
     */
    @Override
    public Mono<Funko> get(Long key) {
        logger.debug("Obteniendo funko de cache con id: " + key);
        return Mono.justOrEmpty(cache.get(key));
    }
    /**
     * Elimina un objeto Funko del cache con una clave especifica.
     *
     * @param key La clave para eliminar del cache.
     * @return Un Mono que indica la finalizacion de la operacion de eliminacion.
     */
    @Override
    public Mono<Void> remove(Long key) {
        logger.debug("Eliminando funko de cache con id: " + key);
        return Mono.fromRunnable(() -> cache.remove(key));
    }
    /**
     * Limpia el cache eliminando los objetos Funko caducados.
     */
    @Override
    public void clear() {
        cache.entrySet().removeIf(entry -> {
            boolean shouldRemove = entry.getValue().getUpdatedAt().plusMinutes(2).isBefore(LocalDateTime.now());
            if (shouldRemove) {
                logger.debug("Autoeliminando por caducidad funko de cache con id: " + entry.getKey());
            }
            return shouldRemove;
        });
    }
    /**
     * Detiene el proceso de limpieza de la cache.
     */
    @Override
    public void shutdown() {
        cleaner.shutdown();
    }
    /**
     * Obtiene el tamano actual de la cache.
     *
     * @return El tamano de la cache.
     */
    public int getTamano(){
        return this.cache.size();
    }
}
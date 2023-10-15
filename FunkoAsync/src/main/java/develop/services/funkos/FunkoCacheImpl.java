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

public class FunkoCacheImpl implements  FunkoCache {
    private final Logger logger = LoggerFactory.getLogger(FunkoCacheImpl.class);
    private final int maxSize;
    private final Map<Long, Funko> cache;
    private final ScheduledExecutorService cleaner;
    private static FunkoCacheImpl instance;

    public static synchronized FunkoCacheImpl getInstance(int maxSize) {
        if(instance == null){
            instance = new FunkoCacheImpl(maxSize);
        }
        return instance;
    }

    private FunkoCacheImpl(int maxSize) {
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

    @Override
    public Mono<Void> put(Long key, Funko value) {
        return Mono.fromRunnable(() -> {
            logger.debug("Añadiendo funko a cache con id: " + key + " y valor: " + value);
            cache.put(key, value);
        });
    }

    @Override
    public Mono<Funko> get(Long key) {
        return Mono.fromCallable(() -> {
            logger.debug("Obteniendo funko de cache con id: " + key);
            return cache.get(key);
        });
    }

    @Override
    public Mono<Void> remove(Long key) {
        return Mono.fromRunnable(() -> {
            logger.debug("Eliminando funko de cache con id: " + key);
            cache.remove(key);
        });
    }

    @Override
    public Mono<Void> clear() {
        return Mono.fromRunnable(() -> {
            cache.entrySet().removeIf(entry -> {
                boolean shouldRemove = entry.getValue().getUpdatedAt().plusMinutes(2).isBefore(LocalDateTime.now());
                if (shouldRemove) {
                    logger.debug("Autoeliminando por caducidad funko de cache con id: " + entry.getKey());
                }
                return shouldRemove;
            });
        });
    }

    @Override
    public Mono<Void> shutdown() {
        return Mono.fromRunnable(() -> {
            cleaner.shutdown();
        });
    }
}

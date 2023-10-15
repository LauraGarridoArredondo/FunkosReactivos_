import develop.models.Funko;
import develop.services.funkos.FunkoCacheImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FunkoCacheImplTest {
    private FunkoCacheImpl funkoCache;

    @BeforeEach
    public void setUp() {
        funkoCache = FunkoCacheImpl.getInstance(2); // Tamaño máximo de 2 para las pruebas
    }

    @Test
    public void testPutAndGet() {
        Long key = 1L;
         Funko funko = new Funko();
         funko.setUpdatedAt(LocalDateTime.now());

        funkoCache.put(key, funko);

        Mono<Funko> retrievedFunko = funkoCache.get(key);

        assertEquals(funko, retrievedFunko);
    }

    @Test
    public void testRemove() {
        Long key = 2L;
        Funko funko = new Funko();
        funko.setUpdatedAt(LocalDateTime.now());

        funkoCache.put(key, funko);

        funkoCache.remove(key);

        Mono<Funko> retrievedFunko = funkoCache.get(key);

        assertNull(retrievedFunko);
    }
}

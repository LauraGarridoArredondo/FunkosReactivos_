import develop.models.Funko;
import develop.repositories.funkos.FunkosRepository;
import develop.services.funkos.FunkosServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class FunkosServiceImplTest {
    private FunkosRepository funkosRepository;
    private FunkosServiceImpl funkosService;

    @BeforeEach
    public void setUp() {
        funkosRepository = mock(FunkosRepository.class);
        funkosService = new FunkosServiceImpl(funkosRepository);
    }

    @Test
    public void testFindAll() {
        Funko funko1 = new Funko(1, "UUID-1", "Funko 1");
        Funko funko2 = new Funko(2, "UUID-2", "Funko 2");
        when(funkosRepository.findAll()).thenReturn(Flux.just(funko1, funko2));

        Flux<Funko> result = funkosService.findAll();

        assertNotNull(result);
        assertEquals(2, result.collectList().block().size());
    }

    @Test
    public void testDeleteById() {
        long funkoId = 1;
        when(funkosRepository.deleteById(funkoId)).thenReturn(Mono.just(true));

        Mono<Boolean> result = funkosService.deleteById(funkoId);

        assertNotNull(result);

        verify(funkosRepository).deleteById(funkoId);
    }

    @Test
    public void testFindAllByNombre() {
        String nombre = "Funko 1";
        Funko funko1 = new Funko(1, "UUID-1", nombre);
        Funko funko2 = new Funko(2, "UUID-2", nombre);
        when(funkosRepository.findAllByNombre(nombre)).thenReturn(Flux.just(funko1, funko2));

        Flux<Funko> result = funkosService.findAllByNombre(nombre);

        assertNotNull(result);
        assertEquals(2, result.collectList().block().size());
    }

    @Test
    public void testFindById() {
        long funkoId = 1;
        Funko funko = new Funko(funkoId, "UUID-1", "Funko 1");
        Optional<Funko> optionalFunko = Optional.of(funko);
        when(funkosRepository.findById(funkoId)).thenReturn((Mono<Funko>));

        Mono<Funko> result = funkosService.findById(funkoId);

        assertNotNull(result);
        assertTrue(result.hasElement().block());
    }

}

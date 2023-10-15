import develop.models.Funko;
import develop.models.Model;
import develop.repositories.funkos.FunkosRepository;
import develop.services.funkos.FunkosServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class FunkoCacheImplTest {
    private FunkosRepository funkosRepository;
    private FunkosServiceImpl funkosService;

    @BeforeEach
    public void setUp() {
        funkosRepository = mock(FunkosRepository.class);
        funkosService = new FunkosServiceImpl(funkosRepository);
    }

    @Test
    public void testFindAll() {
        Funko funko1 = Funko.builder()
                .id(1)
                .COD(UUID.fromString("UUID-1"))
                .myId(1)
                .name("Funko 1")
                .model(Model.MARVEL)
                .price(10.0)
                .releaseData(LocalDate.now())
                .build();

        Funko funko2 = Funko.builder()
                .id(2)
                .COD(UUID.fromString("UUID-2"))
                .myId(2)
                .name("Funko 2")
                .model(Model.DISNEY)
                .price(15.0)
                .releaseData(LocalDate.now())
                .build();


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

        Funko funko1 = Funko.builder()
                .id(1)
                .COD(UUID.fromString("UUID-1"))
                .myId(1)
                .name("Funko 1")
                .model(Model.MARVEL)
                .price(10.0)
                .releaseData(LocalDate.now())
                .build();

        Funko funko2 = Funko.builder()
                .id(2)
                .COD(UUID.fromString("UUID-2"))
                .myId(2)
                .name("Funko 2")
                .model(Model.DISNEY)
                .price(15.0)
                .releaseData(LocalDate.now())
                .build();

        when(funkosRepository.findAllByNombre(nombre)).thenReturn(Flux.just(funko1, funko2));

        Flux<Funko> result = funkosService.findAllByNombre(nombre);

        assertNotNull(result);
        assertEquals(2, result.collectList().block().size());
    }

    @Test
    public void testFindById() {
        long funkoId = 1;

        Funko funko = Funko.builder()
                .id(1)
                .COD(UUID.fromString("UUID-1"))
                .myId(1)
                .name("Funko 1")
                .model(Model.MARVEL)
                .price(10.0)
                .releaseData(LocalDate.now())
                .build();


        when(funkosRepository.findById(funkoId)).thenReturn(Mono.just(funko));

        Mono<Funko> result = funkosService.findById(funkoId);

        assertNotNull(result);
        assertTrue(result.hasElement().block());
    }
}

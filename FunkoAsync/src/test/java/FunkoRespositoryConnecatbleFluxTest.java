
import develop.models.Funko;
import develop.models.Model;
import develop.repositories.funkos.FunkosRepository;
import develop.services.funkos.FunkoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.LocalDate;
import java.util.UUID;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class FunkoRepositoryConnectableFluxTest {
    private FunkosRepository funkosRepository;
    private FunkoService funkoService;

    @BeforeEach
    public void setUp() {
        funkosRepository = mock(FunkosRepository.class);
        funkoService = new FunkoService(funkosRepository);
    }

    @Test
    public void testAddAndGetAll() {
        // Arrange
        var funko1 = Funko.builder()
                .name("Funko 1")
                .model(Model.MARVEL)
                .price(10.0)
                .build();
        var funko2 = Funko.builder()
                .name("Funko 2")
                .model(Model.DISNEY)
                .price(15.0)
                .build();
        when(funkosRepository.findAll()).thenReturn(Flux.just(funko1, funko2);

        // Act
        var result = funkoService.findAll().collectList().block();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Funko 1", result.get(0).getName());
        assertEquals(Model.MARVEL, result.get(0).getModel());
        assertEquals(10.0, result.get(0).getPrice());
    }

    @Test
    public void testAddAndDelete() {
        // Arrange
        var funko1 = Funko.builder()
                .name("Funko 1")
                .model(Model.MARVEL)
                .price(10.0)
                .build();
        when(funkosRepository.addFunko(funko1)).thenReturn(Mono.just(funko1));
        when(funkosRepository.deleteFunko(funko1.getId())).thenReturn(Mono.empty());

        // Act
        var addedFunko = funkoService.addFunko(funko1).block();
        funkoService.deleteFunko(funko1.getId()).block();

        // Assert
        assertNotNull(addedFunko);

        // Comprueba que se ha llamado al método de eliminación del repositorio
        verify(funkosRepository, times(1)).deleteFunko(funko1.getId());
    }

    @Test
    public void testNotifications() {
        // Arrange
        var funko1 = Funko.builder()
                .name("Funko 1")
                .model(Model.MARVEL)
                .price(10.0)
                .build();
        when(funkosRepository.addFunko(funko1)).thenReturn(Mono.just(funko1));

        // Act
        funkoService.setNotificationHandler(funko -> {
            assertEquals(funko1.getName(), funko.getName());
            assertNotNull(funko.getReleaseDate());
        });
        funkoService.notify(funko1);

        // Assert: Las notificaciones se verifican dentro del método setNotificationHandler
    }
}

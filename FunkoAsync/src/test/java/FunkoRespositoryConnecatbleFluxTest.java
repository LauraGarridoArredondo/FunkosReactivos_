import develop.models.Funko;
import develop.services.funkos.FunkoRepositoryConnecatbleFlux;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FunkoRepositoryConnecatbleFluxTest {
    private FunkoRepositoryConnecatbleFlux repository;

    @BeforeEach
    public void setUp() {
        repository = new FunkoRepositoryConnecatbleFlux();
    }

    @Test
    public void testAddAndGetAll() {
        Funko funko = new Funko(1, "UUID-1", "Funko 1");
        repository.add(funko);

        Flux<List<Funko>> allFunkosFlux = repository.getAllAsFlux();

        List<Funko> funkos = allFunkosFlux.blockFirst(); // Obtener el primer valor del flux

        assertEquals(1, funkos.size());
        assertEquals(funko, funkos.get(0));
    }

    @Test
    public void testAddAndDelete() {
        Funko funko1 = new Funko(1, "UUID-1", "Funko 1");
        Funko funko2 = new Funko(2, "UUID-2", "Funko 2");

        repository.add(funko1);
        repository.add(funko2);

        repository.delete(1); // Eliminar funko1

        Flux<List<Funko>> allFunkosFlux = repository.getAllAsFlux();

        List<Funko> funkos = allFunkosFlux.blockFirst();

        assertEquals(1, funkos.size());
        assertEquals(funko2, funkos.get(0));
    }

    @Test
    public void testNotifications() {
        Funko funko = new Funko(1, "UUID-1", "Funko 1");
        repository.add(funko);

        Flux<String> notifications = repository.getNotificationAsFlux();

        String notification = notifications.blockFirst();

        assertEquals("Se ha añadido un nuevo Funko: " + funko, notification);
    }
}

package develop.services.funkos;

import develop.models.Funko;
import reactor.core.publisher.ConnectableFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FunkoRepositoryConnecatbleFlux {
    private final List<Funko> funkos = new ArrayList<>();
    private final Flux<List<Funko>> funkoFlux;
    private final Flux<String> funkoNotificationFlux;
    private FluxSink<List<Funko>> funkoFluxSink;
    private FluxSink<String> funkoNotification;

    public FunkoRepositoryConnecatbleFlux() {
        ConnectableFlux<List<Funko>> connectableFunkoFlux = Flux.<List<Funko>>create(emitter -> this.funkoFluxSink = emitter).publish();
        ConnectableFlux<String> connectableFunkoNotificationFlux = Flux.<String>create(emitter -> this.funkoNotification = emitter).publish();

        funkoFlux = connectableFunkoFlux;
        funkoNotificationFlux = connectableFunkoNotificationFlux;

        connectableFunkoFlux.connect();
        connectableFunkoNotificationFlux.connect();
    }

    public void add(Funko funko) {
        funkos.add(funko);
        funkoFluxSink.next(funkos);
        funkoNotification.next("Se ha añadido un nuevo Funko: " + funko);
    }

    public void delete(long id) {
        Optional<Funko> funkoToRemove = funkos.stream()
                .filter(f -> f.getId() == id) // Compara el ID primitivo con el que deseas eliminar
                .findFirst();

        funkoToRemove.ifPresent(f -> {
            funkos.remove(f);
            funkoFluxSink.next(funkos);
            funkoNotification.next("Se ha eliminado un Funko: " + f);
        });
    }

    public Flux<List<Funko>> getAllAsFlux() {
        return funkoFlux;
    }

    public Flux<String> getNotificationAsFlux() {
        return funkoNotificationFlux;
    }
}

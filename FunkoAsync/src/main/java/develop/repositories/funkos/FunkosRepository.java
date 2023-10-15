package develop.repositories.funkos;

import develop.models.Funko;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.Optional;

public interface FunkosRepository {

    Flux<Funko> findAllByNombre(String nombre);

    Mono<Funko> findById(long id);

    Mono<Funko> save(Funko funko);

    Mono<Funko> update(Funko funko);

    Mono<Optional<Funko>> findById(Long id);

    Flux<Funko> findAll();

    Mono<Boolean> deleteById(Long id);

    Mono<Void> deleteAll();

    Flux<Funko> findByNombre(String nombre);
}

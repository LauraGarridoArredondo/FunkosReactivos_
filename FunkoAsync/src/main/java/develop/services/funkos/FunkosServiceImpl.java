package develop.services.funkos;

import develop.models.Funko;
import develop.repositories.funkos.FunkosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class FunkosServiceImpl implements FunkosRepository {
    private static final int CACHE_SIZE = 10;
    private final FunkoCache cache;
    private final Logger logger = LoggerFactory.getLogger(FunkosServiceImpl.class);
    private final FunkosRepository funkosRepository;

    public FunkosServiceImpl(FunkosRepository funkosRepository) {
        this.funkosRepository = funkosRepository;
        this.cache = FunkoCacheImpl.getInstance(CACHE_SIZE);
    }

    public FunkosServiceImpl(FunkoCache cache, FunkosRepository funkosRepository) {
        this.cache = cache;
        this.funkosRepository = funkosRepository;
    }

    public static FunkosServiceImpl getInstance(FunkosRepository funkosRepository) {
        return new FunkosServiceImpl(funkosRepository); // Crear una nueva instancia de FunkosServiceImpl
    }


    @Override
    public Flux<Funko> findAll() {
        return funkosRepository.findAll()
                .doOnNext(funko -> updateCache(funko));
    }

    @Override
    public Mono<Boolean> deleteById(Long id) {
        return funkosRepository.deleteById(id)
                .flatMap(deleted -> {
                    if (deleted) {
                        cache.remove(id);
                    }
                    return Mono.just(deleted);
                });
    }

    @Override
    public Flux<Funko> findAllByNombre(String nombre) {
        return funkosRepository.findAllByNombre(nombre)
                .flatMap(funko -> {
                    updateCache(funko);
                    return Mono.just(funko);
                });
    }


    @Override
    public Mono<Funko> findById(long id) {
        return funkosRepository.findById(id)
                .flatMap(optionalFunko -> {
                    if (optionalFunko != null) {
                        Funko funko = optionalFunko;
                        cache.put(funko.getId(), funko);
                        return Mono.just(funko);
                    } else {
                        return Mono.empty();
                    }
                });
    }


    @Override
    public Mono<Funko> save(Funko funko) {
        return funkosRepository.save(funko)
                .doOnNext(f -> updateCache(f));
    }

    @Override
    public Mono<Funko> update(Funko funko) {
        return funkosRepository.update(funko)
                .doOnNext(f -> updateCache(f));
    }

    @Override
    public Mono<Optional<Funko>> findById(Long id) {
        return funkosRepository.findById(id)
                .flatMap(optionalFunko -> {
                    if (optionalFunko.isPresent()) {
                        Funko funko = optionalFunko.get();
                        cache.put(funko.getId(), funko);
                    }
                    return Mono.just(Optional.ofNullable(optionalFunko.orElse(null)));
                })
                .onErrorMap(throwable -> new RuntimeException("Error al obtener el funko", throwable));
    }



    @Override
    public Mono<Void> deleteAll() {
        return funkosRepository.deleteAll()
                .then(Mono.fromRunnable(() -> cache.clear()));
    }

    @Override
    public Flux<Funko> findByNombre(String nombre) {
        return funkosRepository.findByNombre(nombre);
    }

    private void updateCache(Funko funko) {
        if (funko != null) {
            cache.put(funko.getId(), funko);
        }
    }

}

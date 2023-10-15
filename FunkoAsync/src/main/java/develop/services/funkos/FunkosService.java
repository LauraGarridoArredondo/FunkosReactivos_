package develop.services.funkos;

import develop.exceptions.funkos.FunkoNoEncotradoException;
import develop.models.Funko;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public interface FunkosService {
    Flux<Funko> findAll() throws SQLException, ExecutionException, InterruptedException;

    Flux <Funko> findAllByNombre(String nombre) throws SQLException, ExecutionException, InterruptedException;

    Mono <Optional<Funko>> findById(long id) throws SQLException, ExecutionException, InterruptedException;

    Mono <Funko> save(Funko alumno) throws SQLException, ExecutionException, InterruptedException;

    Mono <Funko> update(Funko alumno) throws SQLException, FunkoNoEncotradoException, ExecutionException, InterruptedException;

    Mono<Boolean> deleteById(long id) throws SQLException, ExecutionException, InterruptedException;

    Mono <Void> deleteAll() throws SQLException, ExecutionException, InterruptedException;
}
package develop.repositories.funkos;

import develop.models.Funko;
import develop.repositories.crud.CrudRepository;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.Optional;
import java.util.UUID;
/**
 * Interfaz que extiende la funcionalidad del repositorio CRUD para entidades Funko.
 * Proporciona metodos adicionales para buscar Funkos por UUID y nombre.
 */
public interface FunkosRepository  extends CrudRepository<Funko, Long> {
    /**
     * Busca un Funko por su UUID.
     *
     * @param uuid El UUID del Funko a buscar.
     * @return Un Mono que emite el Funko encontrado, o vacio si no se encuentra.
     */
    Mono<Funko> findByUuid(UUID uuid);
    /**
     * Busca Funkos por su nombre.
     *
     * @param nombre El nombre de los Funkos a buscar.
     * @return Un Flux que emite todos los Funkos con el nombre especificado.
     */
    Flux<Funko> findByNombre(String nombre);
}

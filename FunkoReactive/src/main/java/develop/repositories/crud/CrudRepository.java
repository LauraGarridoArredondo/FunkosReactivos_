package develop.repositories.crud;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.util.Optional;
/**
 * Interfaz generica para operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre entidades.
 *
 * @param <T>  El tipo de entidad gestionada por el repositorio.
 * @param <ID> El tipo de identificador unico de la entidad.
 */
public interface CrudRepository<T, ID> {

    /**
     * Guarda una entidad en el repositorio.
     *
     * @param t La entidad a guardar.
     * @return Un Mono que emite la entidad guardada.
     */
    Mono<T> save(T t);
    /**
     * Actualiza una entidad en el repositorio.
     *
     * @param t La entidad a actualizar.
     * @return Un Mono que emite la entidad actualizada.
     */
    Mono<T> update(T t);
    /**
     * Busca una entidad por su identificador unico.
     *
     * @param id El identificador unico de la entidad.
     * @return Un Mono que emite la entidad encontrada, o vacio si no se encuentra.
     */
    Mono<T> findById(ID id);
    /**
     * Recupera todas las entidades del repositorio.
     *
     * @return Un Flux que emite todas las entidades del repositorio.
     */
    Flux<T> findAll();
    /**
     * Elimina una entidad por su identificador unico.
     *
     * @param id El identificador unico de la entidad a eliminar.
     * @return Un Mono que emite verdadero si la eliminacion fue exitosa, falso si no se encontro la entidad.
     */
    Mono<Boolean> deleteById(ID id);
    /**
     * Elimina todas las entidades del repositorio.
     *
     * @return Un Mono que indica la finalizacion de la eliminacion de todas las entidades.
     */
    Mono<Void> deleteAll();
}

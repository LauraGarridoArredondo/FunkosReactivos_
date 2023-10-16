package develop.services.funkos;


import develop.models.Funko;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;
/**
 * Interfaz que define operaciones para gestionar objetos Funko, como la busqueda, creacion, actualizacion y eliminacion.
 */
public interface FunkosService {
    /**
     * Obtiene un Flux que emite todos los objetos Funko existentes.
     *
     * @return Un Flux que emite todos los objetos Funko.
     */
    Flux<Funko> findAll();
    /**
     * Obtiene un Flux que emite todos los objetos Funko con un nombre especifico.
     *
     * @param nombre El nombre a buscar en los objetos Funko.
     * @return Un Flux que emite objetos Funko que coincidan con el nombre especificado.
     */
    Flux<Funko> findAllByNombre(String nombre);
    /**
     * Obtiene un objeto Funko por su identificador unico.
     *
     * @param id El identificador unico del objeto Funko a buscar.
     * @return Un Mono que emite el objeto Funko encontrado, o vacio si no se encuentra.
     */
    Mono<Funko> findById(long id);
    /**
     * Guarda un nuevo objeto Funko en el sistema.
     *
     * @param funko El objeto Funko que se va a guardar.
     * @return Un Mono que emite el objeto Funko guardado.
     */
    Mono<Funko> save(Funko funko);
    /**
     * Actualiza un objeto Funko existente en el sistema.
     *
     * @param funko El objeto Funko que se va a actualizar.
     * @return Un Mono que emite el objeto Funko actualizado.
     */
    Mono<Funko> update(Funko funko);
    /**
     * Elimina un objeto Funko por su identificador unico.
     *
     * @param id El identificador unico del objeto Funko a eliminar.
     * @return Un Mono que indica la finalizacion de la operacion de eliminacion.
     */
    Mono<Funko> deleteById(long id);
    /**
     * Elimina todos los objetos Funko del sistema.
     *
     * @return Un Mono que indica la finalizacion de la operacion de eliminacion.
     */
    Mono<Void> deleteAll();

    /**
     * Busca un objeto Funko por su UUID (identificador universal unico).
     *
     * @param uuid El UUID del objeto Funko a buscar.
     * @return Un Mono que emite el objeto Funko encontrado, o vacio si no se encuentra.
     */
    Mono<Funko> findByUuid(UUID uuid);
}


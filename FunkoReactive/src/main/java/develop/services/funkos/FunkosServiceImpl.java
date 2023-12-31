package develop.services.funkos;

import develop.exceptions.funkos.FunkoNoEncotradoException;
import develop.exceptions.storage.RutaInvalidaException;
import develop.models.Funko;
import develop.models.Notificacion;
import develop.repositories.funkos.FunkosRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
/**
 * Implementacion de la interfaz FunkosService que gestiona las operaciones relacionadas con los objetos Funko, como la busqueda, creacion, actualizacion y eliminacion.
 */
public class FunkosServiceImpl implements FunkosService {
    private static final int CACHE_SIZE = 10;
    private static FunkosServiceImpl instance;
    private final FunkosCache cache;
    private final FunkosNotification notification;
    private final Logger logger = LoggerFactory.getLogger(FunkosServiceImpl.class);
    private final FunkosRepository funkosRepository;
    private final FunkoStorage storage;

    private FunkosServiceImpl(FunkosRepository funkosRepository, FunkosNotification notification, FunkoStorage storage) {
        this.funkosRepository = funkosRepository;
        this.cache = new FunkosCacheImpl(CACHE_SIZE);
        this.notification = notification;
        this.storage = storage;
    }


    public static FunkosServiceImpl getInstance(FunkosRepository funkosRepository, FunkosNotification notification, FunkoStorage storage) {
        if (instance == null) {
            instance = new FunkosServiceImpl(funkosRepository, notification, storage);
        }
        return instance;
    }

    @Override
    public Flux<Funko> findAll() {
        logger.debug("Buscando todos los funkos");
        return funkosRepository.findAll();
    }

    @Override
    public Flux<Funko> findAllByNombre(String nombre) {
        logger.debug("Buscando todos los funkos por nombre");
        return funkosRepository.findByNombre(nombre);
    }

    @Override
    public Mono<Funko> findById(long id) {
        logger.debug("Buscando funko por id: " + id);
        return cache.get(id)
                .switchIfEmpty(funkosRepository.findById(id)
                        .flatMap(funko -> cache.put(funko.getId(), funko)
                                .then(Mono.just(funko)))
                        .switchIfEmpty(Mono.error(new FunkoNoEncotradoException("Funko con id " + id + " no encontrado"))));
    }


    public Mono<Funko> findByUuid(UUID uuid) {
        logger.debug("Buscando funko por uuid: " + uuid);
        return funkosRepository.findByUuid(uuid)
                .flatMap(funko -> cache.put(funko.getId(), funko)
                        .then(Mono.just(funko)))
                .switchIfEmpty(Mono.error(new FunkoNoEncotradoException("Funko con uuid " + uuid + " no encontrado")));
    }


    public Mono<Funko> saveWithoutNotification(Funko funko) {
        logger.debug("Guardando funko sin notificacion: " + funko);
        return funkosRepository.save(funko)
                .flatMap(saved -> findByUuid(saved.getCOD()));
    }

    @Override
    public Mono<Funko> save(Funko funko) {
        logger.debug("Guardando funko: " + funko);
        return saveWithoutNotification(funko)
                .doOnSuccess(saved -> notification.notify(new Notificacion<>(Notificacion.Tipo.NEW, saved)));
    }

    private Mono<Funko> updateWithoutNotification(Funko funko) {
        logger.debug("Actualizando funko sin notificacion: " + funko);
        return funkosRepository.findById(funko.getId())
                .switchIfEmpty(Mono.error(new FunkoNoEncotradoException("Funko con id " + funko.getId() + " no encontrado")))
                .flatMap(existing -> funkosRepository.update(funko)
                        .flatMap(updated -> cache.put(updated.getId(), updated)
                                .thenReturn(updated)));
    }


    @Override
    public Mono<Funko> update(Funko funko) {
        logger.debug("Actualizando funko: " + funko);
        return updateWithoutNotification(funko)
                .doOnSuccess(updated -> notification.notify(new Notificacion<>(Notificacion.Tipo.UPDATED, updated)));
    }

    private Mono<Funko> deleteByIdWithoutNotification(long id) {
        logger.debug("Borrando funko sin notificacion con id: " + id);
        return funkosRepository.findById(id)
                .switchIfEmpty(Mono.error(new FunkoNoEncotradoException("Funko con id " + id + " no encontrado")))
                .flatMap(funko -> cache.remove(funko.getId())
                        .then(funkosRepository.deleteById(funko.getId()))
                        .thenReturn(funko));
    }

    @Override
    public Mono<Funko> deleteById(long id) {
        logger.debug("Borrando funko por id: " + id);
        return deleteByIdWithoutNotification(id)
                .doOnSuccess(deleted -> notification.notify(new Notificacion<>(Notificacion.Tipo.DELETED, deleted)));
    }
    /**
     * Importa una lista de Funkos desde un archivo CSV.
     *
     * @return Un Flux que emite los Funkos importados desde el archivo CSV.
     * @throws IOException Si ocurre un error durante la importacion del archivo.
     */
    public Flux<Funko> importFile() throws IOException{
        logger.debug("Importando lista de funkos desde csv");
        return storage.importCsv();
    }

    @Override
    public Mono<Void> deleteAll() {
        logger.debug("Borrando todos los funkos");
        cache.clear();
        return funkosRepository.deleteAll()
                .then(Mono.empty());
    }
    /**
     * Exporta la lista de Funkos actual a un archivo JSON.
     *
     * @param file La ruta del archivo en la que se exportara la lista de Funkos en formato JSON.
     * @return Un Mono que indica la finalizacion de la operacion de exportacion.
     * @throws IOException Si ocurre un error durante la exportacion del archivo.
     */
    public Mono<Void> exportFile(String file) throws IOException {
        logger.debug("Exportando archivos a Json");
        List<Funko> funkoList = funkosRepository.findAll().collectList().block();
        return storage.exportJson(funkoList, file);
    }
    /**
     * Obtiene un Flux que emite las notificaciones relacionadas con los Funkos, como creacion, actualizacion y eliminacion.
     *
     * @return Un Flux que emite las notificaciones de Funkos.
     */
    public Flux<Notificacion<Funko>> getNotifications() {
        return notification.getNotificationAsFlux();
    }
}
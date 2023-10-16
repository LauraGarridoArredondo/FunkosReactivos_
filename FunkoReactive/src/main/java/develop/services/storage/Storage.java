package develop.services.storage;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

/**
 * Interfaz generica para el almacenamiento y recuperacion de datos de un tipo especifico.
 *
 * @param <T> El tipo de datos a almacenar y recuperar.
 */
public interface Storage<T> {
    /**
     * Exporta una lista de objetos de tipo T a un formato especifico, como JSON.
     *
     * @param data Los objetos a exportar.
     * @param file El nombre del archivo de destino.
     * @return Un Mono<Void> que indica la finalizacion de la exportacion.
     * @throws IOException Si se produce un error durante la exportacion.
     */
    Mono<Void> exportJson(List<T> data, String file) throws IOException;
    /**
     * Importa datos de un formato especifico, como CSV, y los convierte en objetos de tipo T.
     *
     * @return Un Flux que emite objetos de tipo T importados desde el archivo.
     * @throws IOException Si se produce un error durante la importacion.
     */
    Flux<T> importCsv() throws IOException;
}
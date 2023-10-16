package develop.services.funkos;

import develop.models.Funko;
import develop.services.storage.Storage;
/**
 * Interfaz que define la funcionalidad de almacenamiento de objetos Funko. Esta interfaz extiende la interfaz Storage
 * y se utiliza para almacenar y recuperar objetos Funko en un medio de almacenamiento especifico, como un archivo.
 *
 * El tipo de objeto Funko que se almacena en el medio de almacenamiento.
 */
public interface FunkoStorage extends Storage<Funko> {
    // Esta interfaz hereda los metodos de la interfaz Storage y no define metodos adicionales.
}

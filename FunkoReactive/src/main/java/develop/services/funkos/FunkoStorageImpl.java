package develop.services.funkos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import develop.exceptions.storage.RutaInvalidaException;
import develop.models.Funko;
import develop.models.Model;
import develop.utils.LocalDateAdapter;
import develop.utils.LocalDateTimeAdapter;
import develop.utils.UuidAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
/**
 * Implementacion de la interfaz FunkoStorage que se encarga de exportar datos Funko a archivos JSON y de importar datos
 * Funko desde archivos CSV.
 */
public class FunkoStorageImpl implements FunkoStorage {
    private final Logger logger = LoggerFactory.getLogger(FunkoStorageImpl.class);
    private static FunkoStorageImpl instance;

    // Constructor privado para garantizar un Singleton
    private FunkoStorageImpl() {}
    /**
     * Obtiene una instancia de la implementacion de FunkoStorage.
     *
     * @return Una instancia de FunkoStorageImpl.
     */
    public static FunkoStorageImpl getInstance() {
        if (instance == null) {
            instance = new FunkoStorageImpl();
        }
        return instance;
    }
    /**
     * Exporta una lista de objetos Funko a un archivo JSON.
     *
     * @param data Los objetos Funko a exportar.
     * @param file El nombre del archivo JSON de respaldo.
     * @return Un Mono<Void> que indica la finalizacion de la exportacion.
     */
    @Override
    public Mono<Void> exportJson(List<Funko> data, String file) {
        return Mono.fromCallable(() -> {
                    if (!validarRuta(file)) {
                        logger.error("Ruta de fichero invalida: " + file);
                        throw new RutaInvalidaException("Ruta de fichero invalida: " + file);
                    } else {
                        String appPath = System.getProperty("user.dir");
                        String dataPath = appPath + File.separator + "data";
                        String backupFile = dataPath + File.separator + file;
                        Gson gson = new GsonBuilder()
                                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                                .registerTypeAdapter(UUID.class, new UuidAdapter())
                                .setPrettyPrinting()
                                .create();
                        String json = gson.toJson(data);
                        logger.debug("Escribiendo el archivo backup: " + backupFile);
                        Files.writeString(new File(backupFile).toPath(), json);
                    }
                    return null;
                })
                .onErrorMap(e -> {
                    if (e instanceof RutaInvalidaException) {
                        return e;
                    }
                    return new RuntimeException("Error al escribir el archivo de respaldo", e);
                })
                .then();
    }
    /**
     * Valida si una ruta de archivo es un archivo JSON valido.
     *
     * @param ruta La ruta del archivo a validar.
     * @return true si la ruta es un archivo JSON valido, de lo contrario, false.
     */
    private boolean validarRuta(String ruta) {
        String[] partes = ruta.split("\\.");
        if(partes.length > 1 && partes[partes.length - 1].equalsIgnoreCase("json")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Importa objetos Funko desde un archivo CSV.
     *
     * @return Un Flux que emite objetos Funko importados desde el archivo CSV.
     * @throws IOException Si se produce un error al leer el archivo CSV.
     */
    @Override
    public Flux<Funko> importCsv() throws IOException {
        return Flux.create(sink -> {
            String dataPath = "data" + File.separator + "funkos.csv";
            String appPath = System.getProperty("user.dir");
            Path filePath = Paths.get(appPath + File.separator + dataPath);
            logger.debug("Leyendo el archivo: " + filePath.toString());
            try(BufferedReader reader = new BufferedReader(new FileReader(filePath.toString()))){
                reader.lines().skip(1).map(lines -> getFunko(lines)).forEach(funko -> sink.next(funko));
                sink.complete();
            } catch (FileNotFoundException e) {
                logger.error("No se encotro el archivo: " + filePath.toString());
                sink.error(e);
            } catch (IOException e) {
                logger.error("Error al leer el archivo: " + filePath.toString());
                sink.error(e);
            }

        });
    }

    private UUID getUUID(String uuid) {
        return uuid.length() > 36 ? UUID.fromString(uuid.substring(0, 36)) : UUID.fromString(uuid);
    }

    private LocalDate getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

    private Funko getFunko(String linea) {
        String[] campos = linea.split(",");
        UUID COD = getUUID(campos[0]);
        String name = campos[1];
        Model model = Model.valueOf(campos[2]);
        double price = Double.parseDouble(campos[3]);
        LocalDate releaseData = getDate(campos[4]);
        return Funko.builder()
                .COD(COD)
                .name(name)
                .model(model)
                .price(price)
                .releaseData(releaseData)
                .build();
    }
}

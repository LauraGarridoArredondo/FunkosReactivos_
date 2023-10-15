package develop.controller;

import com.google.gson.reflect.TypeToken;
import develop.models.Funko;
import com.google.gson.Gson;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ExportJSON {
    public static Mono<Void> exportarAJson(List<Funko> funkoList, String filename) {
        return Mono.fromCallable(() -> {
            try {
                Gson gson = new Gson();
                String json = gson.toJson(funkoList);
                Path path = Path.of(filename);
                Files.write(path, json.getBytes());
                return null;
            } catch (Exception e) {
                throw new RuntimeException("Error al exportar los datos a JSON", e);
            }
        }).subscribeOn(Schedulers.boundedElastic()).then();
    }


    public static Mono<List<Funko>> importarDesdeJson(String filename) {
        return Mono.fromCallable(() -> {
            try {
                Path path = Path.of(filename);
                if (Files.exists(path)) {
                    String json = Files.readString(path);
                    Gson gson = new Gson();
                    //TypeToken es una clase proporcionada por la biblioteca Gson en Java que se utiliza para capturar
                    // la información de tipo en tiempo de ejecución.
                    // Gson necesita esta información de tipo para deserializar objetos genéricos a
                    // partir de datos JSON.
                    Type type = new TypeToken<List<Funko>>() {}.getType();
                    List<Funko> funkoList = gson.fromJson(json, type);
                    return funkoList;
                }
                return null;  // Retorna null cuando el archivo no existe.
            } catch (Exception e) {
                throw new RuntimeException("Error importando el JSON", e);
            }
        }).subscribeOn(Schedulers.boundedElastic());
    }
}

//Utilizamos subscribeOn / Schedulers. para asegurarnos
// de que la operación se realice en un hilo de E/S separado


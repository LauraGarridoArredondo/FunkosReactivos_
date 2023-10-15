package develop.controller;

import develop.models.Funko;
import com.google.gson.Gson;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.server.ExportException;
import java.util.List;

public class ExportJSON {
    public static Mono<Void> exportarAJson(List<Funko> funkoList, String filename) {
        try{
        return Mono.fromCallable(() -> {
            Gson gson = new Gson();
            String json = gson.toJson(funkoList);
            Path path = Path.of(filename);
            Files.write(path, json.getBytes());
            return null;
        }).subscribeOn(Schedulers.boundedElastic()).then();
        }catch (Exception e){
            return Mono.error(new ExportException("Error al exportar los datos a Json"));
        }
        //Utilizamos subscribeOn(Schedulers.elastic())
        // para asegurarnos de que la operación se realice en un hilo de E/S separado
    }

    public static Mono<List<?>> importarDesdeJson(String filename) {
       try{
        return Mono.fromCallable(() -> {
            Path path = Path.of(filename);
            if (Files.exists(path)) {
                String json = Files.readString(path);
                Gson gson = new Gson();
                List<Funko> funkoList = gson.fromJson(json, List.class);
                return funkoList;
            } else {
                return List.of();
            }
        }).subscribeOn(Schedulers.boundedElastic());
       }catch (Exception e){
           return Mono.error(new ExportException("Error importando el JSON"));
       }
    //Utilizamos subscribeOn(Schedulers.elastic()) para asegurarnos
        // de que la operación se realice en un hilo de E/S separado
    }
}


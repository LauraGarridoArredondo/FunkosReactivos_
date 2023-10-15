package develop;

import develop.models.Model;
import develop.controller.CSVReader;
import develop.models.Funko;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Leer los datos desde un archivo CSV usando Reactor
        CSVReader reader = new CSVReader();
        Flux<Funko> funkoFlux = reader.readCSV();

        // Crear una lista en memoria para almacenar los Funkos
        List<Funko> funkoList = new ArrayList<>();

        // Guardar los Funkos en la lista en memoria
        funkoFlux.subscribe(funkoList::add);

        // Consultas y operaciones en la lista en memoria
        consultasEnMemoria(funkoList);
    }

    private static void consultasEnMemoria(List<Funko> funkoList) {
        // Consulta 1: Funko más caro en la colección
        Funko mostExpensiveFunko = funkoList.stream()
                .max(Comparator.comparing(Funko::getPrice))
                .orElse(null);
        if (mostExpensiveFunko != null) {
            System.out.println("Funko más caro: " + mostExpensiveFunko);
        } else {
            System.out.println("No hay Funkos en la colección.");
        }

        // Consulta 2: Media de precio de Funkos
        double averagePrice = funkoList.stream()
                .mapToDouble(Funko::getPrice)
                .average()
                .orElse(0.0);
        System.out.println("Media de precio de Funkos: " + averagePrice);

        // Consulta 3: Funkos que han sido lanzados en 2023
        funkoList.stream()
                .filter(funko -> funko.getReleaseData() != null && funko.getReleaseData().getYear() == 2023)
                .forEach(releasedIn2023 -> {
                    System.out.println("Funkos lanzados en 2023: " + releasedIn2023);
                });

        // Consulta 4: Número de Funkos de Stitch
        long count = funkoList.stream()
                .filter(funko -> funko.getModel() == Model.STITCH)
                .count();
        System.out.println("Número de Funkos de Stitch: " + count);

        // Consulta 5: Listado de Funkos de Stitch
        funkoList.stream()
                .filter(funko -> funko.getModel() == Model.STITCH)
                .forEach(stitchFunko -> {
                    System.out.println("Funko de Stitch: " + stitchFunko);
                });
    }

    private static void notificar(Funko funko) {
        // Lógica de notificación
        System.out.println("🟢 Notificación: Nuevo Funko guardado: " + funko);
    }
}

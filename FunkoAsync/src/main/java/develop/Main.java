package develop;

import develop.models.Model;
import develop.services.funkos.FunkoRepositoryConnecatbleFlux;
import develop.controller.CSVReader;
import develop.models.Funko;
import develop.repositories.funkos.FunkosRepository;
import develop.repositories.funkos.FunkosRepositoryImpl;
import develop.services.database.DatabaseManager;
import develop.services.funkos.FunkosServiceImpl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.SQLException;
import java.util.Comparator;


public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Leer los datos desde un archivo CSV usando Reactor
        CSVReader reader = new CSVReader();
        Flux<Funko> funkoFlux = reader.readCSV();

        // Inicializar los servicios y repositorios
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        FunkosRepository funkosRepository = FunkosRepositoryImpl.getInstance(databaseManager);
        FunkosServiceImpl Servicio = new FunkosServiceImpl(funkosRepository);

        // Guardar los Funkos en la base de datos
        funkoFlux
                .flatMap(funko -> {
                    return Servicio.save(funko);
                }) // Utiliza el método `save` del servicio
                .subscribe();

        // Buscar y mostrar Funkos por ID
        long funkoId = 91L;
        Mono<Funko> funkoMono = Servicio.findById(funkoId);

        funkoMono
                .subscribe(funko -> {
                    if (funko != null) {
                        System.out.println("Funko encontrado: " + funko);
                    } else {
                        System.out.println("Funko no encontrado para el ID: " + funkoId);
                    }
                }, exception -> {
                    if (exception instanceof SQLException) {
                        System.err.println("Error de SQL: " + exception.getMessage());
                    }
                });


        FunkoRepositoryConnecatbleFlux repository = new FunkoRepositoryConnecatbleFlux();

        System.out.println("Sistema de obtención de la lista en Tiempo Real");
        repository.getAllAsFlux().subscribe(
                lista -> {
                    System.out.println("👉 Lista de Funkos actulizada: " + lista);
                },
                error -> System.err.println("Se ha producido un error: " + error),
                () -> System.out.println("Completado")
        );

        System.out.println("Sistema de obtención de notificaciones en Tiempo Real");
        repository.getNotificationAsFlux().subscribe(
                notificacion -> System.out.println("🟢 Notificación: " + notificacion),
                error -> System.err.println("Se ha producido un error: " + error),
                () -> System.out.println("Completado")
        );

        Funko funko1 = Funko.builder()
                .id(1L)
                .name("Funko1")
                .price(10.0)
                .build();

        Funko funko2 = Funko.builder()
                .id(2L)
                .name("Funko2")
                .price(14.0)
                .build();


        Funko funko3 = Funko.builder()
                .id(3L) // Asigna valores a los campos necesarios
                .name("Funko3")
                .price(20.0)
                .build();


        System.out.println("Eliminamos un Funko: " + funko2);
        repository.delete(funko2.getId());
        Thread.sleep(5000);
    }

    //Consutlas al funko:
    CSVReader reader = new CSVReader(); // Debemos implementar CSVReader adecuadamente
    Flux<Funko> funkoFlux = reader.readCSV(); // Asumiendo que readCSV() devuelve un Flux<Funko>

    public void FunkoService(Flux<Funko> funkoFlux) {
        this.funkoFlux = funkoFlux;
    }

private void funkomascaro(){
    funkoFlux
        .collectList()
        .map(funkos -> funkos.stream().max(Comparator.comparing(Funko::getPrice)).orElse(null))
        .subscribe(mostExpensiveFunko -> {
            if (mostExpensiveFunko != null) {
                System.out.println("Funko más caro: " + mostExpensiveFunko);
            } else {
                System.out.println("No hay funkos en la colección.");
            }
        });}

private void mediaFunkos(){
    // Consulta 2: Media de precio de Funkos
    funkoFlux
            .collectList()
            .map(funkos -> funkos.stream().mapToDouble(Funko::getPrice).average().orElse(0.0))
            .subscribe(averagePrice -> {
                System.out.println("Media de precio de Funkos: " + averagePrice);
            });
}

private void funkos2023(){
    // Consulta 3: Funkos que han sido lanzados en 2023
    funkoFlux
            .filter(funko -> funko.getReleaseData() != null && funko.getReleaseData().getYear() == 2023)
            .subscribe(releasedIn2023 -> {
                System.out.println("Funkos lanzados en 2023: " + releasedIn2023);
            });
}

    public void FunkosStitch() {
        // Consulta 4: Número de funkos de Stitch
        funkoFlux
                .filter(funko -> funko.getModel() == Model.STITCH)
                .count()
                .subscribe(count -> {
                    System.out.println("Número de Funkos de Stitch: " + count);
                });
    }


    public void listadoStitchFunko() {
        // Consulta 5: Listado de funkos de Stitch
        funkoFlux
                .filter(funko -> funko.getModel() == Model.STITCH)
                .subscribe(stitchFunko -> {
                    System.out.println("Funko de Stitch: " + stitchFunko);
                });
    }
}

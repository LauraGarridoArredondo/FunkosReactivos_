package develop.controller;

import develop.models.Funko;
import develop.models.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class CSVReader {
    private final Logger logger = LoggerFactory.getLogger(CSVReader.class);

    public Flux<Funko> readCSV() {
        String dataPath = "data" + File.separator + "funkos.csv";
        String appPath = System.getProperty("user.dir");
        Path filePath = Paths.get(appPath + File.separator + dataPath);

        logger.debug("Leyendo el archivo: " + filePath.toString());
        return Flux.create(sink -> {
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

package develop.models;

import develop.locale.MyLocale;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Data
@Builder
/**
 * Clase que representa un modelo de Funko.
 * Un Funko es una figura coleccionable que tiene atributos como un identificador unico,
 * un codigo unico, un nombre, un modelo, un precio, una fecha de creacion, y una fecha de ultima actualizacion.
 *
 * @version 1.0
 */
public class Funko {
    private long id; // Identificador unico del funko, este lo asigna la base de datos
    private UUID COD; // Codigo unico del funko
    private long myId; // Otro identificador que se le asigna con el IdGenerator
    private String name; // Nombre del funko
    private Model model; // Modelo del funko
    private double price; // Precio del funko
    private LocalDate releaseData; // Fecha de creacion del funko
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now(); // Fecha de creacion del funko en la base de datos
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now(); // Fecha de ultima actualizacion del funko

    /**
     * Convierte el objeto Funko a una representacion en forma de cadena.
     *
     * @return Una cadena que representa el objeto Funko.
     */
    @Override
    public String toString() {
        MyLocale myLocal = new MyLocale();
        return "Funko{" +
                "id=" + id +
                ", COD=" + COD +
                ", myId=" + myId +
                ", name='" + name + '\'' +
                ", model=" + model +
                ", price=" + myLocal.toLocalMoney(price) +    // Imprimimos el precio codificado a la moneda Local
                ", releaseData=" + myLocal.toLocalDate(releaseData) + // Imprimimos el ano de lanzamiento codificado a la fecha local
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }

    /**
     * Obtiene un objeto UUID a partir de una representacion de cadena.
     *
     * @param uuid La representacion de cadena del UUID.
     * @return Un objeto UUID.
     */
    public static UUID getUUID(String uuid) {
        return uuid.length() > 36? UUID.fromString(uuid.substring(0,36)): UUID.fromString(uuid);
    }
    /**
     * Obtiene una fecha LocalDate a partir de una representacion de cadena.
     *
     * @param date La representacion de cadena de la fecha en formato "yyyy-MM-dd".
     * @return Un objeto LocalDate.
     */
    public static LocalDate getDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date,formatter);
    }
    /**
     * Crea un objeto Funko a partir de una representacion de cadena de datos.
     *
     * @param linea La representacion de cadena de datos separados por comas.
     * @return Un objeto Funko.
     */
    public static Funko getFunko(String linea){
        String [] campos = linea.split(",");
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
package develop.repositories.funkos;
import develop.exceptions.funkos.FunkoNoAlmacenadoException;
import develop.exceptions.funkos.FunkoNoEncotradoException;
import develop.models.Funko;
import develop.models.IdGenerator;
import develop.models.Model;
import develop.services.database.DatabaseManager;
import develop.services.funkos.FunkosServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public class FunkosRepositoryImpl implements FunkosRepository {
    private final Logger logger = LoggerFactory.getLogger(FunkosRepositoryImpl.class);
    private final DatabaseManager db;
    private final IdGenerator idGenerator;

    public FunkosRepositoryImpl(DatabaseManager db, IdGenerator idGenerator) {
        this.db = db;
        this.idGenerator = idGenerator;
    }

    public static FunkosServiceImpl getInstance(DatabaseManager databaseManager) {
        FunkosRepository funkosRepository = FunkosRepositoryImpl.getInstance(databaseManager);
        return new FunkosServiceImpl(funkosRepository);
    }


    @Override
    public Flux<Funko> findAll() {
        return Flux.create(fluxSink -> {
            String query = "SELECT * FROM funkos";
            try (var connection = db.getConnection();
                 var stmt = connection.prepareStatement(query)
            ) {
                logger.debug("Obteniendo todos los funkos");
                var rs = stmt.executeQuery();
                while (rs.next()) {
                    Funko funko = Funko.builder()
                            .id(rs.getLong("ID"))
                            .COD(rs.getObject("cod", UUID.class))
                            .myId(rs.getLong("MyId"))
                            .name(rs.getString("nombre"))
                            .model(Model.valueOf(rs.getString("modelo")))
                            .price(rs.getDouble("precio"))
                            .releaseData(rs.getObject("fecha_lanzamiento", LocalDate.class))
                            .createdAt(rs.getObject("created_at", LocalDateTime.class))
                            .updatedAt(rs.getObject("updated_at", LocalDateTime.class))
                            .build();
                    fluxSink.next(funko);
                }

                fluxSink.complete();
            } catch (SQLException e) {
                logger.error("Error al buscar todos los funkos", e);
                fluxSink.error(e);
            }
        });
    }

    @Override
    public Flux<Funko> findByNombre(String nombre) {
        return Flux.create(fluxSink -> {
            String query = "SELECT * FROM funkos WHERE nombre LIKE ?";
            try (var connection = db.getConnection();
                 var stmt = connection.prepareStatement(query)
            ) {
                logger.debug("Obteniendo todos los funkos por nombre que contenga: " + nombre);
                stmt.setString(1, "%" + nombre + "%");
                var rs = stmt.executeQuery();
                while (rs.next()) {
                    Funko funko = Funko.builder()
                            .id(rs.getLong("ID"))
                            .COD(rs.getObject("cod", UUID.class))
                            .myId(rs.getLong("MyId"))
                            .name(rs.getString("nombre"))
                            .model(Model.valueOf(rs.getString("modelo")))
                            .price(rs.getDouble("precio"))
                            .releaseData(rs.getObject("fecha_lanzamiento", LocalDate.class))
                            .createdAt(rs.getObject("created_at", LocalDateTime.class))
                            .updatedAt(rs.getObject("updated_at", LocalDateTime.class))
                            .build();
                    fluxSink.next(funko);
                }
                fluxSink.complete();
            } catch (SQLException e) {
                logger.error("Error al buscar funkos por nombre", e);
                fluxSink.error(e);
            }
        });
    }

    @Override
    public Mono<Optional<Funko>> findById(Long id) {
        return Mono.fromCallable(() -> {
            Optional<Funko> funko = Optional.empty();
            String query = "SELECT * FROM funkos WHERE ID =?";
            try (var connection = db.getConnection();
                 var stmt = connection.prepareStatement(query)
            ) {
                stmt.setLong(1, id);
                var rs = stmt.executeQuery();
                while (rs.next()) {
                    funko = Optional.of(Funko.builder()
                            .id(rs.getLong("ID"))
                            .COD(rs.getObject("cod", UUID.class))
                            .myId(rs.getLong("MyId"))
                            .name(rs.getString("nombre"))
                            .model(Model.valueOf(rs.getString("modelo")))
                            .price(rs.getDouble("precio"))
                            .releaseData(rs.getObject("fecha_lanzamiento", LocalDate.class))
                            .createdAt(rs.getObject("created_at", LocalDateTime.class))
                            .updatedAt(rs.getObject("updated_at", LocalDateTime.class))
                            .build()
                    );
                }
            } catch (SQLException e) {
                logger.error("Error al buscar funko por id", e);
                throw new RuntimeException(e);
            }
            return funko;
        });
    }

    @Override
    public Flux<Funko> findAllByNombre(String nombre) {
        return Flux.create(fluxSink -> {
            String query = "SELECT * FROM funkos WHERE nombre = ?";
            try (var connection = db.getConnection();
                 var stmt = connection.prepareStatement(query)
            ) {
                stmt.setString(1, nombre);
                logger.debug("Obteniendo todos los funkos por nombre exacto: " + nombre);
                var rs = stmt.executeQuery();
                while (rs.next()) {
                    Funko funko = Funko.builder()
                            .id(rs.getLong("ID"))
                            .COD(rs.getObject("cod", UUID.class))
                            .myId(rs.getLong("MyId"))
                            .name(rs.getString("nombre"))
                            .model(Model.valueOf(rs.getString("modelo")))
                            .price(rs.getDouble("precio"))
                            .releaseData(rs.getObject("fecha_lanzamiento", LocalDate.class))
                            .createdAt(rs.getObject("created_at", LocalDateTime.class))
                            .updatedAt(rs.getObject("updated_at", LocalDateTime.class))
                            .build();
                    fluxSink.next(funko);
                }
                fluxSink.complete();
            } catch (SQLException e) {
                logger.error("Error al buscar funkos por nombre exacto", e);
                fluxSink.error(e);
            }
        });
    }


    @Override
    public Mono<Funko> findById(long id) {
        String query = "SELECT * FROM funkos WHERE ID = ?";

        return Mono.fromCallable(() -> {
            try (var connection = db.getConnection();
                 var stmt = connection.prepareStatement(query)) {
                stmt.setLong(1, id);
                var rs = stmt.executeQuery();

                if (rs.next()) {
                    return Funko.builder()
                            .id(rs.getLong("ID"))
                            .COD(rs.getObject("cod", UUID.class))
                            .myId(rs.getLong("MyId"))
                            .name(rs.getString("nombre"))
                            .model(Model.valueOf(rs.getString("modelo")))
                            .price(rs.getDouble("precio"))
                            .releaseData(rs.getObject("fecha_lanzamiento", LocalDate.class))
                            .createdAt(rs.getObject("created_at", LocalDateTime.class))
                            .updatedAt(rs.getObject("updated_at", LocalDateTime.class))
                            .build();
                } else {
                    return null;
                }
            } catch (SQLException e) {
                logger.error("Error al buscar funko por id", e);
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public Mono<Funko> save(Funko funko) {
        return Mono.fromCallable(() -> {
            String query = "INSERT INTO funkos (cod, MyId, nombre, modelo, precio, fecha_lanzamiento, created_at, updated_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (var connection = db.getConnection();
                 var stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
            ) {
                logger.debug("Guardando el funko: " + funko);

                funko.setMyId(idGenerator.incrementAndGet());
                funko.setUpdatedAt(LocalDateTime.now());
                stmt.setObject(1, funko.getCOD());
                stmt.setLong(2, funko.getMyId());
                stmt.setString(3, funko.getName());
                stmt.setString(4, funko.getModel().toString());
                stmt.setDouble(5, funko.getPrice());
                stmt.setObject(6, funko.getReleaseData());
                stmt.setObject(7, funko.getCreatedAt());
                stmt.setObject(8, funko.getUpdatedAt());
                var res = stmt.executeUpdate();
                if (res > 0) {
                    ResultSet rs = stmt.getGeneratedKeys();
                    while (rs.next()) {
                        funko.setId(rs.getLong(1));
                    }
                    rs.close();
                } else {
                    logger.error("Funko no guardado con id: " + funko.getId());
                    throw new FunkoNoAlmacenadoException("Funko no guardado con id: " + funko.getId());
                }
            } catch (SQLException | FunkoNoAlmacenadoException e) {
                logger.error("Error al guardar el funko", e);
                throw e;
            }
            return funko;
        });
    }

    @Override
    public Mono<Funko> update(Funko funko) {
        return Mono.fromCallable(() -> {
            String query = "UPDATE funkos SET nombre = ?, modelo = ?, precio = ?, updated_at = ? WHERE ID = ?";
            try (var connection = db.getConnection();
                 var stmt = connection.prepareStatement(query)
            ) {
                logger.debug("Actualizando el funko: " + funko);
                funko.setUpdatedAt(LocalDateTime.now());
                stmt.setString(1, funko.getName());
                stmt.setString(2, funko.getModel().toString());
                stmt.setDouble(3, funko.getPrice());
                stmt.setObject(4, funko.getUpdatedAt());
                stmt.setLong(5, funko.getId());
                var res = stmt.executeUpdate();
                if (res > 0) {
                    logger.debug("Funko actualizado");
                } else {
                    logger.error("Funko no actualizado al no encontrarse en la base de datos con id: " + funko.getId());
                    throw new FunkoNoEncotradoException("Funko no encontrado con id: " + funko.getId());
                }
            } catch (SQLException | FunkoNoEncotradoException e) {
                throw e;
            }
            return funko;
        });
    }

    @Override
    public Mono<Boolean> deleteById(Long aLong) {
        return Mono.fromCallable(() -> {
            String query = "DELETE FROM funkos WHERE ID = ?";
            try (var connection = db.getConnection();
                 var stmt = connection.prepareStatement(query)
            ) {
                logger.debug("Borrando el funko con id: " + aLong);
                stmt.setLong(1, aLong);
                var res = stmt.executeUpdate();
                return res > 0;
            }
        });
    }

    @Override
    public Mono<Void> deleteAll() {
        return Mono.fromRunnable(() -> {
            String query = "DELETE FROM funkos";
            try (var connection = db.getConnection();
                 var stmt = connection.prepareStatement(query)
            ) {
                stmt.executeUpdate();
            } catch (SQLException e) {
                try {
                    throw e;
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

}

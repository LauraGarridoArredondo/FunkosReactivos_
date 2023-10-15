DROP TABLE IF EXISTS funkos;
CREATE TABLE IF NOT EXISTS funkos (
                                      ID BIGINT AUTO_INCREMENT,
                                      cod UUID NOT NULL DEFAULT RANDOM_UUID(),
                                      MyId BIGINT NOT NULL,
                                      nombre VARCHAR(255) NOT NULL,
                                      modelo VARCHAR(50) CHECK (modelo IN ('MARVEL', 'DISNEY', 'ANIME', 'STITCH', 'OTROS')),
                                      precio REAL NOT NULL DEFAULT 0,
                                      fecha_lanzamiento DATE NOT NULL DEFAULT CURRENT_DATE,
                                      created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                      updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

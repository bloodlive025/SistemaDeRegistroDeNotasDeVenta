CREATE TABLE usuarios(
    id BIGINT NOT NULL AUTO_INCREMENT,
    login VARCHAR(100) NOT NULL UNIQUE,
    clave VARCHAR(300) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);
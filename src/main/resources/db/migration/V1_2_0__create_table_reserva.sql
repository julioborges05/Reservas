CREATE TABLE reserva (
    id              SERIAL          PRIMARY KEY,
    nome_usuario    VARCHAR(255)    NOT NULL,
    id_restaurante  INTEGER         NOT NULL,
    hora_chegada    TIMESTAMP,
    CONSTRAINT reserva_restaurante_fk FOREIGN KEY (id_restaurante) REFERENCES restaurante(id)
);

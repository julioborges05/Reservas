CREATE TABLE reserva_vm (
    id              SERIAL          PRIMARY KEY,
    id_reserva      INTEGER         NOT NULL,
    id_restaurante  INTEGER         NOT NULL,
    numero_mesa     INTEGER         NOT NULL,
    status          VARCHAR(255)    NOT NULL,
    CONSTRAINT reserva_vm_reserva_fk FOREIGN KEY (id_reserva) REFERENCES reserva(id)
);

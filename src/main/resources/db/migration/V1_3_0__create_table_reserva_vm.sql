CREATE TABLE reserva_vm (
    id              SERIAL          PRIMARY KEY,
    id_reserva      INTEGER,
    id_restaurante  INTEGER,
    numero_mesa     INTEGER,
    status          VARCHAR(255)    NOT NULL,
    CONSTRAINT reserva_vm_reserva_fk FOREIGN KEY (id_reserva) REFERENCES reserva(id),
    CONSTRAINT reserva_vm_restaurante_fk FOREIGN KEY (id_restaurante) REFERENCES restaurante(id)
);

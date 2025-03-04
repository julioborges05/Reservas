CREATE TABLE mesa (
    id_restaurante      INTEGER         NOT NULL,
    numero_mesa         INTEGER         NOT NULL,
    status_mesa         VARCHAR(255)    NOT NULL,
    id_reserva_vm       INTEGER,
    PRIMARY KEY (id_restaurante, numero_mesa),
    CONSTRAINT mesa_restaurante_fk FOREIGN KEY (id_restaurante) REFERENCES restaurante(id),
    CONSTRAINT mesa_reserva_vm_fk FOREIGN KEY (id_reserva_vm) REFERENCES reserva_vm(id)
);

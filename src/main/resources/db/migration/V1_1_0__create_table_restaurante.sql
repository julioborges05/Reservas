create table restaurante
(
    id              serial,
    nome           varchar(255) not null,
    endereco           varchar(255) not null,
    tipo           varchar(255) not null,
    constraint restaurante_pk primary key (id)
);
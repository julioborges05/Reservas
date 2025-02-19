create table endereco
(
    id          serial,
    cep         varchar(255) not null,
    logradouro  varchar(255) not null,
    bairro      varchar(255) not null,
    cidade      varchar(255) not null,
    numero      varchar(255) not null,
    complemento  varchar(255),
    constraint endereco_pk primary key (id)
);

create table restaurante
(
    id                  serial,
    nome                varchar(255) not null,
    id_endereco         integer not null,
    tipo                varchar(255) not null,
    horario_abertura    time,
    horario_fechamento  time,
    capacidade          integer,
    constraint restaurante_pk primary key (id),
    constraint restaurante_endereco_fk foreign key (id_endereco) references endereco (id)
);
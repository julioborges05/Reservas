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

create table mesa
(
    id serial,
    numero  integer not null,
    status  varchar(255)  not null,
    id_restaurante         integer not null,

    constraint mesa_pk primary key (id),
    constraint mesa_restaurante_fk foreign key (id_restaurante) references restaurante (id)
);

create table reserva
(
    id              serial,
    nome_usuario     varchar(255) not null,
    id_restaurante  integer not null,
    id_mesa         integer not null,

    constraint reserva_pk primary key (id),
    constraint reserva_restaurante_fk foreign key (id_restaurante) references restaurante (id),
    constraint reserva_mesa_fk foreign key (id_mesa) references mesa (id)
)
create table cliente (
    id bigserial not null,
    nome varchar(255) not null,
    telefone varchar(255) not null,
    email varchar(255) not null,
    endereco varchar(255) not null,

    constraint pk_cliente primary key (id)
);


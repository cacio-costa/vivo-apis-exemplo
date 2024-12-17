create table plano (
    id bigserial not null,
    nome varchar(255) not null,
    tipo varchar(30) not null,
    valor decimal(10,2) not null default 0,

    constraint pk_plano primary key (id)
);


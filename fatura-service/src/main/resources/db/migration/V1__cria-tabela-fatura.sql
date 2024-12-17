create table fatura (
    id bigserial not null,
    plano_id bigint not null,
    valor decimal(10,2) not null default 0,
    vencimento date not null,
    status varchar(255) not null,
    cliente_id bigint not null,

    constraint pk_fatura primary key (id)
);


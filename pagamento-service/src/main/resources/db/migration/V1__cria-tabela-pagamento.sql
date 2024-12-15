create table pagamento (
    id bigserial not null,
    valor decimal(10,2) not null default 0,
    data date not null,
    status varchar(255) not null,
    fatura_id bigint not null,

    constraint pk_pagamento primary key (id)
);


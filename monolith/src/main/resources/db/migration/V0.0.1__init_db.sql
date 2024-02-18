create table if not exists user_info -- Пользователь
(
    id            bigserial,                          -- Id для идентификации сущности (PK)
    first_name    varchar(256)     not null,
    second_name   varchar(256)     not null,
    age           integer          not null,
    birth_date    timestamp        not null,
    biography     varchar(256),
    city          varchar(72),
    password      varchar(128),
    primary key (id)
);

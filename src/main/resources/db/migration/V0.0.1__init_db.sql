create table if not exists user_info -- Пользователь
(
    id            bigserial,                          -- Id для идентификации сущности (PK)
    firstName     varchar(256)     not null,
    secondName    varchar(256)     not null,
    age           integer          not null,
    birthDate     timestamp        not null,
    biography     varchar(256),
    city          varchar(72),
    password      varchar(32),
    primary key (id)
);

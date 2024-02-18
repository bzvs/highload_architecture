create table if not exists post -- Пользователь
(
    id              bigserial,                          -- Id для идентификации сущности (PK)
    author_id       bigserial         not null,
    text            varchar(1024)     not null,
    create_date     timestamp            default now(),
    primary key (id),
    constraint fk_author_id foreign key (author_id) references user_info (id)
);



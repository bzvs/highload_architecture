create table if not exists message -- Сообщение
(
    id              bigserial,                          -- Id для идентификации сущности (PK)
    from_id         bigserial          not null,
    to_id           bigserial          not null,
    text            varchar(1024)      not null,
    create_date     timestamp          default now(),
    primary key (id),
    constraint fk_from_id foreign key (from_id) references user_info (id),
    constraint fk_to_id foreign key (to_id) references user_info (id)
);



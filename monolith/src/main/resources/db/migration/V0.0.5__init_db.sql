create table if not exists counter -- Счетчик
(
    id                    bigserial,                          -- Id для идентификации сущности (PK)
    user_id               bigint           not null,
    unread_message_count   bigint           not null,
    update_date           timestamp        not null,

    primary key (id)
);

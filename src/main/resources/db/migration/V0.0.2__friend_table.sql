create table if not exists friend -- Пользователь
(
    id            bigserial,                          -- Id для идентификации сущности (PK)
    user_id       bigserial     not null,
    friend_id     bigserial     not null,
    primary key (id),
    constraint fk_user_id foreign key (user_id) references user_info (id),
    constraint fk_friend_id foreign key (friend_id) references user_info (id)
);

create unique index user_friend_idx on friend (user_id, friend_id);



create table if not exists users
(
    id        bigint generated always as identity
        constraint users_pk
            primary key,
    user_name varchar(36) not null,
    password  varchar     not null,
    role      varchar(5)  not null
);

alter table users
    owner to docker;
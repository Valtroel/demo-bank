create database bank;

create sequence user_id;

alter sequence user_id owner to docker;

create table users
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

create unique index users_user_name_uindex
    on users (user_name);

create table accounts
(
    id      bigint generated always as identity
        constraint accounts_pk
            primary key,
    user_id bigint            not null
        constraint accounts_users_id_fk
            references users,
    status  varchar(8)        not null,
    amount  numeric default 0 not null
);

alter table accounts
    owner to docker;


create table if not exists accounts
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
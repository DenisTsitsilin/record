create table record
(
    record_id serial
        constraint record_pk
            primary key,
    status    varchar not null
);
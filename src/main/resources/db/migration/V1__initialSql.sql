CREATE SCHEMA users;

CREATE SEQUENCE user_sequence
    START 1
    INCREMENT 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

create table users.users
(
    id         BIGINT PRIMARY KEY DEFAULT nextval('user_sequence'),
    first_name varchar(50)  not null,
    last_name  varchar(50)  not null,
    phone      varchar(11)  not null,
    email      varchar(100) not null,
    status     varchar(20)  not null,
    updated_at timestamp without time zone,
    created_at timestamp without time zone

);



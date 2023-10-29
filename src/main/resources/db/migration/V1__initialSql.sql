CREATE SCHEMA user;

create table user.user (
    id BIGINT PRIMARY KEY DEFAULT nextval('user_sequence') primary key ,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    phone varchar(11) not null,
    status varchar(20) not null
);

CREATE SEQUENCE user_sequence
    START 1
    INCREMENT 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

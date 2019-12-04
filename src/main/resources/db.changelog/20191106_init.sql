CREATE SEQUENCE hibernate_sequence;

CREATE TABLE jazapp
(
    id BIGSERIAL NOT NULL,
    firstName varchar NOT NULL,
    lastName varchar NOT NULL,
    username varchar NOT NULL,
    password varchar NOT NULL,
    email varchar NOT NULL,
    birthDate varchar NOT NULL,

    PRIMARY KEY (id)
);
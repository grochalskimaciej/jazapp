-- CREATE SEQUENCE hibernate_sequence;

CREATE TABLE jazapp
(
--     id BIGSERIAL NOT NULL,
    forename VARCHAR(50)   NOT NULL,
    surname VARCHAR(50)   NOT NULL,
    username VARCHAR(30)   NOT NULL,
    pass VARCHAR(30)   NOT NULL,
    email VARCHAR(30)   NOT NULL,
    registerdate VARCHAR(15)   NOT NULL

--     PRIMARY KEY (id)
);
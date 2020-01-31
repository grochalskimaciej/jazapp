CREATE TABLE section
(
    id BIGSERIAL NOT NULL,
    name VARCHAR NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE parameter
(
    id   BIGSERIAL NOT NULL,
    name VARCHAR   NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE category
(
    id BIGSERIAL NOT NULL,
    name varchar NOT NULL,

    section_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (section_id) REFERENCES section (id)
);

CREATE TABLE auction
(
    id BIGSERIAL NOT NULL,
    title VARCHAR NOT NULL,
    description VARCHAR,
    price numeric NOT NULL,
--     isBeingEdited BOOLEAN NOT NULL,

    owner_id BIGINT NOT NULL,
    category_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES jazapp (id),
    FOREIGN KEY (category_id) REFERENCES category (id)
);

CREATE TABLE photo
(
    id BIGSERIAL NOT NULL,
    link VARCHAR NULL,

    auction_id BIGINT NOT NULL,

    PRIMARY KEY (id),
    FOREIGN KEY (auction_id) REFERENCES auction(id)
);

CREATE TABLE auction_parameter
(
    auction_id   BIGSERIAL NOT NULL,
    parameter_id BIGINT NOT NULL,
    value VARCHAR NOT NULL,

    PRIMARY KEY (auction_id),
    FOREIGN KEY (auction_id) REFERENCES auction(id),
    FOREIGN KEY (parameter_id) REFERENCES parameter(id)
);



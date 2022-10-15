CREATE TABLE music_bands
(
    id                     SERIAL PRIMARY KEY,
    name                   VARCHAR(255) NOT NULL,
    coordinates_x          DOUBLE PRECISION,
    coordinates_y          INT          NOT NULL,
    creation_date          DATE         NOT NULL,
    number_of_participants BIGINT,
    genre                  VARCHAR(255) NOT NULL,
    person_name            VARCHAR(255),
    person_height          DOUBLE PRECISION,
    person_weight          BIGINT,
    person_eyecolor        VARCHAR(255),
    person_location_x      FLOAT,
    person_location_y      FLOAT,
    person_location_name   VARCHAR(255),
    userName               VARCHAR(255)
);

CREATE TABLE users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL
);
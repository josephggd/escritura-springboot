CREATE TABLE locations
(
    id        UUID    NOT NULL,
    created   date,
    visible   BOOLEAN NOT NULL,
    lat       FLOAT   NOT NULL,
    lon       FLOAT   NOT NULL,
    blurb     VARCHAR(255),
    signature VARCHAR(255),
    CONSTRAINT pk_locations PRIMARY KEY (id)
);

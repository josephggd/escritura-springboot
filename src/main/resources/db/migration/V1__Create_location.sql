CREATE TABLE locations (
    id uuid,
    created date,
    visible boolean,

    lat float,
    lon float,
    blurb varchar(72),
    signature varchar(72)
)

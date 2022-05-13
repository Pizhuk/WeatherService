CREATE SEQUENCE IF NOT EXISTS cities_id_seq;

CREATE TABLE IF NOT EXISTS cities
(
    id           int8         NOT NULL PRIMARY KEY,
    name        VARCHAR(50)  NOT NULL UNIQUE,
    lat decimal NOT NULL,
    lon decimal NOT NULL
    );

ALTER SEQUENCE cities_id_seq OWNED BY cities.id;

ALTER TABLE cities
    ALTER COLUMN id SET DEFAULT nextval('cities_id_seq');

drop table current_weather;
select id from cities where name = 'Moscow';
select lat, lon from cities where name = 'Moscow';
select * from cities
select * from current_weather

CREATE SEQUENCE IF NOT EXISTS current_weather_id_seq;

CREATE TABLE IF NOT EXISTS current_weather
(
    id           int8    NOT NULL PRIMARY KEY,
    city_id      int8    NOT NULL REFERENCES cities (id),
    json_info    text    NOT NULL,
    dt			 bigint  NOT NULL
    );

ALTER SEQUENCE current_weather_id_seq OWNED BY current_weather.id;

ALTER TABLE current_weather
    ALTER COLUMN id SET DEFAULT nextval('current_weather_id_seq');

CREATE SEQUENCE IF NOT EXISTS hourly_weather_id_seq;

CREATE TABLE IF NOT EXISTS hourly_weather
(
    id           int8    NOT NULL PRIMARY KEY,
    city_id      int8    NOT NULL REFERENCES cities (id),
    json_info    text    NOT NULL,
    dt			 bigint  NOT NULL
    );

ALTER SEQUENCE hourly_weather_id_seq OWNED BY hourly_weather.id;

ALTER TABLE hourly_weather
    ALTER COLUMN id SET DEFAULT nextval('hourly_weather_id_seq');


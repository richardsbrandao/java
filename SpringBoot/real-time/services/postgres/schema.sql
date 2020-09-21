CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

DROP TABLE IF EXISTS league;
DROP TABLE IF EXISTS club;

CREATE TABLE league (id UUID PRIMARY KEY default uuid_generate_v4(), country CHAR(2) NOT NULL, division VARCHAR(255) NOT NULL, season CHAR(9) NOT NULL);
CREATE TABLE club (id UUID PRIMARY KEY default uuid_generate_v4(), country CHAR(2) NOT NULL, name VARCHAR(255) NOT NULL, image VARCHAR(255) NOT NULL);

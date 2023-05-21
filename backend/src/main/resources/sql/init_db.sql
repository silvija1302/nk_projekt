CREATE TABLE IF NOT EXISTS player (
    id int NOT NULL PRIMARY KEY,
    lastName varchar(255),
    firstName varchar(255),
    birthDate date,
    birthCity varchar(255),
    nationalityId int,
    teamId int,
    positionId int
);

CREATE TABLE IF NOT EXISTS position (
    id int NOT NULL PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE IF NOT EXISTS nationality(
    id int NOT NULL PRIMARY KEY,
    country varchar(255)
);

CREATE TABLE IF NOT EXISTS team (
    id int NOT NULL PRIMARY KEY,
    name varchar(255),
    shortName varchar(3),
    stadiumId int,
    coachId int,
    leagueId int
);

CREATE TABLE IF NOT EXISTS stadium (
    id int NOT NULL PRIMARY KEY,
    name varchar(255),
    capacity int,
    location varchar(255)
);

CREATE TABLE IF NOT EXISTS league (
    id int NOT NULL PRIMARY KEY,
    name varchar(255)
);

CREATE TABLE IF NOT EXISTS coach (
    id int NOT NULL PRIMARY KEY,
    lastName varchar(255),
    firstName varchar(255),
    birthDate varchar(255),
    birthCity varchar(255),
    nationalityId int,
    teamId int
    );

CREATE TABLE IF NOT EXISTS training (
    id int NOT NULL PRIMARY KEY,
    teamId int,
    coachId int,
    datetime timestamp
);

CREATE TABLE IF NOT EXISTS 'match' (
    id int NOT NULL PRIMARY KEY,
    homeTeamId int,
    awayTeamId int,
    datetime timestamp
);

ALTER TABLE IF EXISTS player
    ADD CONSTRAINT fk_nationalityId
    FOREIGN KEY (nationalityId) REFERENCES nationality(id);

ALTER TABLE IF EXISTS player
    ADD CONSTRAINT fk_teamId
    FOREIGN KEY (teamId) REFERENCES team(id);

ALTER TABLE IF EXISTS player
    ADD CONSTRAINT fk_positionId
    FOREIGN KEY (positionId) REFERENCES position(id);

ALTER TABLE IF EXISTS team
    ADD CONSTRAINT fk_stadiumId
    FOREIGN KEY (stadiumId) REFERENCES stadium(id);

ALTER TABLE IF EXISTS team
    ADD CONSTRAINT fk_coachId
    FOREIGN KEY (coachId) REFERENCES coach(id);

ALTER TABLE IF EXISTS team
    ADD CONSTRAINT fk_leagueId
    FOREIGN KEY (leagueId) REFERENCES league(id);

ALTER TABLE IF EXISTS coach
    ADD CONSTRAINT fk_teamId
    FOREIGN KEY (team) REFERENCES team(id);

ALTER TABLE IF EXISTS coach
    ADD CONSTRAINT fk_nationalityId
    FOREIGN KEY (nationalityId) REFERENCES nationality(id);

ALTER TABLE IF EXISTS training
    ADD CONSTRAINT fk_teamId
    FOREIGN KEY (teamId) REFERENCES team(id);

ALTER TABLE IF EXISTS training
    ADD CONSTRAINT fk_coachId
    FOREIGN KEY (coachId) REFERENCES coach(id);

ALTER TABLE IF EXISTS 'match'
    ADD CONSTRAINT fk_awayTeamId
    FOREIGN KEY (awayTeamId) REFERENCES team(id);

ALTER TABLE IF EXISTS 'match'
    ADD CONSTRAINT fk_homeTeamId
    FOREIGN KEY (homeTeamId) REFERENCES team(id);

SET MODE PostgreSQL;

create TABLE IF NOT EXISTS heroes(
id int PRIMARY KEY auto_increment,
heroName VARCHAR,
heroPower VARCHAR,
heroWeakness VARCHAR,
age INTEGER,
squadId INTEGER,
);



create TABLE IF NOT EXISTS squads(
id int PRIMARY KEY auto_increment,
squadName VARCHAR,
squadMission VARCHAR,
squadSize INTEGER,
);




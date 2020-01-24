DROP INDEX IF EXISTS username;

DROP TABLE IF EXISTS movies;
DROP TABLE IF EXISTS projections;
DROP TABLE IF EXISTS projection_types;
DROP TABLE IF EXISTS seats;
DROP TABLE IF EXISTS theaters;
DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS users;

CREATE TABLE users (

	id INTEGER PRIMARY KEY,
	username VARCHAR(10) NOT NULL,
	password VARCHAR(10) NOT NULL,
	registrationdate DATETIME NOT NULL,
	role VARCHAR(5) NOT NULL DEFAULT 'USER'
	
);

INSERT INTO users (username, password, registrationdate, role) VALUES ('a', 'a', '2017-11-21 19:23', 'ADMIN');
INSERT INTO users (username, password, registrationdate, role) VALUES ('b', 'b', '2018-02-09 12:46', 'USER');

CREATE TABLE movies (

	id INTEGER PRIMARY KEY,
	title VARCHAR(50) NOT NULL,
	duration VARCHAR(10) NOT NULL,
	distributor VARCHAR(50) NOT NULL,
	origincountry VARCHAR(50) NOT NULL,
	yearofproduction INT NOT NULL


);

INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction) VALUES ('The Irishman', '03:37', 'Netflix', 'United States', '2019');
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction) VALUES ('Joker', '02:02', 'Warner Bros. Pictures', 'United States', '2019');
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction) VALUES ('The Lion King', '01:58', 'Walt Disney Studios', 'United States', '2019');
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction) VALUES ('Bad Boys for Life', '02:04', 'Columbia Pictures', 'United States', '2020');
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction) VALUES ('Parasite', '02:12', 'Barunson Entertainment', 'South Korea', '2019');
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction) VALUES ('Birds of Prey', '01:49', 'DC Entertainment', 'United States', '2020');
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction) VALUES ('Underwater', '01:35', '20th Century Fox', 'United States', '2020');
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction) VALUES ('Jumanji', '02:03', 'Hartbeat Productioins', 'United States', '2019');
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction) VALUES ('A fall from Grace', '01:55', 'Tyler Perry Studios', 'United States', '2020');

CREATE TABLE projection_types (

	id INTEGER PRIMARY KEY,
	name VARCHAR(3) NOT NULL
	
);

INSERT INTO projection_types (name) VALUES ('2D');
INSERT INTO projection_types (name) VALUES ('3D');
INSERT INTO projection_types (name) VALUES ('4D');

CREATE TABLE theaters (

	id INTEGER PRIMARY KEY,
	name VARCHAR(5) NOT NULL,
	projectiontype INT NOT NULL,
	FOREIGN KEY (projectiontype) references projection_types(id) ON DELETE RESTRICT

);

INSERT INTO theaters (name, projectiontype) VALUES ('A', 1);
INSERT INTO theaters (name, projectiontype) VALUES ('B', 2);
INSERT INTO theaters (name, projectiontype) VALUES ('C', 3);

CREATE TABLE seats (

	number INTEGER UNIQUE,
	theater INT NOT NULL,
	FOREIGN KEY (theater) references theaters(id) ON DELETE RESTRICT

);

INSERT INTO seats (number, theater) VALUES (1, 1);
INSERT INTO seats (number, theater) VALUES (2, 1);
INSERT INTO seats (number, theater) VALUES (3, 1);
INSERT INTO seats (number, theater) VALUES (4, 1);
INSERT INTO seats (number, theater) VALUES (5, 1);

CREATE TABLE projections (

	id INTEGER PRIMARY KEY,
	movie INT NOT NULL,
	projectiontype INT NOT NULL,
	theater INT NOT NULL,
	dateandtime DATETIME NOT NULL,
	ticketprice DECIMAL(10, 2) NOT NULL DEFAULT 999.00,
	admincreator INT NOT NULL,
	FOREIGN KEY (movie) references movies(id) ON DELETE RESTRICT,
	FOREIGN KEY (projectiontype) references projection_types(id) ON DELETE RESTRICT,
	FOREIGN KEY (theater) references theaters(id) ON DELETE RESTRICT,
	FOREIGN KEY (admincreator) references users(id) ON DELETE RESTRICT
	

);

INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (1, 2, 1, '2020-03-01 12:46', 400, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (2, 3, 3, '2020-03-01 13:46', 500, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (3, 1, 2, '2020-03-01 15:46', 600, 1);


DROP INDEX IF EXISTS username;

DROP TABLE IF EXISTS movies;
DROP TABLE IF EXISTS projections;
DROP TABLE IF EXISTS projection_types;
DROP TABLE IF EXISTS seats;
DROP TABLE IF EXISTS theaters;
DROP TABLE IF EXISTS tickets;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS seat_availability;

CREATE TABLE users (

	id INTEGER PRIMARY KEY,
	username VARCHAR(10) NOT NULL,
	password VARCHAR(10) NOT NULL,
	registrationdate DATETIME NOT NULL,
	role VARCHAR(5) NOT NULL DEFAULT 'USER'
	
);

INSERT INTO users (username, password, registrationdate, role) VALUES ('a', 'a', '2017-11-21 19:23', 'ADMIN');
INSERT INTO users (username, password, registrationdate, role) VALUES ('b', 'b', '2018-02-09 12:46', 'USER');
INSERT INTO users (username, password, registrationdate, role) VALUES ('c', 'c', '2019-02-02 12:46', 'USER');

CREATE TABLE movies (

	id INTEGER PRIMARY KEY,
	title VARCHAR(50) NOT NULL,
	duration VARCHAR(10) NOT NULL,
	distributor VARCHAR(50) NOT NULL,
	origincountry VARCHAR(50) NOT NULL,
	yearofproduction INT NOT NULL


);

INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction) VALUES ('The Irishman', '217', 'Netflix', 'United States', '2019');
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction) VALUES ('Joker', '122', 'Warner Bros. Pictures', 'United States', '2019');
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction) VALUES ('The Lion King', '118', 'Walt Disney Studios', 'United States', '2019');
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction) VALUES ('Bad Boys for Life', '124', 'Columbia Pictures', 'United States', '2020');
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction) VALUES ('Parasite', '132', 'Barunson Entertainment', 'South Korea', '2019');
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction) VALUES ('Birds of Prey', '109', 'DC Entertainment', 'United States', '2020');
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction) VALUES ('Underwater', '95', '20th Century Fox', 'United States', '2020');
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction) VALUES ('Jumanji', '123', 'Hartbeat Productioins', 'United States', '2019');
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction) VALUES ('A fall from Grace', '115', 'Tyler Perry Studios', 'United States', '2020');
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction) VALUES ('Aquaman', '142', 'DC Entertainment', 'China', '2018');

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

	id INTEGER PRIMARY KEY,
	number INTEGER UNIQUE,
	theater INT NOT NULL,
	FOREIGN KEY (theater) references theaters(id) ON DELETE RESTRICT

);

INSERT INTO seats (number, theater) VALUES (1, 1);
INSERT INTO seats (number, theater) VALUES (2, 1);
INSERT INTO seats (number, theater) VALUES (3, 1);
INSERT INTO seats (number, theater) VALUES (4, 1);
INSERT INTO seats (number, theater) VALUES (5, 1);
INSERT INTO seats (number, theater) VALUES (6, 2);
INSERT INTO seats (number, theater) VALUES (7, 2);
INSERT INTO seats (number, theater) VALUES (8, 2);
INSERT INTO seats (number, theater) VALUES (9, 2);
INSERT INTO seats (number, theater) VALUES (10, 2);
INSERT INTO seats (number, theater) VALUES (11, 3);
INSERT INTO seats (number, theater) VALUES (12, 3);
INSERT INTO seats (number, theater) VALUES (13, 3);
INSERT INTO seats (number, theater) VALUES (14, 3);
INSERT INTO seats (number, theater) VALUES (15, 3);

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

INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (1, 3, 3, '2020-02-09 19:00', 800, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (1, 2, 2, '2020-02-10 19:00', 550, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (1, 1, 1, '2020-02-11 19:00', 250, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (1, 3, 3, '2020-02-12 19:00', 800, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (1, 2, 2, '2020-02-13 19:00', 550, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (2, 3, 3, '2020-02-09 15:00', 800, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (2, 2, 2, '2020-02-10 15:00', 550, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (2, 1, 1, '2020-02-11 15:00', 250, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (2, 3, 3, '2020-02-12 15:00', 800, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (2, 2, 2, '2020-02-13 15:00', 550, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (3, 3, 3, '2020-02-09 17:00', 800, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (3, 2, 2, '2020-02-10 17:00', 550, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (3, 1, 1, '2020-02-11 17:00', 250, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (3, 3, 3, '2020-02-12 17:00', 800, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (3, 2, 2, '2020-02-13 17:00', 550, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (4, 3, 3, '2020-02-09 23:00', 800, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (4, 2, 2, '2020-02-10 23:00', 550, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (4, 1, 1, '2020-02-11 23:00', 250, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (4, 3, 3, '2020-02-12 23:00', 800, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (4, 2, 2, '2020-02-13 23:00', 550, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (5, 2, 2, '2020-02-09 15:00', 550, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (5, 1, 1, '2020-02-10 15:00', 250, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (5, 3, 3, '2020-02-11 15:00', 800, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (5, 2, 2, '2020-02-12 15:00', 550, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (5, 3, 3, '2020-02-13 15:00', 800, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (6, 2, 2, '2020-02-09 17:30', 550, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (6, 1, 1, '2020-02-10 17:30', 250, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (6, 3, 3, '2020-02-11 17:30', 800, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (6, 2, 2, '2020-02-12 17:30', 550, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (6, 3, 3, '2020-02-13 17:30', 800, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (7, 2, 2, '2020-02-09 19:30', 550, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (7, 1, 1, '2020-02-10 19:30', 250, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (7, 3, 3, '2020-02-11 19:30', 800, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (7, 2, 2, '2020-02-12 19:30', 550, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (7, 3, 3, '2020-02-13 19:30', 800, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (8, 2, 2, '2020-02-09 21:30', 550, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (8, 1, 1, '2020-02-10 21:30', 250, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (8, 3, 3, '2020-02-11 21:30', 800, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (8, 2, 2, '2020-02-12 21:30', 550, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (8, 3, 3, '2020-02-13 21:30', 800, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (9, 1, 1, '2020-02-09 16:00', 250, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (9, 3, 3, '2020-02-10 16:00', 800, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (9, 2, 2, '2020-02-11 16:00', 550, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (9, 1, 1, '2020-02-12 16:00', 250, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (9, 1, 1, '2020-02-13 16:00', 250, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (10, 1, 1, '2020-02-09 18:00', 250, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (10, 3, 3, '2020-02-10 18:00', 800, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (10, 2, 2, '2020-02-11 18:00', 550, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (10, 1, 1, '2020-02-12 18:00', 250, 1);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator) VALUES (10, 1, 1, '2020-02-13 18:00', 250, 1);



CREATE TABLE tickets (

	id INTEGER PRIMARY KEY,
	projection INT NOT NULL,
	seat INT NOT NULL,
	dateandtime DATETIME NOT NULL,
	price DECIMAL(10, 2) NOT NULL DEFAULT 999.00,
	buyer INT NOT NULL,
	FOREIGN KEY (projection) references projections(id) ON DELETE RESTRICT,
	FOREIGN KEY (seat) references seats(id) ON DELETE RESTRICT,
	FOREIGN KEY (buyer) references users(id) ON DELETE RESTRICT
	
);

CREATE TABLE seat_availability (

	projection_id INT NOT NULL,
	seat_id INT NOT NULL,
	taken INT NOT NULL,
	FOREIGN KEY (projection_id) references projections(id) ON DELETE RESTRICT,
	FOREIGN KEY (seat_id) references seats(id) ON DELETE RESTRICT
);

INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (1, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (1, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (1, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (1, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (1, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (2, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (2, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (2, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (2, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (2, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (3, 1, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (3, 2, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (3, 3, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (3, 4, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (3, 5, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (4, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (4, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (4, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (4, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (4, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (5, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (5, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (5, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (5, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (5, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (6, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (6, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (6, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (6, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (6, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (7, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (7, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (7, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (7, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (7, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (8, 1, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (8, 2, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (8, 3, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (8, 4, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (8, 5, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (9, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (9, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (9, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (9, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (9, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (10, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (10, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (10, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (10, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (10, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (11, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (11, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (11, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (11, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (11, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (12, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (12, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (12, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (12, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (12, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (13, 1, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (13, 2, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (13, 3, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (13, 4, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (13, 5, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (14, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (14, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (14, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (14, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (14, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (15, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (15, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (15, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (15, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (15, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (16, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (16, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (16, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (16, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (16, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (17, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (17, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (17, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (17, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (17, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (18, 1, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (18, 2, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (18, 3, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (18, 4, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (18, 5, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (19, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (19, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (19, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (19, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (19, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (20, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (20, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (20, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (20, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (20, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (21, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (21, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (21, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (21, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (21, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (22, 1, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (22, 2, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (22, 3, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (22, 4, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (22, 5, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (23, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (23, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (23, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (23, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (23, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (24, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (24, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (24, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (24, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (24, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (25, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (25, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (25, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (25, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (25, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (26, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (26, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (26, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (26, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (26, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (27, 1, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (27, 2, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (27, 3, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (27, 4, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (27, 5, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (28, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (28, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (28, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (28, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (28, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (29, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (29, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (29, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (29, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (29, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (30, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (30, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (30, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (30, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (30, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (31, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (31, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (31, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (31, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (31, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (32, 1, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (32, 2, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (32, 3, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (32, 4, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (32, 5, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (33, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (33, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (33, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (33, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (33, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (34, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (34, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (34, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (34, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (34, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (35, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (35, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (35, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (35, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (35, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (36, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (36, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (36, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (36, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (36, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (37, 1, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (37, 2, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (37, 3, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (37, 4, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (37, 5, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (38, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (38, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (38, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (38, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (38, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (39, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (39, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (39, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (39, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (39, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (40, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (40, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (40, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (40, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (40, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (41, 1, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (41, 2, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (41, 3, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (41, 4, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (41, 5, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (42, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (42, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (42, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (42, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (42, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (43, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (43, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (43, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (43, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (43, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (44, 1, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (44, 2, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (44, 3, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (44, 4, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (44, 5, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (45, 1, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (45, 2, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (45, 3, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (45, 4, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (45, 5, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (46, 1, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (46, 2, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (46, 3, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (46, 4, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (46, 5, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (47, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (47, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (47, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (47, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (47, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (48, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (48, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (48, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (48, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (48, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (49, 1, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (49, 2, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (49, 3, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (49, 4, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (49, 5, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (50, 1, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (50, 2, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (50, 3, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (50, 4, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (50, 5, 0);

















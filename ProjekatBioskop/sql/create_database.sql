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
	role VARCHAR(5) NOT NULL DEFAULT 'USER',
	deleted INT
	
);

INSERT INTO users (username, password, registrationdate, role) VALUES ('a', 'a', '2017-11-21 19:23', 'ADMIN', 0);
INSERT INTO users (username, password, registrationdate, role) VALUES ('b', 'b', '2018-02-09 12:46', 'USER', 0);
INSERT INTO users (username, password, registrationdate, role) VALUES ('c', 'c', '2019-02-02 12:46', 'USER', 0);

CREATE TABLE movies (

	id INTEGER PRIMARY KEY,
	title VARCHAR(50) NOT NULL,
	duration VARCHAR(10) NOT NULL,
	distributor VARCHAR(50) NOT NULL,
	origincountry VARCHAR(50) NOT NULL,
	yearofproduction INT NOT NULL,
	deleted INT


);

INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction, deleted) VALUES ('The Irishman', '217', 'Netflix', 'United States', '2019', 0);
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction, deleted) VALUES ('Joker', '122', 'Warner Bros. Pictures', 'United States', '2019', 0);
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction, deleted) VALUES ('The Lion King', '118', 'Walt Disney Studios', 'United States', '2019', 0);
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction, deleted) VALUES ('Bad Boys for Life', '124', 'Columbia Pictures', 'United States', '2020', 0);
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction, deleted) VALUES ('Parasite', '132', 'Barunson Entertainment', 'South Korea', '2019', 0);
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction, deleted) VALUES ('Birds of Prey', '109', 'DC Entertainment', 'United States', '2020', 0);
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction, deleted) VALUES ('Underwater', '95', '20th Century Fox', 'United States', '2020', 0);
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction, deleted) VALUES ('Jumanji', '123', 'Hartbeat Productioins', 'United States', '2019', 0);
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction, deleted) VALUES ('A fall from Grace', '115', 'Tyler Perry Studios', 'United States', '2020', 0);
INSERT INTO movies (title, duration, distributor, origincountry, yearofproduction, deleted) VALUES ('Aquaman', '142', 'DC Entertainment', 'China', '2018', 0);

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
	deleted INT,
	FOREIGN KEY (movie) references movies(id) ON DELETE RESTRICT,
	FOREIGN KEY (projectiontype) references projection_types(id) ON DELETE RESTRICT,
	FOREIGN KEY (theater) references theaters(id) ON DELETE RESTRICT,
	FOREIGN KEY (admincreator) references users(id) ON DELETE RESTRICT
	

);

INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (1, 3, 3, '2020-02-09 19:00', 800, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (1, 1, 1, '2020-02-11 19:00', 250, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (1, 2, 2, '2020-02-13 19:00', 550, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (2, 2, 2, '2020-02-10 15:00', 550, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (2, 3, 3, '2020-02-12 15:00', 800, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (3, 3, 3, '2020-02-09 17:00', 800, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (3, 1, 1, '2020-02-11 17:00', 250, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (3, 2, 2, '2020-02-13 17:00', 550, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (4, 2, 2, '2020-02-10 23:00', 550, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (4, 3, 3, '2020-02-12 23:00', 800, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (5, 2, 2, '2020-02-09 15:00', 550, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (5, 3, 3, '2020-02-11 15:00', 800, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (5, 3, 3, '2020-02-13 15:00', 800, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (6, 1, 1, '2020-02-10 17:30', 250, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (6, 2, 2, '2020-02-12 17:30', 550, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (7, 2, 2, '2020-02-09 19:30', 550, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (7, 3, 3, '2020-02-11 19:30', 800, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (7, 3, 3, '2020-02-13 19:30', 800, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (8, 1, 1, '2020-02-10 21:30', 250, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (8, 2, 2, '2020-02-12 21:30', 550, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (9, 1, 1, '2020-02-09 16:00', 250, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (9, 2, 2, '2020-02-11 16:00', 550, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (9, 1, 1, '2020-02-13 16:00', 250, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (10, 3, 3, '2020-02-10 18:00', 800, 1, 0);
INSERT INTO projections (movie, projectiontype, theater, dateandtime, ticketprice, admincreator, deleted) VALUES (10, 1, 1, '2020-02-12 18:00', 250, 1, 0);



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

INSERT INTO tickets (projection, seat, dateandtime, price, buyer) VALUES (1, 1, '2020-02-05 18:00', 250, 1, 0);

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
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (2, 1, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (2, 2, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (2, 3, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (2, 4, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (2, 5, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (3, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (3, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (3, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (3, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (3, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (4, 5, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (4, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (4, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (4, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (4, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (5, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (5, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (5, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (5, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (5, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (6, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (6, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (6, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (6, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (6, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (7, 1, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (7, 2, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (7, 3, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (7, 4, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (7, 5, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (8, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (8, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (8, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (8, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (8, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (9, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (9, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (9, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (9, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (9, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (10, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (10, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (10, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (10, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (10, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (11, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (11, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (11, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (11, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (11, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (12, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (12, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (12, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (12, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (12, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (13, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (13, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (13, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (13, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (13, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (14, 1, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (14, 2, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (14, 3, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (14, 4, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (14, 5, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (15, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (15, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (15, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (15, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (15, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (16, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (16, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (16, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (16, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (16, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (17, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (17, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (17, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (17, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (17, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (18, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (18, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (18, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (18, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (18, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (19, 1, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (19, 2, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (19, 3, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (19, 4, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (19, 5, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (20, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (20, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (20, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (20, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (20, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (21, 1, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (21, 2, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (21, 3, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (21, 4, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (21, 5, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (22, 6, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (22, 7, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (22, 8, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (22, 9, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (22, 10, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (23, 1, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (23, 2, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (23, 3, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (23, 4, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (23, 5, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (24, 11, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (24, 12, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (24, 13, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (24, 14, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (24, 15, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (25, 1, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (25, 2, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (25, 3, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (25, 4, 0);
INSERT INTO seat_availability (projection_id, seat_id, taken) VALUES (25, 5, 0);

















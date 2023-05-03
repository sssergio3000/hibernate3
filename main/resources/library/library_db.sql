DROP DATABASE IF EXISTS library;

CREATE DATABASE library;

USE library;


CREATE TABLE author
(
	id bigint NOT NULL AUTO_INCREMENT,
	name varchar(255) DEFAULT NULL,
	last_name varchar(255) DEFAULT NULL,
	PRIMARY KEY ( id )
);

INSERT INTO author(name, last_name)
VALUES
('Franz', 'Kafka'),
('Charles', 'Dickens'),
('Herman', 'Melville'),
('Clive', 'Lewis');

-- ************************************************

select * from author;


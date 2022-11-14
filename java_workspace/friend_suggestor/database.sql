CREATE TABLE friendsdb;

CREATE TABLE user_details(
	id INTEGER PRIMARY KEY,
	username varchar(50) NOT NULL,
	first_name varchar(50),
	last_name varchar(50),
	email varchar(50),
	passwrod varchar(50) NOT NULL
);

CREATE TABLE user_friends(
	id INTEGER PRIMARY KEY,
	user_id INTEGER NOT NULL,
	friend_id INTEGER NOT NULL,
	is_accepted varchar(1) DEFAULT 'N'
);
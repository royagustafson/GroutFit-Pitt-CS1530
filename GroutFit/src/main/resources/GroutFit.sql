DROP TABLE IF EXISTS profile CASCADE;
DROP TABLE IF EXISTS wishlist CASCADE;
DROP TABLE IF EXISTS clothing_item CASCADE;
DROP TABLE IF EXISTS item CASCADE;
DROP TABLE IF EXISTS outfit CASCADE;

--TODO:
------- put this in main/resources on maven
------- make size and clothing type domains
------- images? on the server and store index? seperate db?
------- write check constraints
------- triggers

CREATE TABLE profile(
	profile_id INT PRIMARY KEY,
	email        TEXT NOT NULL,
	password     TEXT NOT NULL,
	size_shirt   TEXT,
	size_pants   TEXT,
	size_shoe    TEXT
);

CREATE TABLE clothing_type(
	type_id        INT  PRIMARY KEY,
	article        TEXT NOT NULL, --should be shirt/pants/shoes etc
	price NUMERIC(6, 2) NOT NULL,
	brand          TEXT NOT NULL,
	description    TEXT NOT NULL
);

CREATE TABLE clothing_item(
	item_id  INT  PRIMARY KEY,
	type_id  INT  REFERENCES clothing_type(type_id),
	color    TEXT NOT NULL,
	size     TEXT NOT NULL,
	quantity INT  NOT NULL,
	gender   TEXT
);

CREATE TABLE wishlist(
	profile_id INT REFERENCES profile(profile_id),
	item_id    INT REFERENCES clothing_item(item_id),
	PRIMARY KEY(profile_id, item_id)
);

CREATE TABLE outfit(
	outfit_id INT PRIMARY KEY,
	creator INT REFERENCES profile(profile_id),
	full_body BOOLEAN,
	top INT REFERENCES clothing_item(item_id),
	bottom INT REFERENCES clothing_item(item_id),
	jacket INT REFERENCES clothing_item(item_id),
	shoes INT REFERENCES clothing_item(item_id),
	acc1 INT REFERENCES clothing_item(item_id),
	acc2 INT REFERENCES clothing_item(item_id),
	acc3 INT REFERENCES clothing_item(item_id)
);

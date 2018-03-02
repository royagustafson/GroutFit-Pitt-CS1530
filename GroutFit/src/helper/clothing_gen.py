"""
CREATE TABLE item(
	item_id        INT  PRIMARY KEY,
	type          TEXT  NOT NULL,
	price NUMERIC(6, 2) NOT NULL,
	manufactioner TEXT  NOT NULL,
	description   TEXT  NOT NULL
);

CREATE TABLE inventory(
	inventory_id INT PRIMARY KEY,
	item_id INT REFERENCES item(item_id),
	color TEXT NOT NULL,
	size  TEXT NOT NULL,
	quantity INT NOT NULL,
	gender TEXT
);
"""
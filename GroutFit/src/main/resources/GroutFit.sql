DROP TABLE IF EXISTS profile CASCADE;
DROP TABLE IF EXISTS wishlist CASCADE;
DROP TABLE IF EXISTS inventory CASCADE;
DROP TABLE IF EXISTS item CASCADE;
DROP TABLE IF EXISTS outfit CASCADE;

--TODO:
------- put this in main/resources on maven
------- make size and clothing type domains
------- images? on the server with index? seperate db?
------- write check constraints
------- triggers

CREATE TABLE profile (
  profile_id INT PRIMARY KEY,
  email      TEXT   NOT NULL,
  password   BIGINT NOT NULL,
  size_shirt TEXT,
  size_pants TEXT,
  size_shoe  TEXT
);

CREATE TABLE item (
  item_id       INT PRIMARY KEY,
  type          TEXT          NOT NULL,
  price         NUMERIC(6, 2) NOT NULL,
  manufactioner TEXT          NOT NULL,
  description   TEXT          NOT NULL
);

CREATE TABLE inventory (
  inventory_id INT PRIMARY KEY,
  item_id      INT REFERENCES item (item_id),
  color        TEXT NOT NULL,
  size         TEXT NOT NULL,
  quantity     INT  NOT NULL,
  gender       TEXT
);

CREATE TABLE wishlist (
  profile_id INT REFERENCES profile (profile_id),
  item_id    INT REFERENCES inventory (inventory_id),
  PRIMARY KEY (profile_id, item_id)
);

CREATE TABLE outfit (
  outfit_id INT PRIMARY KEY,
  creator   INT REFERENCES profile (profile_id),
  full_body BOOLEAN,
  top       INT REFERENCES inventory (inventory_id),
  bottom    INT REFERENCES inventory (inventory_id),
  jacket    INT REFERENCES inventory (inventory_id),
  shoes     INT REFERENCES inventory (inventory_id),
  acc1      INT REFERENCES inventory (inventory_id),
  acc2      INT REFERENCES inventory (inventory_id),
  acc3      INT REFERENCES inventory (inventory_id)
);

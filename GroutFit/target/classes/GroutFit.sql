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

CREATE TABLE profile (
  email      VARCHAR(40) PRIMARY KEY,
  password   VARCHAR(60) NOT NULL,
  size_shirt CHAR(2),
  size_pants CHAR(2),
  size_dress CHAR(2),
  gender     CHAR(1)
);

CREATE TABLE clothing_type (
  type_id     INT PRIMARY KEY,
  name        VARCHAR(20)   NOT NULL,
  category    VARCHAR(10)   NOT NULL, --should be shirt/pants/sweatshirt etc
  price       NUMERIC(6, 2) NOT NULL,
  description VARCHAR(255)  NOT NULL
);

CREATE TABLE clothing_item (
  item_id  INT PRIMARY KEY,
  type_id  INT REFERENCES clothing_type (type_id),
  color    VARCHAR(6) NOT NULL,
  size     VARCHAR(2) NOT NULL,
  quantity INT        NOT NULL,
  gender   BOOLEAN -- male = true, female = false, unisex = null
);

CREATE TABLE wishlist (
  profile_id INT REFERENCES profile (profile_id),
  item_id    INT REFERENCES clothing_item (item_id),
  PRIMARY KEY (profile_id, item_id)
);

CREATE TABLE outfit (
  outfit_id INT PRIMARY KEY,
  creator   INT REFERENCES profile (profile_id),
  full_body BOOLEAN,
  top       INT REFERENCES clothing_item (item_id),
  bottom    INT REFERENCES clothing_item (item_id),
  jacket    INT REFERENCES clothing_item (item_id)
);
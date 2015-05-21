CREATE TABLE IF NOT EXISTS tasks (
  id int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  info text NOT NULL,
  `comment` text,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users (
  id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  role int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (id)
);

INSERT INTO users (username, password) VALUES ('user', 'W6ph5Mm5Pz8GgiULbPgzG37mj9g=');

INSERT INTO tasks (info) VALUES ('Tache 1');
INSERT INTO tasks (info, comment) VALUES ('Tache 2', 'Termine');

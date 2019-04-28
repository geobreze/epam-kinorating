CREATE TABLE user
(
  id       INTEGER     NOT NULL AUTO_INCREMENT PRIMARY KEY,
  login    VARCHAR(20) NOT NULL UNIQUE,
  password CHAR(64)    NOT NULL,
  role     ENUM ('ADMIN', 'USER') DEFAULT 'USER',
  ban      TINYINT                DEFAULT 0
);

CREATE TABLE film
(
  id          INTEGER      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  title       VARCHAR(255) NOT NULL UNIQUE,
  description TEXT         NOT NULL,
  last_update DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE mark
(
  id      INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id INTEGER NOT NULL,
  film_id INTEGER NOT NULL,
  value   INTEGER NOT NULL,
  CONSTRAINT mark_foreign_key_film_id FOREIGN KEY (film_id) REFERENCES film (id) ON DELETE CASCADE,
  CONSTRAINT mark_foreign_key_user_id FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE,
  CONSTRAINT unique_mark UNIQUE (user_id, film_id)
);

CREATE TABLE comment
(
  id          INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
  user_id     INTEGER NOT NULL,
  film_id     INTEGER NOT NULL,
  text        TEXT    NOT NULL,
  CONSTRAINT comment_foreign_key_film_id FOREIGN KEY (film_id) REFERENCES film (id) ON DELETE CASCADE,
  CONSTRAINT comment_foreign_key_user_id FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE,
  last_update DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
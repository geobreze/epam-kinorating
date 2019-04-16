INSERT INTO user (login, password, role)
VALUES
  ('admin', MD5('admin'), 'ADMIN'),
  ('user', MD5('user'), 'USER');
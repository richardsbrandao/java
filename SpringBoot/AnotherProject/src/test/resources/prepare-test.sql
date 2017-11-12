delete from users;
delete from categories;

INSERT INTO categories (id, name) VALUES (DEFAULT, 'DEFENDER');

INSERT INTO users (id, name, born_date, category_id) VALUES (DEFAULT, 'Nesta', '1976-03-19', (SELECT id FROM categories where name = 'DEFENDER'));

INSERT INTO reports (id, requester, content) VALUES (DEFAULT, 'Richard', '');
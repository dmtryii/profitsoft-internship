DELETE FROM film;
DELETE FROM director;
ALTER TABLE film AUTO_INCREMENT = 1;
ALTER TABLE director AUTO_INCREMENT = 1;

INSERT INTO director (first_name, last_name) VALUES
    ('Christopher', 'Nolan'),
    ('Quentin', 'Tarantino');

INSERT INTO film (title, rating, release_date, director_id) VALUES
    ('Interstellar', '0.92', '2014-11-06', 1),
    ('Tenet', '0.78', '2020-08-12', 1),
    ('The Dark Knight', '0.93', '2008-08-14', 1),

    ('Pulp Fiction', '0.92', '1994-10-14', 2),
    ('Inglourious Basterds', '0.89', '2009-08-20', 2),
    ('The Hateful Eight', '0.86', '2016-01-14', 2),
    ('Django Unchained', '0.92', '2013-01-17', 2),
    ('Test', '0.99', '2023-01-17', 2);
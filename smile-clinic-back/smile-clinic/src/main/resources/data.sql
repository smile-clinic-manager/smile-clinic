-- Insert sample data into the 'students' table with snake_case column names

INSERT INTO students (id, first_name, last_name, address)
VALUES (1, 'Juan', 'Lopez', 'Calle Sevilla, 12 portal 3 1A');

INSERT INTO students (id, first_name, last_name, address)
VALUES (2, 'Maria', 'Garcia', 'Calle Mayor, 22 portal 1 2B');

INSERT INTO students (id, first_name, last_name, address)
VALUES (3, 'Pedro', 'Martinez', 'Avenida de la Paz, 45 3C');

INSERT INTO students (id, first_name, last_name, address)
VALUES (4, 'Laura', 'Hernandez', 'Plaza del Sol, 9 bajo A');

INSERT INTO students (id, first_name, last_name, address)
VALUES (5, 'Sofia', 'Fernandez', 'Calle Gran Via, 10 2D');

INSERT INTO students (id, first_name, last_name, address)
VALUES (6, 'Carlos', 'Ruiz', 'Avenida de las Americas, 33 portal 2 5B');

ALTER SEQUENCE students_seq RESTART WITH 7;  -- Esto es para que la secuencia empiece en 7 y asi las nuevas
                                             -- entidades se crean sin pisar los ids de las de la carga inicial.


-- Insert sample data into the 'students' table with snake_case column names

INSERT INTO users (id, first_name, last_name1, last_name2, username, dni, email, password, role)
VALUES
    (1, 'Alice', 'Smith', 'Johnson', 'alice123', '12345678A', 'alice.smith@example.com', 'password123', 'CLINIC_ADMIN'),
    (2, 'Bob', 'Johnson', NULL, 'bobbyJ', '23456789B', 'bob.johnson@example.com', 'password456', 'CLINIC_DENTIST'),
    (3, 'Charlie', 'Brown', 'Taylor', 'charlieB', '34567890C', 'charlie.brown@example.com', 'password789', 'CLINIC_ADMIN'),
    (4, 'Diana', 'Green', 'Miller', 'dianaGM', '45678901D', 'diana.green@example.com', 'securePass1', 'CLINIC_DENTIST'),
    (5, 'Eve', 'White', NULL, 'eveWhite', '56789012E', 'eve.white@example.com', 'password987', 'CLINIC_ADMIN'),
    (6, 'Frank', 'Black', 'Scott', 'frankScott', '67890123F', 'frank.black@example.com', 'password654', 'CLINIC_DENTIST');

ALTER SEQUENCE user_seq RESTART WITH 7;  -- Esto es para que la secuencia empiece en 7 y asi las nuevas
                                             -- entidades se crean sin pisar los ids de las de la carga inicial.


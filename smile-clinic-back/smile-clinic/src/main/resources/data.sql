-- Insert sample data into the 'students' table with snake_case column names

INSERT INTO users (id, first_name, last_name1, last_name2, username, dni, email, password, role)
VALUES
    (1, 'Alice', 'Smith', 'Johnson', 'alice123', '12345678A', 'alice.smith@example.com', 'password123', 'CLINIC_ADMIN'),
    (2, 'Bob', 'Johnson', NULL, 'bobbyJ', '23456789B', 'bob.johnson@example.com', 'password456', 'CLINIC_DENTIST'),
    (3, 'Charlie', 'Brown', 'Taylor', 'charlieB', '34567890C', 'charlie.brown@example.com', 'password789', 'CLINIC_ADMIN'),
    (4, 'Diana', 'Green', 'Miller', 'dianaGM', '45678901D', 'diana.green@example.com', 'securePass1', 'CLINIC_DENTIST'),
    (5, 'Eve', 'White', NULL, 'eveWhite', '56789012E', 'eve.white@example.com', 'password987', 'CLINIC_ADMIN'),
    (6, 'Frank', 'Black', 'Scott', 'frankScott', '67890123F', 'frank.black@example.com', 'password654', 'CLINIC_DENTIST');

INSERT INTO clinics (id, name, address, phone, email, website, description, image, owner_id)
VALUES
    (8, 'Clínica Dental Sonrisa', 'Calle Mayor 10, Sevilla', '954000111', 'contacto@sonrisa.com', 'https://sonrisa.com', 'Especialistas en ortodoncia', 'sonrisa.jpg', 1),
    (9, 'Centro Médico Salud Total', 'Avenida Andalucía 23, Sevilla', '954111222', 'info@saludtotal.com', 'https://saludtotal.com', 'Atención médica integral', 'saludtotal.jpg', 2),
    (10, 'Clínica Bella Vida', 'Calle Sierpes 45, Sevilla', '954222333', 'info@bellavida.com', 'https://bellavida.com', 'Tratamientos avanzados.', 'bellavida.jpg', 3),
    (11, 'Odontologia Avanzada', 'Calle Feria 5, Sevilla', '954333444', 'contacto@avanzada.com', 'https://avanzada.com', 'Higiene bucal.', 'avanzada.jpg', 4),
    (12, 'Centro Boca Sana', 'Plaza Nueva 12, Sevilla', '954444555', 'info@bocasana.com', 'https://bocasana.com', 'Odontología', 'bocasana.jpg', 5);
